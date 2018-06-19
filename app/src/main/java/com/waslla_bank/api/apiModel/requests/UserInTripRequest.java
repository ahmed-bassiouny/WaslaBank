package com.waslla_bank.api.apiModel.requests;

import com.google.gson.annotations.SerializedName;
import com.waslla_bank.api.apiModel.ApiKey;

public class UserInTripRequest {

    @SerializedName(ApiKey.REQUEST_ID)
    private int requestId;
    @SerializedName(ApiKey.USER_ID)
    private int userId;
    @SerializedName(ApiKey.TYPE)
    private String type;
    @SerializedName(ApiKey.LAT)
    private double lat;
    @SerializedName(ApiKey.LNG)
    private double lng;

    public UserInTripRequest(int requestId, int userId, String type, double lat, double lng) {
        this.requestId = requestId;
        this.userId = userId;
        this.type = type;
        this.lat = lat;
        this.lng = lng;
    }

    public UserInTripRequest() {
    }
}
