package bassiouny.ahmed.wasllabank.api.apiModel.requests;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.wasllabank.api.apiModel.ApiKey;

/**
 * Created by bassiouny on 18/04/18.
 */

public class TripStatusRequest {

    @SerializedName(ApiKey.REQUEST_ID)
    private int requestId;
    @SerializedName(ApiKey.IS_FINISHED)
    private int isFinished;
    @SerializedName(ApiKey.IS_CANCELED)
    private int isCanceled;

    private TripStatusRequest(Builder builder) {
        requestId = builder.requestId;
        isFinished = builder.isFinished;
        isCanceled = builder.isCanceled;
    }


    public static final class Builder {
        private int requestId;
        private int isFinished;
        private int isCanceled;

        public Builder() {
        }

        public Builder requestId(int val) {
            requestId = val;
            return this;
        }

        public Builder isFinished(boolean val) {
            if(val)
                isFinished = 1;
            else
                isFinished = 0;
            return this;
        }

        public Builder isCanceled(boolean val) {
            if(val)
                isCanceled = 1;
            else
                isCanceled = 0;
            return this;
        }

        public TripStatusRequest build() {
            return new TripStatusRequest(this);
        }
    }
}
