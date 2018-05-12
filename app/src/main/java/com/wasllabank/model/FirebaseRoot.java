package com.wasllabank.model;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bassiouny on 21/04/18.
 */

public class FirebaseRoot {
    private final static String trip = "trip";
    private final static String user = "user";
    private final static String userIdStr = "userId";
    private final static String driver = "driver";
    private final static String startLatStr = "startLat";
    private final static String startLngStr = "startLng";
    private final static String startDateTimeStr = "startDateTime";
    private final static String currentLatStr = "currentLat";
    private final static String currentLngStr = "currentLng";
    private final static String imageStr = "image";
    private final static String nameStr = "name";
    private final static String joinedStr = "joined";

    public static void updateDriverLocation(int tripId, int driverId, double currentLat, double currentLng) {
        Map<String, Object> locationMap = new HashMap<>();
        locationMap.put(currentLatStr, currentLat);
        locationMap.put(currentLngStr, currentLng);
        FirebaseDatabase.getInstance().getReference()
                .child(trip)
                .child(String.valueOf(tripId))
                .child(driver)
                .child(String.valueOf(driverId))
                .updateChildren(locationMap);
    }

    public static void updateUserLocation(int tripId, int userId, double currentLat, double currentLng) {
        Map<String, Object> locationMap = new HashMap<>();
        locationMap.put(currentLatStr, currentLat);
        locationMap.put(currentLngStr, currentLng);
        FirebaseDatabase.getInstance().getReference()
                .child(trip)
                .child(String.valueOf(tripId))
                .child(user)
                .child(String.valueOf(userId))
                .updateChildren(locationMap);
    }

    public static void setUserInfo(int tripId, int userId, String image, String name) {
        Map<String, Object> locationMap = new HashMap<>();
        locationMap.put(imageStr, image);
        locationMap.put(nameStr, name);
        locationMap.put(userIdStr, userId);
        FirebaseDatabase.getInstance().getReference()
                .child(trip)
                .child(String.valueOf(tripId))
                .child(user)
                .child(String.valueOf(userId))
                .updateChildren(locationMap);
    }

    public static void setUserTripLocation(int tripId, int userId, double startLat, double startLng, String startDateTime, OnCompleteListener onCompleteListener) {
        Map<String, Object> locationMap = new HashMap<>();
        locationMap.put(startLatStr, startLat);
        locationMap.put(startLngStr, startLng);
        locationMap.put(startDateTimeStr, startDateTime);
        locationMap.put(joinedStr, true);
        FirebaseDatabase.getInstance().getReference()
                .child(trip)
                .child(String.valueOf(tripId))
                .child(user)
                .child(String.valueOf(userId))
                .updateChildren(locationMap).addOnCompleteListener(onCompleteListener);
    }

    public static void deleteUserTripLocation(int tripId, int userId) {

        FirebaseDatabase.getInstance().getReference()
                .child(trip)
                .child(String.valueOf(tripId))
                .child(user)
                .child(String.valueOf(userId))
                .removeValue();
    }

    public static void addListenerForDriver(int tripId, ValueEventListener valueEventListener) {
        FirebaseDatabase.getInstance().getReference()
                .child(trip)
                .child(String.valueOf(tripId))
                .child(driver)
                .addValueEventListener(valueEventListener);
    }

    public static void removeListenerForDriver(int tripId, ValueEventListener valueEventListener) {
        FirebaseDatabase.getInstance().getReference()
                .child(trip)
                .child(String.valueOf(tripId))
                .child(driver)
                .removeEventListener(valueEventListener);
    }

    public static void addListenerForUsers(int tripId, ValueEventListener valueEventListener) {
        FirebaseDatabase.getInstance().getReference()
                .child(trip)
                .child(String.valueOf(tripId))
                .child(user)
                .addValueEventListener(valueEventListener);
    }

    public static void removeListenerForUsers(int tripId, ValueEventListener valueEventListener) {
        FirebaseDatabase.getInstance().getReference()
                .child(trip)
                .child(String.valueOf(tripId))
                .child(user)
                .removeEventListener(valueEventListener);
    }
    public static void removeTrip(int tripId) {
        FirebaseDatabase.getInstance().getReference()
                .child(trip)
                .child(String.valueOf(tripId))
                .removeValue();
    }
    // make listener on user name
    // if user name not found this case mean driver deleted user
    public static void deleteUserByDriver(int tripId,int userId, ValueEventListener valueEventListener) {
        FirebaseDatabase.getInstance().getReference()
                .child(trip)
                .child(String.valueOf(tripId))
                .child(user)
                .child(String.valueOf(userId))
                .child("name")
                .addValueEventListener(valueEventListener);
    }
}
