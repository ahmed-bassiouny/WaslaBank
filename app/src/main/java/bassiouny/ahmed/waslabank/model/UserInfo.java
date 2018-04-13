package bassiouny.ahmed.waslabank.model;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;

/**
 * Created by bassiouny on 12/04/18.
 */

// this class represent user data view in home screen
public class UserInfo {

    @SerializedName(ApiKey.TOTAL_POINT)
    private int point;
    @SerializedName(ApiKey.TOTAL_REQUESTS)
    private int requests;
    @SerializedName(ApiKey.TOTAL_USERS)
    private int orders;
    @SerializedName(ApiKey.TOTAL_RATE)
    private float rate;

    public int getPoint() {
        return point;
    }

    public int getRequests() {
        return requests;
    }

    public int getOrders() {
        return orders;
    }

    public float getRate() {
        return rate;
    }
}
