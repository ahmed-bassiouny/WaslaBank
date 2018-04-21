package bassiouny.ahmed.waslabank.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import com.google.android.gms.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by bassiouny on 14/11/17.
 */


public class LocationManager implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private int numberOfUpdate = 1000;
    private int wainitForNextLocation = 10000;
    private LocationListener locationListener;
    LocationRequest request;

    public LocationManager(Context context, LocationListener locationListener) {
        this.locationListener = locationListener;
        buildGoogleApiClient(context);
    }

    private void buildGoogleApiClient(Context context) {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(context)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
            mGoogleApiClient.connect();
        }
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void onConnected(Bundle bundle) {
        if(request ==null)
            request = new LocationRequest().setNumUpdates(numberOfUpdate).setInterval(wainitForNextLocation);
        addListener();
    }


    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @SuppressWarnings("MissingPermission")
    private void addListener(){
        if(mGoogleApiClient.isConnected())
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, request, locationListener);
    }
    public void removeListener(LocationListener locationListener) {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, locationListener);
    }

}
