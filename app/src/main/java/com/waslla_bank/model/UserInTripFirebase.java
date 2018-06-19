package com.waslla_bank.model;

import com.waslla_bank.utils.MyUtils;

/**
 * Created by bassiouny on 21/04/18.
 */

public class UserInTripFirebase {
    private double startLat;
    private double startLng;
    private String startDateTime;
    private double currentLat;
    private double currentLng;
    private int userId;
    private String image;
    private String name;
    private boolean joined;
    public boolean isLoading;

    public double getStartLat() {
        return startLat;
    }

    public void setStartLat(double startLat) {
        this.startLat = startLat;
    }

    public double getStartLng() {
        return startLng;
    }

    public void setStartLng(double startLng) {
        this.startLng = startLng;
    }

    public double getCurrentLat() {
        return currentLat;
    }

    public void setCurrentLat(double currentLat) {
        this.currentLat = currentLat;
    }

    public double getCurrentLng() {
        return currentLng;
    }

    public void setCurrentLng(double currentLng) {
        this.currentLng = currentLng;
    }

    public String getImage() {
        return MyUtils.getString(image);
    }

    public String getName() {
        return MyUtils.getString(name);
    }

    public boolean isJoined() {
        return joined;
    }

    public int getUserId() {
        return userId;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setJoined(boolean joined) {
        this.joined = joined;
    }
}
