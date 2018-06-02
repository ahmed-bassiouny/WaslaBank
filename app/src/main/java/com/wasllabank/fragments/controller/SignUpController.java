package com.wasllabank.fragments.controller;

import com.wasllabank.utils.SharedPrefKey;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import com.wasllabank.api.ApiRequests;
import com.wasllabank.api.apiModel.requests.UserSignUpRequest;
import com.wasllabank.interfaces.BaseResponseInterface;

/**
 * Created by bassiouny on 30/03/18.
 */

public class SignUpController {

    public void saveUserObject(String name,String phone,String email,String password){
        // save user data in shared pref
        UserSignUpRequest.Builder builder = new UserSignUpRequest.Builder();
        builder.name(name);
        builder.phone(phone);
        builder.email(email);
        builder.password(password);
        SharedPrefManager.setObject(SharedPrefKey.USER_REGISTER,builder);
    }
    public void checkEmailAndPhone(String email , String phone , BaseResponseInterface anInterface){
        UserSignUpRequest.Builder builder = new UserSignUpRequest.Builder();
        builder.email(email);
        builder.phone(phone);
        ApiRequests.checkEmailAndPhone(builder.build(),anInterface);
    }
}
