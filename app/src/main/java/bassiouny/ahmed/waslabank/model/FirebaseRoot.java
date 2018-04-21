package bassiouny.ahmed.waslabank.model;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bassiouny on 21/04/18.
 */

public class FirebaseRoot {
    private final static String trip = "trip";
    private final static String user = "user";
    private final static String startLatStr = "startLat";
    private final static String startLngStr = "startLng";
    private final static String currentLatStr = "currentLat";
    private final static String currentLngStr = "currentLng";

    public static void updateDriverLocation(int tripId,int driverId, double currentLat, double currentLng) {
        Map<String, Double> locationMap = new HashMap<>();
        locationMap.put(currentLatStr, currentLat);
        locationMap.put(currentLngStr, currentLng);
        FirebaseDatabase.getInstance().getReference()
                .child(trip)
                .child(String.valueOf(tripId))
                .child(String.valueOf(driverId))
                .setValue(locationMap);
    }
}
