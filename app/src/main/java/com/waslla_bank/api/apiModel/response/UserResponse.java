package com.waslla_bank.api.apiModel.response;

import com.waslla_bank.model.User;
import com.google.gson.annotations.SerializedName;

import com.waslla_bank.api.apiModel.ApiKey;

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
