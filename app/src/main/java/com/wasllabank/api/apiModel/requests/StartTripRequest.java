package com.wasllabank.api.apiModel.requests;

import com.wasllabank.api.apiModel.ApiKey;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bassiouny on 18/04/18.
 */

public class StartTripRequest {

    @SerializedName(ApiKey.USER_ID)
    private int userId;
    @SerializedName(ApiKey.REQUEST_ID)
    private int requestId;
    @SerializedName(ApiKey.IS_RUNNING)
    private int isRunning;
    @SerializedName(ApiKey.IS_JOINED)
    private int isJoined;
    @SerializedName(ApiKey.START_POINT_LAT)
    private double startPointLat;
    @SerializedName(ApiKey.START_POINT_LNG)
    private double startPointLng;

    private StartTripRequest(Builder builder) {
        userId = builder.userId;
        requestId = builder.requestId;
        isRunning = builder.isRunning;
        isJoined = builder.isJoined;
        startPointLat = builder.startPointLat;
        startPointLng = builder.startPointLng;
    }


    public static final class Builder {
        private int userId;
        private int requestId;
        private int isRunning;
        private int isJoined;
        private double startPointLat;
        private double startPointLng;

        public Builder() {
        }

        public Builder userId(int val) {
            userId = val;
            return this;
        }

        public Builder requestId(int val) {
            requestId = val;
            return this;
        }

        public Builder isRunning(boolean val) {
            if (val)
                isRunning = 1;
            else isRunning = 0;
            return this;
        }

        public Builder isJoined(boolean val) {
            if (val)
                isJoined = 1;
            else isJoined = 0;
            return this;
        }
        public Builder startPointLat(double val) {
            startPointLat = val;
            return this;
        }

        public Builder startPointLng(double val) {
            startPointLng = val;
            return this;
        }


        public StartTripRequest build() {
            return new StartTripRequest(this);
        }
    }
}
