package com.wasllabank.fragments.view;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wasllabank.activities.view.CancelTripActivity;
import com.wasllabank.activities.view.DriverViewMapActivity;
import com.wasllabank.activities.view.UserViewMapActivity;
import com.wasllabank.fragments.controller.TripDetailsController;
import com.wasllabank.interfaces.BaseResponseInterface;
import com.wasllabank.interfaces.ObserverInterface;
import com.wasllabank.model.TripDetails;
import com.wasllabank.model.User;
import com.wasllabank.utils.LocationManager;
import com.wasllabank.utils.MyUtils;
import com.wasllabank.utils.SharedPrefKey;
import com.google.android.gms.location.LocationListener;

import bassiouny.ahmed.genericmanager.SharedPrefManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class TripDetailsFragment extends Fragment implements ObserverInterface<TripDetails>, LocationListener {


    private static final int REQUEST_CODE = 30;
    // local variable
    private static TripDetailsFragment mInstance;
    private int userId;
    private int status;
    private TripDetailsController controller;
    private LocationManager locationManager;
    private final int requestLocationPermission = 1;
    private Location location; // current location
    private String comment;

    // Driver
    public final int tripRunningDriver = 1;
    public final int tripNotRunningDriver = 2;

    // User
    public final int tripRunningJoinedUser = 3;
    public final int tripJoinedUser = 4;
    public final int tripNotJoinedUser = 5;

    //view
    private TextView tvFrom;
    private TextView tvTo;
    private TextView tvAvailablePlaces;
    private TextView tvDate, tvTime;
    private Button btnJoinTrip, btnCancel;
    private TripDetails tripDetails;
    private ViewStub viewStub;

    public TripDetailsFragment() {
        // Required empty public constructor
    }

    public static TripDetailsFragment getInstance() {
        if (mInstance == null) {
            mInstance = new TripDetailsFragment();
        }
        return mInstance;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(com.wasllabank.R.layout.fragment_trip_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        onClick();
        requestLocationPermissionAndGetLocation();
    }

    @Override
    public void onResume() {
        super.onResume();
        // check if gps enable
        if (!MyUtils.isGpsEnable(getContext())) {
            MyUtils.showSettingsAlert(TripDetailsFragment.this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (locationManager != null)
            locationManager.removeListener(this);
    }

    private void onClick() {

        btnJoinTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (status) {
                    case tripRunningDriver:
                        // trip now running
                        // driver can show map
                        Intent i = new Intent(getContext(), DriverViewMapActivity.class);
                        i.putExtra("TRIP_ID", tripDetails.getId());
                        i.putExtra("DRIVER_VIEW", true);
                        startActivity(i);
                        break;
                    case tripNotRunningDriver:
                        // trip now not running
                        // check location object to get lat and lng to save it in backend
                        // to know start trip location
                        if (location == null) {
                            Toast.makeText(getActivity(), "please try again", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // driver can start trip
                        loading(true);
                        getController().startTrip(tripDetails.getId(), userId, true,
                                location.getLatitude(), location.getLongitude(), new BaseResponseInterface() {
                                    @Override
                                    public void onSuccess(Object o) {
                                        if (getActivity() == null)
                                            return;
                                        tripDetails.setIsRunning();
                                        loading(false);
                                    }

                                    @Override
                                    public void onFailed(String errorMessage) {
                                        if (getActivity() == null)
                                            return;
                                        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                                        loading(false);
                                    }
                                });
                        break;
                    case tripRunningJoinedUser:
                        // trip now running
                        // user can view map
                        Intent intent = new Intent(getContext(), UserViewMapActivity.class);
                        intent.putExtra("TRIP_ID", tripDetails.getId());
                        intent.putExtra("DRIVER_ID", tripDetails.getDriver().getId());
                        startActivity(intent);
                        break;
                    case tripNotJoinedUser:
                        // trip now not running
                        // user can join trip
                        loading(true);
                        getController().joinTrip(tripDetails.getId(), userId, true,comment, new BaseResponseInterface() {
                            @Override
                            public void onSuccess(Object o) {
                                if (getActivity() == null)
                                    return;
                                tripDetails.setIsJoined(true);
                                loading(false);
                            }

                            @Override
                            public void onFailed(String errorMessage) {
                                if (getActivity() == null)
                                    return;
                                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                                loading(false);
                            }
                        });
                        break;
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getContext(), CancelTripActivity.class),REQUEST_CODE);
            }
        });
    }

    private void findView(View view) {
        tvFrom = view.findViewById(com.wasllabank.R.id.tv_from);
        tvTo = view.findViewById(com.wasllabank.R.id.tv_to);
        tvAvailablePlaces = view.findViewById(com.wasllabank.R.id.tv_available_places);
        tvDate = view.findViewById(com.wasllabank.R.id.tv_date);
        tvTime = view.findViewById(com.wasllabank.R.id.tv_time);
        btnJoinTrip = view.findViewById(com.wasllabank.R.id.btn_join_trip);
        btnCancel = view.findViewById(com.wasllabank.R.id.btn_cancel);
        viewStub = view.findViewById(com.wasllabank.R.id.view_stub_progress);
    }

    @Override
    public void update(TripDetails tripDetails) {
        // set trip details
        tvFrom.setText(tripDetails.getStartPointText());
        tvTo.setText(tripDetails.getEndPointText());
        tvDate.setText(tripDetails.getDate());
        tvTime.setText(tripDetails.getTime());
        tvAvailablePlaces.setText(tripDetails.getAvailablePlaces());
        // set user id
        userId = SharedPrefManager.getObject(SharedPrefKey.USER, User.class).getId();
        // check what should user do
        this.tripDetails = tripDetails;
        checkTripStatus();
    }

    private void checkTripStatus() {
        // check if current user who created this trip
        if (tripDetails.getDriver().getId() == userId) {
            // driver
            // check if trip running
            if (tripDetails.getIsRunning()) {
                // running trip => user can view map
                btnJoinTrip.setText(getString(com.wasllabank.R.string.view_map));
                btnJoinTrip.setVisibility(View.VISIBLE);
                btnCancel.setVisibility(View.INVISIBLE);
                status = tripRunningDriver;
            } else {
                // not running trip => user can cancel trip because he is driver
                btnJoinTrip.setText(getString(com.wasllabank.R.string.start));
                btnJoinTrip.setVisibility(View.VISIBLE);
                btnCancel.setVisibility(View.VISIBLE);
                status = tripNotRunningDriver;
            }
        } else {
            // user
            // check if i joined in trip && trip running now
            if (tripDetails.getIsJoined() && tripDetails.getIsRunning()&&tripDetails.isAccepted()) {
                // user can't cancel
                btnCancel.setVisibility(View.INVISIBLE);
                // user view map
                btnJoinTrip.setText(getString(com.wasllabank.R.string.view_map));
                btnJoinTrip.setVisibility(View.VISIBLE);
                status = tripRunningJoinedUser;
            } else if (tripDetails.getIsJoined() && !tripDetails.getIsRunning()) {
                // user in trip but trip not running
                // in this case user can cancel joined request
                btnCancel.setVisibility(View.VISIBLE);
                btnJoinTrip.setVisibility(View.INVISIBLE);
                status = tripJoinedUser;
                // todo client request user can make request if driver started trip
            } else /*if (!tripDetails.getIsJoined() && !tripDetails.getIsRunning()) */{
                // this case user not in trip && trip not running
                btnCancel.setVisibility(View.INVISIBLE);
                btnJoinTrip.setText(getString(com.wasllabank.R.string.join_trip));
                btnJoinTrip.setVisibility(View.VISIBLE);
                status = tripNotJoinedUser;
            } /*else {
                // this case user not in trip and trip running
                btnCancel.setVisibility(View.INVISIBLE);
                btnJoinTrip.setVisibility(View.INVISIBLE);
            }*/
        }
    }

    private TripDetailsController getController() {
        if (controller == null)
            controller = new TripDetailsController();
        return controller;
    }

    private void loading(boolean isLoading) {
        if (isLoading) {
            viewStub.setVisibility(View.VISIBLE);
            btnJoinTrip.setVisibility(View.INVISIBLE);
            btnCancel.setVisibility(View.INVISIBLE);
        } else {
            viewStub.setVisibility(View.INVISIBLE);
            checkTripStatus();
        }
    }

    // request to access location (run time permission )
    private void requestLocationPermissionAndGetLocation() {
        if(getContext() == null)
            return;
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED)
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, requestLocationPermission);
        else
            locationManager = new LocationManager(getContext(), this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length == 0 )
            return;
        if (requestCode == requestLocationPermission && grantResults[0] == PackageManager.PERMISSION_DENIED)
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, requestLocationPermission);
        else if (requestCode == requestLocationPermission && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            locationManager = new LocationManager(getContext(), this);
    }


    @Override
    public void onLocationChanged(Location location) {
        // get location and save it in shared pref
        /*String now = DateTimeManager.convertUnixTimeStampToString(Calendar.getInstance().getTimeInMillis(), DateTimeFormat.DATE_TIME_24_FORMAT);
        CurrentTripRequest currentTripRequest = new CurrentTripRequest(location.getLatitude(), location.getLongitude(), now);
        SharedPrefManager.setObject(SharedPrefKey.CURRENT_TRIP, currentTripRequest);
        locationManager.removeListener(this);*/
        TripDetailsFragment.this.location = location;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(REQUEST_CODE ==  requestCode && resultCode == Activity.RESULT_OK){
            comment = data.getStringExtra("comment");
            loading(true);
            switch (status) {
                case tripNotRunningDriver:
                    // driver can cancel trip
                    getController().finishTrip(tripDetails.getId(), false, true,comment, new BaseResponseInterface() {
                        @Override
                        public void onSuccess(Object o) {
                            // this mean trip canceled
                            // finish activity
                            if (getActivity() == null)
                                return;
                            getActivity().finish();
                        }

                        @Override
                        public void onFailed(String errorMessage) {
                            if (getActivity() == null)
                                return;
                            Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                            loading(false);
                        }
                    });
                    break;
                case tripJoinedUser:
                    // user can cancel joined
                    loading(true);
                    getController().joinTrip(tripDetails.getId(), userId, false,comment, new BaseResponseInterface() {
                        @Override
                        public void onSuccess(Object o) {
                            if (getActivity() == null)
                                return;
                            tripDetails.setIsJoined(false);
                            loading(false);
                        }

                        @Override
                        public void onFailed(String errorMessage) {
                            if (getActivity() == null)
                                return;
                            Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                            loading(false);
                        }
                    });
                    break;
            }
        }
    }
}