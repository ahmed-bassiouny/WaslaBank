package com.waslla_bank.api.apiModel.requests;

import com.google.gson.annotations.SerializedName;

import com.waslla_bank.api.apiModel.ApiKey;

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
    @SerializedName(ApiKey.COMMENT)
    private String comment;

    private TripStatusRequest(Builder builder) {
        requestId = builder.requestId;
        isFinished = builder.isFinished;
        isCanceled = builder.isCanceled;
        comment = builder.comment;
    }


    public static final class Builder {
        private int requestId;
        private int isFinished;
        private int isCanceled;
        private String comment;

        public Builder() {
        }

        public Builder requestId(int val) {
            requestId = val;
            return this;
        }
        public Builder comment(String val) {
            comment = val;
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
