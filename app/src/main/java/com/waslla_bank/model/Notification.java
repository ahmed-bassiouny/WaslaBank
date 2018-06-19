package com.waslla_bank.model;

import com.waslla_bank.utils.MyUtils;
import com.google.gson.annotations.SerializedName;

import com.waslla_bank.api.apiModel.ApiKey;

/**
 * Created by bassiouny on 24/04/18.
 */

public class Notification {

    @SerializedName(ApiKey.FROM_USER_NAME)
    private String name;
    @SerializedName(ApiKey.FROM_USER_IAMGE)
    private String image;
    @SerializedName(ApiKey.TITLE)
    private String title;
    @SerializedName(ApiKey.BODY)
    private String body;
    @SerializedName(ApiKey.TIME)
    private String time;

    public String getName() {
        return MyUtils.getString(name);
    }

    public String getImage() {
        return MyUtils.getString(image);
    }

    public String getBody() {
        return MyUtils.getString(body);
    }

    public String getTime() {
        return MyUtils.getString(time);
    }
}
