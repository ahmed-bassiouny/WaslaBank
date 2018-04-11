package bassiouny.ahmed.waslabank.api.apiModel.requests;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;

/**
 * Created by bassiouny on 11/04/18.
 */

@SuppressWarnings({"UnusedDeclaration"})
public class TripsByDate {

    @SerializedName(ApiKey.DATE)
    private String date;
    @SerializedName(ApiKey.PAGE)
    private int page;

    private TripsByDate(Builder builder) {
        date = builder.date;
        page = builder.page;
    }


    public static final class Builder {
        private String date;
        private int page;

        public Builder() {
        }

        public Builder date(String val) {
            date = val;
            return this;
        }

        public Builder page(int val) {
            page = val;
            return this;
        }

        public TripsByDate build() {
            return new TripsByDate(this);
        }
    }
}
