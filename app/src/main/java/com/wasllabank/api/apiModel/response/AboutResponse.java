package com.wasllabank.api.apiModel.response;

import com.google.gson.annotations.SerializedName;

import com.wasllabank.api.apiModel.ApiKey;
import com.wasllabank.model.About;

/**
 * Created by bassiouny on 11/04/18.
 */

public class AboutResponse extends ParentResponse<About> {

    @SerializedName(ApiKey.DATA)
    private About about;

    @Override
    public About getObject() {
        return about;
    }
}
