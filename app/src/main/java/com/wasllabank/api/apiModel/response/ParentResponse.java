package com.wasllabank.api.apiModel.response;

import android.support.annotation.NonNull;

import com.wasllabank.utils.MyUtils;
import com.google.gson.annotations.SerializedName;

import com.wasllabank.api.apiModel.ApiKey;

/**
 * Created by bassiouny on 26/03/18.
 */

public abstract class ParentResponse <T>{

    @SerializedName(ApiKey.STATUS)
    @SuppressWarnings({"UnusedDeclaration"})
    private boolean status;
    @SerializedName(ApiKey.MESSAGE)
    @SuppressWarnings({"UnusedDeclaration"})
    private String message;

    public boolean getStatus() {
        return status;
    }
    @NonNull
    public String getMessage() {
        return MyUtils.getString(message);
    }

    public abstract T getObject();
}
