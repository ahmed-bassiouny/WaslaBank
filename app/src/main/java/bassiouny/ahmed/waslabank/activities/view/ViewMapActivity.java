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
import bassiouny.ahmed.waslabank.adapter.UserInTripItem;
import bassiouny.ahmed.waslabank.api.ApiRequests;
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

public class ViewMapActivity extends MyToolbar implements OnMapReadyCallback, LocationListener, ItemClickInterface<UserInTripFirebase> {

    // view
    private FrameLayout frameLayoutUsers;
    private ViewStub viewStubProgress;
    private RecyclerView recycler;
    private TextView tvTryAgain;
    // local variable
    private LocationManager locationManager;
    private final int requestLocationPermission = 1;
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
    private boolean driverView = false;
    private ValueEventListener driverListener;
    private ValueEventListener userListener;
    // adapter
    private UserInTripItem adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_view_map);
        // init tool bar
        initToolbar("", false);
        addBackImagePrimary();
        addSupportActionbar();
        // add view
        findView();
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
        // set (driver or user )
        user = SharedPrefManager.getObject(SharedPrefKey.USER, User.class);
        // get trip id
        tripId = getIntent().getIntExtra("TRIP_ID", 0);
        // check if trip id == 0 this mean something happned wrong
        if (tripId == 0)
            finish();
        // check if this activity for driver
        driverView = getIntent().getBooleanExtra("DRIVER_VIEW", false);
        if (driverView) {
            // driver view
            // set driver icon
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker));
            // add finish trip button
            addView(addFinishTripButton(), layoutParamFinishTripIcon());

        } else {
            // user view
            // add user information in firebase
            // user information = name , image
            FirebaseRoot.setUserInfo(tripId, user.getId(), user.getImage(), user.getName());
            // make frame layout contain users invisible because this is user view
            frameLayoutUsers.setVisibility(View.GONE);
        }
    }

    private void startLoadUserJoinedInTrip() {
        frameLayoutUsers.setVisibility(View.VISIBLE);
        viewStubProgress.setVisibility(View.VISIBLE);
        recycler.setVisibility(View.INVISIBLE);
        adapter = new UserInTripItem(this);
        recycler.getItemAnimator().setChangeDuration(0);
        recycler.setAdapter(adapter);
    }

    private void findView() {
        frameLayoutUsers = findViewById(R.id.frameLayout_users);
        viewStubProgress = findViewById(R.id.view_stub_progress);
        recycler = findViewById(R.id.recycler);
        tvTryAgain = findViewById(R.id.tv_try_again);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.addItemDecoration(new SimpleDividerItemDecoration(this));
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
        // remove listener about users
        if (userListener != null)
            FirebaseRoot.removeListenerForUsers(tripId, userListener);
        // remove listener about drivers
        if (driverListener != null)
            FirebaseRoot.removeListenerForDriver(tripId, driverListener);
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
        if (driverView) {
            // driver view
            // add listener for users
            getUsersListener();
            FirebaseRoot.addListenerForUsers(tripId, userListener);
            startLoadUserJoinedInTrip();
        } else {
            // user view
            // add listener for driver
            getDriverListener();
            FirebaseRoot.addListenerForDriver(tripId, driverListener);
        }
    }

    private void updateLocation(Location location) {
        if (driverView) {
            // update driver location on firebase
            FirebaseRoot.updateDriverLocation(tripId, user.getId(), location.getLatitude(), location.getLongitude());
        } else {
            FirebaseRoot.updateUserLocation(tripId, user.getId(), location.getLatitude(), location.getLongitude());
        }
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

    private void getUsersListener() {
        userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clearList();
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    if (item == null)
                        return;
                    UserInTripFirebase user = item.getValue(UserInTripFirebase.class);
                    if (user != null && googleMap != null && markerOptions.getPosition() != null) {
                        googleMap.clear();
                        // add current driver user app
                        userMarker = googleMap.addMarker(markerOptions);
                        if (!user.getName().isEmpty()) {
                            googleMap.addMarker(new MarkerOptions().position(new LatLng(user.getCurrentLat(), user.getCurrentLng())));
                            adapter.addUser(user);
                        }
                    }
                }
                // loaded users and make recycler visible
                recycler.setVisibility(View.VISIBLE);
                viewStubProgress.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

    @Override
    public void getItem(@Nullable final UserInTripFirebase userInTripFirebase, final int position) {
        if (userInTripFirebase == null)
            return;
        // user in trip and still waiting to joined
        if (!userInTripFirebase.isJoined()) {
            // join user to this trip
            adapter.loading(position, true);
            FirebaseRoot.setUserTripLocation(tripId, userInTripFirebase.getUserId(), markerOptions.getPosition().latitude, markerOptions.getPosition().longitude,
                    DateTimeManager.convertUnixTimeStampToString(Calendar.getInstance().getTimeInMillis(), DateTimeFormat.DATE_TIME_24_FORMAT), new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            adapter.loading(position, false);
                        }
                    });
        } else {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setMessage("Are you sure you want to leave " + userInTripFirebase.getName() + " ?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            makeUserLeaveTrip(userInTripFirebase, position);
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    private void makeUserLeaveTrip(final UserInTripFirebase userInTripFirebase, final int position) {
        adapter.loading(position, true);
        // leave user from this trip
        TripDetailsRequest.Builder builder = new TripDetailsRequest.Builder();
        builder.userId(userInTripFirebase.getUserId());
        builder.requestId(tripId);
        builder.startAt(userInTripFirebase.getStartDateTime());
        builder.startPointLat(userInTripFirebase.getStartLat());
        builder.startPointLng(userInTripFirebase.getStartLng());
        builder.endAt(DateTimeManager.convertUnixTimeStampToString(Calendar.getInstance().getTimeInMillis(), DateTimeFormat.DATE_TIME_24_FORMAT));
        builder.endPointLat(markerOptions.getPosition().latitude);
        builder.endPointLng(markerOptions.getPosition().longitude);
        builder.isFinished();
        ApiRequests.setTripDetails(builder.build(), new BaseResponseInterface() {
            @Override
            public void onSuccess(Object o) {
                // remove current user
                FirebaseRoot.deleteUserTripLocation(tripId, userInTripFirebase.getUserId());
                // get all users still in trip
                FirebaseRoot.addListenerForUsers(tripId, userListener);
            }

            @Override
            public void onFailed(String errorMessage) {
                Toast.makeText(ViewMapActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                adapter.loading(position, false);
            }
        });
    }

    public ImageView addFinishTripButton() {
        // create finishTrip image view
        ImageView finishTrip = new ImageView(this);
        // finishTrip image view src
        finishTrip.setImageDrawable(getResources().getDrawable(R.drawable.ic_cancel));
        // handle click item
        finishTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter.getItemCount() == 0) {
                    // driver can finish trip
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(ViewMapActivity.this, R.style.AlertDialogStyle);
                    } else {
                        builder = new AlertDialog.Builder(ViewMapActivity.this);
                    }
                    builder.setTitle("Are you sure you want to finish trip")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // todo finish trip request
                                    // todo remove trip root
                                    // restart app
                                    Intent i = new Intent(ViewMapActivity.this, HomeActivity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(i);
                                    finish();
                                }
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .show();
                } else {
                    // driver must leave user
                    Toast.makeText(ViewMapActivity.this, "you still have users in your trip", Toast.LENGTH_LONG).show();
                }
            }
        });
        return finishTrip;
    }

    private FrameLayout.LayoutParams layoutParamFinishTripIcon() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.END);
        params.setMargins(10, 10, 50, 10);
        return params;
    }
}
