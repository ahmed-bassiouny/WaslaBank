package com.waslla_bank.model;

import com.waslla_bank.api.apiModel.ApiKey;
import com.waslla_bank.utils.MyUtils;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bassiouny on 23/04/18.
 */

public class About {

    @SerializedName(ApiKey.BODY)
    private String body;

    public String getBody() {
        return MyUtils.getString(body);
    }
}
