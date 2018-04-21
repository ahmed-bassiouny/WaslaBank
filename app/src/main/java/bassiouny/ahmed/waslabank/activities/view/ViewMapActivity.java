package bassiouny.ahmed.waslabank.activities.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.model.FirebaseRoot;
import bassiouny.ahmed.waslabank.model.User;
import bassiouny.ahmed.waslabank.utils.LocationManager;
import bassiouny.ahmed.waslabank.utils.MyUtils;
import bassiouny.ahmed.waslabank.utils.SharedPrefKey;

public class ViewMapActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    // local variable
    private LocationManager locationManager;
    private final int requestLocationPermission = 1;
    private GoogleMap googleMap;
    private MarkerOptions markerOptions;
    private Marker userMarker;
    // zoom on map to make zoom first time
    private boolean zoomOnMap = true;
    private int userId;
    private int tripId;
    // driver view if true make this activity for driver
    // else make this activity for user
    private boolean driverView = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_view_map);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // check if request permission to access location
        // if true get location manager
        // else ask for permission
        requestLocationPermission();
        // init marker
        markerOptions = new MarkerOptions();
        // set driver id
        userId = SharedPrefManager.getObject(SharedPrefKey.USER, User.class).getId();
        // get trip id
        tripId = getIntent().getIntExtra("TRIP_ID", 0);
        // check if trip id == 0 this mean something happned wrong
        if (tripId == 0)
            finish();
        // check if this activity for driver
        driverView = getIntent().getBooleanExtra("DRIVER_VIEW", false);
        if (driverView) {
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // check if gps enable
        if (!MyUtils.isGpsEnable(this)) {
            MyUtils.showSettingsAlert(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationManager.removeListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    // request to access location (run time permission )
    private void requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, requestLocationPermission);
        else
            locationManager = new LocationManager(this, this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == requestLocationPermission && grantResults[0] == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, requestLocationPermission);
        else if (requestCode == requestLocationPermission && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            locationManager = new LocationManager(this, this);
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
        updateLocation(location);
        // make zoom first time
        if (zoomOnMap) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markerOptions.getPosition(), 15), 1000, null);
            zoomOnMap = false;
        }
    }

    private void updateLocation(Location location) {
        if (driverView) {
            // update driver location on firebase
            if (userId != 0)
                FirebaseRoot.updateDriverLocation(tripId, userId, location.getLatitude(), location.getLongitude());
        } else {
            FirebaseRoot.updateUserLocation(tripId, userId, location.getLatitude(), location.getLongitude());
        }
    }
}
