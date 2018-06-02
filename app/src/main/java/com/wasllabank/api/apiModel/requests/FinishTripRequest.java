package com.wasllabank.api.apiModel.requests;

import com.google.gson.annotations.SerializedName;

import com.wasllabank.api.apiModel.ApiKey;

/**
 * Created by bassiouny on 23/04/18.
 */

public class FinishTripRequest {

    @SerializedName(ApiKey.USER_ID)
    private int userId;
    @SerializedName(ApiKey.REQUEST_ID)
    private int requestId;
    @SerializedName(ApiKey.END_POINT_LAT)
    private double endPointLat;
    @SerializedName(ApiKey.END_POINT_LNG)
    private double endPointLng;
    @SerializedName(ApiKey.END_AT)
    private String endAt;

    private FinishTripRequest(Builder builder) {
        userId = builder.userId;
        requestId = builder.requestId;
        endPointLat = builder.endPointLat;
        endPointLng = builder.endPointLng;
        endAt = builder.endAt;
    }


    public static final class Builder {
        private int userId;
        private int requestId;
        private double endPointLat;
        private double endPointLng;
        private String endAt;

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

        public Builder endPointLat(double val) {
            endPointLat = val;
            return this;
        }

        public Builder endPointLng(double val) {
            endPointLng = val;
            return this;
        }

        public Builder endAt(String val) {
            endAt = val;
            return this;
        }

        public FinishTripRequest build() {
            return new FinishTripRequest(this);
        }
    }
}
