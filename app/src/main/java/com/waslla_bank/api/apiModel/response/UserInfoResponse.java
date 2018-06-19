package com.waslla_bank.api.apiModel.response;

import com.waslla_bank.api.apiModel.ApiKey;
import com.waslla_bank.model.UserInfo;
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
