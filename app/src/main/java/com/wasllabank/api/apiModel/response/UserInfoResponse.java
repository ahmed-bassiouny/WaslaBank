package com.wasllabank.api.apiModel.response;

import com.wasllabank.api.apiModel.ApiKey;
import com.wasllabank.model.UserInfo;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bassiouny on 12/04/18.
 */

public class UserInfoResponse extends ParentResponse<UserInfo> {

    @SerializedName(ApiKey.DATA)
    private UserInfo userInfo;

    @Override
    public UserInfo getObject() {
        if (userInfo == null)
            userInfo = new UserInfo();
        return userInfo;
    }
}
