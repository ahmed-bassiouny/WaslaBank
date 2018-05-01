package bassiouny.ahmed.waslabank.api.apiModel.requests;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;

/**
 * Created by bassiouny on 01/05/18.
 */

public class AcceptRejectUser {

    @SerializedName(ApiKey.REQUEST_ID)
    private int requestId;
    @SerializedName(ApiKey.USER_ID)
    private int userId;
    @SerializedName(ApiKey.IS_ACCEPTED)
    private int isAccept;

    private AcceptRejectUser(Builder builder) {
        requestId = builder.requestId;
        userId = builder.userId;
        isAccept = builder.isAccept;
    }


    public static final class Builder {
        private int requestId;
        private int userId;
        private int isAccept;

        public Builder() {
        }

        public Builder requestId(int val) {
            requestId = val;
            return this;
        }

        public Builder userId(int val) {
            userId = val;
            return this;
        }

        public Builder isAccept(boolean val) {
            if (val)
                isAccept = 1;
            else isAccept = 0;
            return this;
        }

        public AcceptRejectUser build() {
            return new AcceptRejectUser(this);
        }
    }
}
