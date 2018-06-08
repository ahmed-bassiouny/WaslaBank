package com.wasllabank.activities.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wasllabank.R;
import com.wasllabank.api.ApiRequests;
import com.wasllabank.api.apiModel.requests.FeedbackRequest;
import com.wasllabank.api.apiModel.requests.FinishTripRequest;
import com.wasllabank.interfaces.ItemClickInterface;
import com.wasllabank.model.FirebaseRoot;
import com.wasllabank.model.User;
import com.wasllabank.model.UserInTrip;
import com.wasllabank.utils.DateTimeFormat;
import com.wasllabank.utils.MyToolbar;
import com.wasllabank.utils.SharedPrefKey;
import com.wasllabank.utils.SimpleDividerItemDecoration;
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
import java.util.List;

import bassiouny.ahmed.genericmanager.DateTimeManager;
import bassiouny.ahmed.genericmanager.SharedPrefManager;

import com.wasllabank.activities.controller.ViewMapController;
import com.wasllabank.adapter.UserInTripItem;
import com.wasllabank.api.apiModel.requests.TripDetailsRequest;
import com.wasllabank.interfaces.BaseResponseInterface;
import com.wasllabank.model.UserInTripFirebase;

public class DriverViewMapActivity extends MyToolbar implements OnMapReadyCallback, LocationListener, ItemClickInterface<UserInTrip> {

    // view
    private ViewStub viewStubProgress;
    private RecyclerView recycler;
    private TextView tvNoUser;
    // local variable
    private GoogleMap googleMap;
    private MarkerOptions markerOptions;
    private Marker userMarker;
    // zoom on map to make zoom first time
    private boolean zoomOnMap = true;
    // current user view map
    private User driver;
    private int tripId;
    private ValueEventListener userListener;
    // adapter
    private UserInTripItem adapter;
    private ViewMapController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.wasllabank.R.layout.activity_driver_view_map);
        // init tool bar
        initToolbar("", false);
        addBackImagePrimary();
        addSupportActionbar();
        // add view
        findView();
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(com.wasllabank.R.id.map);
        mapFragment.getMapAsync(this);
        // check if request permission to access location
        // if true get location manager
        // else ask for permission
        getController().requestLocationPermission();
        // init marker
        markerOptions = new MarkerOptions();
        // set (driver or user )
        driver = SharedPrefManager.getObject(SharedPrefKey.USER, User.class);
        // get trip id
        tripId = getIntent().getIntExtra("TRIP_ID", 0);
        // check if trip id == 0 this mean something happned wrong
        if (tripId == 0)
            finish();
        // driver view
        // set driver icon
        markerOptions.icon(BitmapDescriptorFactory.fromResource(com.wasllabank.R.drawable.car_marker));
        // add finish trip button
        addView(addFinishTripButton(), layoutParamFinishTripIcon());


    }


    private void startLoadUserJoinedInTrip() {
        viewStubProgress.setVisibility(View.VISIBLE);
        recycler.setVisibility(View.INVISIBLE);
        tvNoUser.setVisibility(View.GONE);
        ApiRequests.getUserInCurrentTrip(tripId, new BaseResponseInterface<List<UserInTrip>>() {
            @Override
            public void onSuccess(List<UserInTrip> userInTrips) {
                if(userInTrips.size() == 0 ){
                    tvNoUser.setVisibility(View.VISIBLE);
                }else {
                    adapter = new UserInTripItem(DriverViewMapActivity.this,userInTrips);
                    recycler.setAdapter(adapter);
                    recycler.setVisibility(View.VISIBLE);
                }
                viewStubProgress.setVisibility(View.GONE);
            }

            @Override
            public void onFailed(String errorMessage) {
                viewStubProgress.setVisibility(View.GONE);
                Toast.makeText(DriverViewMapActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findView() {
        viewStubProgress = findViewById(com.wasllabank.R.id.view_stub_progress);
        recycler = findViewById(com.wasllabank.R.id.recycler);
        tvNoUser = findViewById(com.wasllabank.R.id.tv_no_user);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.addItemDecoration(new SimpleDividerItemDecoration(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        startLoadUserJoinedInTrip();
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
        // remove listener about users
        if (userListener != null)
            FirebaseRoot.removeListenerForUsers(tripId, userListener);
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
        // update driver location on firebase
        FirebaseRoot.updateDriverLocation(tripId, driver.getId(), location.getLatitude(), location.getLongitude());
        // make zoom first time
        if (zoomOnMap) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markerOptions.getPosition(), 15), 1000, null);
            zoomOnMap = false;
            FirebaseRoot.addListenerForUsers(tripId, getUsersListener());
        }

    }

    private ValueEventListener getUsersListener() {
        if (userListener != null)
            return userListener;
        return userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                googleMap.clear();
                // add current driver user app
                userMarker = googleMap.addMarker(markerOptions);
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    if (item == null)
                        return;
                    UserInTripFirebase user = item.getValue(UserInTripFirebase.class);
                    if (user != null) {
                        googleMap.addMarker(new MarkerOptions().position(new LatLng(user.getCurrentLat(), user.getCurrentLng())));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

    @Override
    public void getItem(final UserInTrip user, final int position) {
        // user in trip and still waiting to joined
        if (!user.getIsEntered()) {
            // join user to this trip
            // start lading
            adapter.loading(position, true);
            // make join request
            ApiRequests.joinUserInCurrentTrip(user.getUserId()
                    , tripId, markerOptions.getPosition().latitude, markerOptions.getPosition().longitude, new BaseResponseInterface() {
                        @Override
                        public void onSuccess(Object o) {
                            // stop loading
                            adapter.loading(position, false);
                            // make user status in trip
                            adapter.joinedUser(position);

                        }

                        @Override
                        public void onFailed(String errorMessage) {
                            // stop loading
                            adapter.loading(position, false);
                            // print message
                            Toast.makeText(DriverViewMapActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                        }
                    });
            /*FirebaseRoot.setUserTripLocation(tripId, userInTripFirebase.getUserId(), markerOptions.getPosition().latitude, markerOptions.getPosition().longitude,
                    DateTimeManager.convertUnixTimeStampToString(Calendar.getInstance().getTimeInMillis(), DateTimeFormat.DATE_TIME_24_FORMAT), new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                        }
                    });*/
        } else {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, com.wasllabank.R.style.AlertDialogStyle);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setMessage("Are you sure you want to leave " + user.getUserName()+ " ?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            makeUserLeaveTrip(user.getUserId(), position);
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    private void makeUserLeaveTrip(final int userId , final int position) {

        // start lading
        adapter.loading(position, true);
        // make join request
        ApiRequests.leaveUserInCurrentTrip(userId
                , tripId, markerOptions.getPosition().latitude, markerOptions.getPosition().longitude, new BaseResponseInterface() {
                    @Override
                    public void onSuccess(Object o) {
                        // stop loading
                        adapter.loading(position, false);
                        // remove user from list
                        adapter.removeItem(position);
                        // remove current user (lat,lng) from firebase
                        FirebaseRoot.deleteUserTripLocation(tripId, userId);

                        // todo i don't need this because listener running
                        // get all users still in trip
                        //FirebaseRoot.addListenerForUsers(tripId, userListener);

                        // create feedback builder
                        FeedbackRequest.Builder builder = new FeedbackRequest.Builder();
                        // driver id
                        builder.userIdFrom(driver.getId());
                        // user id leaved this trip
                        builder.userIdTo(userId);
                        // trip id
                        builder.requestId(tripId);
                        // create intent to open feedback activity
                        Intent intent = new Intent(DriverViewMapActivity.this, FeedbackActivity.class);
                        intent.putExtra("FEEDBACK", builder);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailed(String errorMessage) {
                        // stop loading
                        adapter.loading(position, false);
                        // print message
                        Toast.makeText(DriverViewMapActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                    }
                });
        /*
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
                // make driver rate user

            }

            @Override
            public void onFailed(String errorMessage) {
            }
        });*/
    }

    public TextView addFinishTripButton() {
        // create finishTrip image view
        TextView finishTrip = new TextView(this);
        // finishTrip image view src
        finishTrip.setTextColor(getResources().getColor(R.color.white));
        finishTrip.setText(getResources().getString(R.string.end_trip));
        finishTrip.setPadding(50,20,50,20);
        finishTrip.setBackground(getResources().getDrawable(R.drawable.round_fill_red));
        // handle click item
        finishTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter == null ||adapter.getItemCount() == 0) {
                    // driver can finish trip
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(DriverViewMapActivity.this, com.wasllabank.R.style.AlertDialogStyle);
                    } else {
                        builder = new AlertDialog.Builder(DriverViewMapActivity.this);
                    }
                    builder.setTitle("Are you sure you want to finish trip")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    driverFinishTrip();
                                }
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .show();
                } else {
                    // driver must leave user
                    Toast.makeText(DriverViewMapActivity.this, "you still have users in your trip", Toast.LENGTH_LONG).show();
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

    private void driverFinishTrip() {
        // create finish request object
        Toast.makeText(this, "please wait", Toast.LENGTH_LONG).show();
        FinishTripRequest.Builder builder = new FinishTripRequest.Builder();
        builder.requestId(tripId);
        builder.userId(driver.getId());
        builder.endPointLat(markerOptions.getPosition().latitude);
        builder.endPointLng(markerOptions.getPosition().longitude);
        String now = DateTimeManager.convertUnixTimeStampToString(Calendar.getInstance().getTimeInMillis(), DateTimeFormat.DATE_TIME_24_FORMAT);
        builder.endAt(now);
        ApiRequests.driverFinishTrip(builder.build(), new BaseResponseInterface() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(DriverViewMapActivity.this, "Your Trip Finished", Toast.LENGTH_SHORT).show();
                // remove trip root from firebase
                FirebaseRoot.removeTrip(tripId);
                // close activity
                // start main activity
                Intent i = new Intent(DriverViewMapActivity.this, HomeActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }

            @Override
            public void onFailed(String errorMessage) {
                Toast.makeText(DriverViewMapActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ViewMapController getController() {
        if (controller == null)
            controller = new ViewMapController(this);
        return controller;
    }
}
