package bassiouny.ahmed.waslabank.activities.view;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.Gravity;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import bassiouny.ahmed.genericmanager.DateTimeManager;
import bassiouny.ahmed.genericmanager.SharedPrefManager;
import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.activities.controller.ViewMapController;
import bassiouny.ahmed.waslabank.adapter.UserInTripItem;
import bassiouny.ahmed.waslabank.api.ApiRequests;
import bassiouny.ahmed.waslabank.api.apiModel.requests.FinishTripRequest;
import bassiouny.ahmed.waslabank.api.apiModel.requests.TripDetailsRequest;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.waslabank.interfaces.ItemClickInterface;
import bassiouny.ahmed.waslabank.model.UserInTripFirebase;
import bassiouny.ahmed.waslabank.model.FirebaseRoot;
import bassiouny.ahmed.waslabank.model.User;
import bassiouny.ahmed.waslabank.utils.DateTimeFormat;
import bassiouny.ahmed.waslabank.utils.LocationManager;
import bassiouny.ahmed.waslabank.utils.MyToolbar;
import bassiouny.ahmed.waslabank.utils.MyUtils;
import bassiouny.ahmed.waslabank.utils.SharedPrefKey;
import bassiouny.ahmed.waslabank.utils.SimpleDividerItemDecoration;

public class UserViewMapActivity extends MyToolbar implements OnMapReadyCallback, LocationListener {


    // local variable
    private GoogleMap googleMap;
    private MarkerOptions markerOptions;
    private Marker userMarker;
    // zoom on map to make zoom first time
    private boolean zoomOnMap = true;
    // current user view map
    private User user;
    private int tripId;
    // driver view if true make this activity for driver
    // else make this activity for user
    private ValueEventListener driverListener;
    private ViewMapController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_map);
        // init tool bar
        initToolbar("", false);
        addBackImagePrimary();
        addSupportActionbar();
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // check if request permission to access location
        // if true get location manager
        // else ask for permission
        getController().requestLocationPermission();
        // init marker
        markerOptions = new MarkerOptions();
        // set (driver or user )
        user = SharedPrefManager.getObject(SharedPrefKey.USER, User.class);
        // get trip id
        tripId = getIntent().getIntExtra("TRIP_ID", 0);
        // check if trip id == 0 this mean something happned wrong
        if (tripId == 0)
            finish();

        // add user information in firebase
        // user information => name , image
        FirebaseRoot.setUserInfo(tripId, user.getId(), user.getImage(), user.getName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        getController().openGps();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getController().removeLocationListener();
        // remove listener about drivers
        if (driverListener != null)
            FirebaseRoot.removeListenerForDriver(tripId, driverListener);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        getController().onRequestPermissionsResult(requestCode, grantResults);
    }


    @Override
    public void onLocationChanged(Location location) {
        // check if user marker found => remove it
        if (userMarker != null)
            userMarker.remove();
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        markerOptions.position(latLng);
        userMarker = googleMap.addMarker(markerOptions);
        // update location on firebase
        FirebaseRoot.updateUserLocation(tripId, user.getId(), location.getLatitude(), location.getLongitude());
        // make zoom first time
        if (zoomOnMap) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markerOptions.getPosition(), 15), 1000, null);
            zoomOnMap = false;
        }

        // add listener for driver
        getDriverListener();
        FirebaseRoot.addListenerForDriver(tripId, driverListener);

    }


    private void getDriverListener() {
        driverListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    if (item == null)
                        return;
                    UserInTripFirebase location = item.getValue(UserInTripFirebase.class);
                    if (location != null && googleMap != null && markerOptions.getPosition() != null) {
                        googleMap.clear();
                        // add current user user app
                        userMarker = googleMap.addMarker(markerOptions);
                        googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker)).position(new LatLng(location.getCurrentLat(), location.getCurrentLng())));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

    private ViewMapController getController() {
        if (controller == null)
            controller = new ViewMapController(this);
        return controller;
    }
}

