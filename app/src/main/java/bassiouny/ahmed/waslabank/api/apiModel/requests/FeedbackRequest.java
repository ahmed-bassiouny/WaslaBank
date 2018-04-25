package bassiouny.ahmed.waslabank.api.apiModel.requests;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;

/**
 * Created by bassiouny on 25/04/18.
 */

public class FeedbackRequest {

    @SerializedName(ApiKey.REQUEST_ID)
    private int requestId;
    @SerializedName(ApiKey.USER_ID_FROM)
    private int userIdFrom;
    @SerializedName(ApiKey.USER_ID_TO)
    private int userIdTo;
    @SerializedName(ApiKey.RATE)
    private int rate;
    @SerializedName(ApiKey.COMMENT)
    private String comment;

    private FeedbackRequest(Builder builder) {
        requestId = builder.requestId;
        userIdFrom = builder.userIdFrom;
        userIdTo = builder.userIdTo;
        rate = builder.rate;
        comment = builder.comment;
    }


    public static final class Builder implements Parcelable {
        private int requestId;
        private int userIdFrom;
        private int userIdTo;
        private int rate;
        private String comment;

        public Builder() {
        }

        public Builder requestId(int val) {
            requestId = val;
            return this;
        }

        public Builder userIdFrom(int val) {
            userIdFrom = val;
            return this;
        }

        public Builder userIdTo(int val) {
            userIdTo = val;
            return this;
        }

        public Builder rate(int val) {
            rate = val;
            return this;
        }

        public Builder comment(String val) {
            comment = val;
            return this;
        }

        public FeedbackRequest build() {
            return new FeedbackRequest(this);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.requestId);
            dest.writeInt(this.userIdFrom);
            dest.writeInt(this.userIdTo);
            dest.writeInt(this.rate);
            dest.writeString(this.comment);
        }

        protected Builder(Parcel in) {
            this.requestId = in.readInt();
            this.userIdFrom = in.readInt();
            this.userIdTo = in.readInt();
            this.rate = in.readInt();
            this.comment = in.readString();
        }

        public static final Parcelable.Creator<Builder> CREATOR = new Parcelable.Creator<Builder>() {
            @Override
            public Builder createFromParcel(Parcel source) {
                return new Builder(source);
            }

            @Override
            public Builder[] newArray(int size) {
                return new Builder[size];
            }
        };
    }
}
