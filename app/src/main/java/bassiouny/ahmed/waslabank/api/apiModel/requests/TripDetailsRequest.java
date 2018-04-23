package bassiouny.ahmed.waslabank.api.apiModel.requests;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;

/**
 * Created by bassiouny on 22/04/18.
 */

public class TripDetailsRequest {

    @SerializedName(ApiKey.USER_ID)
    private int userId;
    @SerializedName(ApiKey.REQUEST_ID)
    private int requestId;
    @SerializedName(ApiKey.START_POINT_LAT)
    private double startPointLat;
    @SerializedName(ApiKey.START_POINT_LNG)
    private double startPointLng;
    @SerializedName(ApiKey.START_AT)
    private String startAt;
    @SerializedName(ApiKey.END_POINT_LAT)
    private double endPointLat;
    @SerializedName(ApiKey.END_POINT_LNG)
    private double endPointLng;
    @SerializedName(ApiKey.END_AT)
    private String endAt;
    @SerializedName(ApiKey.IS_FINISHED)
    private int isFinished;
    @SerializedName(ApiKey.DRIVER_FINISH_TRIP)
    private int driverFinishTrip;

    private TripDetailsRequest(Builder builder) {
        userId = builder.userId;
        requestId = builder.requestId;
        startPointLat = builder.startPointLat;
        startPointLng = builder.startPointLng;
        startAt = builder.startAt;
        endPointLat = builder.endPointLat;
        endPointLng = builder.endPointLng;
        endAt = builder.endAt;
        isFinished = builder.isFinished;
        driverFinishTrip = builder.driverFinishTrip;
    }

    public static final class Builder {
        private int userId;
        private int requestId;
        private double startPointLat;
        private double startPointLng;
        private String startAt;
        private double endPointLat;
        private double endPointLng;
        private String endAt;
        private int isFinished;
        private int driverFinishTrip;

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

        public Builder startPointLat(double val) {
            startPointLat = val;
            return this;
        }

        public Builder startPointLng(double val) {
            startPointLng = val;
            return this;
        }

        public Builder startAt(String val) {
            startAt = val;
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

        public Builder isFinished() {
            isFinished = 1;
            return this;
        }

        public Builder driverFinishTrip() {
            driverFinishTrip = 1;
            return this;
        }

        public TripDetailsRequest build() {
            return new TripDetailsRequest(this);
        }
    }
}
