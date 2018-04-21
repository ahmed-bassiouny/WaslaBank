package bassiouny.ahmed.waslabank.api.apiModel.requests;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;

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

    private StartTripRequest(Builder builder) {
        userId = builder.userId;
        requestId = builder.requestId;
        isRunning = builder.isRunning;
        isJoined = builder.isJoined;
    }


    public static final class Builder {
        private int userId;
        private int requestId;
        private int isRunning;
        private int isJoined;

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

        public StartTripRequest build() {
            return new StartTripRequest(this);
        }
    }
}
