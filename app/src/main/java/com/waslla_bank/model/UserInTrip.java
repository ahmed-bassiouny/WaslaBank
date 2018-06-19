package com.waslla_bank.model;

import com.waslla_bank.utils.MyUtils;
import com.google.gson.annotations.SerializedName;

import com.waslla_bank.api.apiModel.ApiKey;

/**
 * Created by bassiouny on 01/05/18.
 */
@SuppressWarnings({"UnusedDeclaration"})
public class UserInTrip {

    @SerializedName(ApiKey.USER_ID)
    private int userId;
    @SerializedName(ApiKey.REQUEST_ID)
    private int requestId;
    @SerializedName(ApiKey.NAME)
    private String userName;
    @SerializedName(ApiKey.PHONE)
    private String userPhone;
    @SerializedName(ApiKey.IMAGE)
    private String userImage;
    @SerializedName(ApiKey.IS_ACCEPTED)
    private boolean isAccepted;
    @SerializedName(ApiKey.IS_ENTERED)
    private boolean isEntered;
    private boolean loading;

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return MyUtils.getString(userName);
    }

    public String getUserPhone() {
        return MyUtils.getString(userPhone);
    }

    public String getUserImage() {
        return MyUtils.getString(userImage);
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted() {
        isAccepted = true;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public int getRequestId() {
        return requestId;
    }

    public boolean getIsEntered() {
        return isEntered;
    }

    public void setIsEntered() {
        this.isEntered = true;
    }
}
