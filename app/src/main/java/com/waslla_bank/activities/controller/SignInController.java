package com.waslla_bank.activities.controller;

import android.content.Context;

import com.waslla_bank.api.apiModel.requests.UserLoginRequest;
import com.waslla_bank.utils.SharedPrefKey;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import com.waslla_bank.api.ApiRequests;
import com.waslla_bank.interfaces.BaseResponseInterface;

/**
 * Created by bassiouny on 30/03/18.
 */

public class SignInController {
    private Context context;

    public SignInController(Context context) {
        this.context = context;
    }

    public void login(String phone , String password,BaseResponseInterface anInterface){
        // create UserLoginRequest Object
        UserLoginRequest.Builder builder = new UserLoginRequest.Builder();
        builder.phone(phone);
        builder.password(password);
        builder.notificationToken(SharedPrefManager.getString(SharedPrefKey.TOKEN));
        ApiRequests.login(builder.build(),anInterface);
    }
}
