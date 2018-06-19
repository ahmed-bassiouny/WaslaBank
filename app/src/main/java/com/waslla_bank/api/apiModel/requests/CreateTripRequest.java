package com.waslla_bank.api.apiModel.requests;

import com.waslla_bank.api.apiModel.ApiKey;
import com.waslla_bank.utils.MyUtils;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bassiouny on 15/04/18.
 */

public class CreateTripRequest {

    @SerializedName(ApiKey.CREATED_BY)
    private int createdBy;
    @SerializedName(ApiKey.DRIVER_ID)
    private int driverId;
    @SerializedName(ApiKey.TYPE)
    private String type = "driver_hosting";
    @SerializedName(ApiKey.START_POINT_ADDRESS)
    private String startPointText;
    @SerializedName(ApiKey.START_POINT_LAT)
    private double startPointLat;
    @SerializedName(ApiKey.START_POINT_LNG)
    private double startPointLng;
    @SerializedName(ApiKey.END_POINT_ADDRESS)
    private String endPointText;
    @SerializedName(ApiKey.END_POINT_LAT)
    private double endPointLat;
    @SerializedName(ApiKey.END_POINT_LNG)
    private double endPointLng;
    @SerializedName(ApiKey.IS_CANCELED)
    private int isCanceled = 0;
    @SerializedName(ApiKey.DATE_TIME)
    private String dateTime;
    // transient to ignore date filed from json
    transient private String date;
    // transient to ignore time filed from json
    transient private String Time;

    private CreateTripRequest(Builder builder) {
        driverId = builder.driverId;
        createdBy = builder.driverId;
        startPointText = builder.startPointText;
        startPointLat = builder.startPointLat;
        startPointLng = builder.startPointLng;
        endPointText = builder.endPointText;
        endPointLat = builder.endPointLat;
        endPointLng = builder.endPointLng;
        dateTime = builder.date + " " + builder.Time;
        date = builder.date;
        Time = builder.Time;
    }


    public static final class Builder {
        private int driverId, createdBy;
        private String startPointText;
        private double startPointLat;
        private double startPointLng;
        private String endPointText;
        private double endPointLat;
        private double endPointLng;
        private String dateTime;
        private String date;
        private String Time;

        public Builder() {
        }

        public Builder driverId(int val) {
            driverId = val;
            createdBy = val;
            return this;
        }

        public Builder startPointText(String val) {
            startPointText = val;
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

        public Builder endPointText(String val) {
            endPointText = val;
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

        public Builder dateTime(String val) {
            dateTime = val;
            return this;
        }

        public Builder date(String val) {
            date = val;
            return this;
        }

        public Builder Time(String val) {
            // get time hour and minute
            // add second
            Time = val+":00";
            return this;
        }

        public CreateTripRequest build() {
            return new CreateTripRequest(this);
        }
    }

    public int getDriverId() {
        return driverId;
    }

    public String getStartPointText() {
        return MyUtils.getString(startPointText);
    }

    public double getStartPointLat() {
        return startPointLat;
    }

    public double getStartPointLng() {
        return startPointLng;
    }

    public String getEndPointText() {
        return MyUtils.getString(endPointText);
    }

    public double getEndPointLat() {
        return endPointLat;
    }

    public double getEndPointLng() {
        return endPointLng;
    }

    public String getDateTime() {
        return MyUtils.getString(dateTime);
    }

    public String getDate() {
        return MyUtils.getString(date);
    }

    public String getTime() {
        return MyUtils.getString(Time);
    }
}
