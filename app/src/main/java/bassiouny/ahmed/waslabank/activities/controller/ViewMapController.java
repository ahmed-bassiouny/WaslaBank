package bassiouny.ahmed.waslabank.activities.controller;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.location.LocationListener;

import bassiouny.ahmed.waslabank.utils.LocationManager;
import bassiouny.ahmed.waslabank.utils.MyUtils;

/**
 * Created by bassiouny on 21/04/18.
 */

public class ViewMapController {

    private Activity activity;
    private LocationListener locationListener;
    private LocationManager locationManager;
    private final int requestLocationPermission = 1;

    public ViewMapController(Activity activity) {
        this.activity = activity;
        this.locationListener = (LocationListener) activity;
    }

    // request to access location (run time permission )
    public void requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, requestLocationPermission);
        else
            initLocationManager();
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull int[] grantResults) {
        if (requestCode == requestLocationPermission && grantResults[0] == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, requestLocationPermission);
        else if (requestCode == requestLocationPermission && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            initLocationManager();
    }

    public void removeLocationListener() {
        locationManager.removeListener(locationListener);
    }

    private void initLocationManager(){
        locationManager = new LocationManager(activity, locationListener);
    }
    public void openGps(){
        // check if gps enable
        if (!MyUtils.isGpsEnable(activity)) {
            MyUtils.showSettingsAlert(activity);
        }
    }
}
