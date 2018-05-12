package com.wasllabank.api.apiModel.response;

import com.wasllabank.model.User;
import com.google.gson.annotations.SerializedName;

import com.wasllabank.api.apiModel.ApiKey;

/**
 * Created by bassiouny on 31/03/18.
 */

public class UserResponse extends ParentResponse<User>{

    @SerializedName(ApiKey.DATA)
    private User user;

    @Override
    public User getObject() {
        if(user == null)
            user = new User();
        return user;
    }
}
