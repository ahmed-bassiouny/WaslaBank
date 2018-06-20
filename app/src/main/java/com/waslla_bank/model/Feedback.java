package com.waslla_bank.model;

import com.waslla_bank.utils.MyUtils;
import com.google.gson.annotations.SerializedName;

import com.waslla_bank.api.apiModel.ApiKey;

/**
 * Created by bassiouny on 11/04/18.
 */

public class Feedback {

    @SerializedName(ApiKey.RATE)
    private String rate;
    @SerializedName(ApiKey.COMMENT)
    private String comment;
    @SerializedName(ApiKey.NAME)
    private String userName;
    @SerializedName(ApiKey.IMAGE)
    private String userImage;

    public float getRate() {
        try {
            return Float.parseFloat(MyUtils.getString(rate));
        }catch (Exception e){
            return 0;
        }
    }

    public String getComment() {
        return comment;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserImage() {
        return userImage;
    }
}