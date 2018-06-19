package com.waslla_bank.api.apiModel.response;

import com.google.gson.annotations.SerializedName;

import com.waslla_bank.api.apiModel.ApiKey;
import com.waslla_bank.model.About;

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
