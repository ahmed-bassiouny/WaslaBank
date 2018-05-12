package com.wasllabank.fragments.controller;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.wasllabank.api.ApiRequests;
import com.wasllabank.utils.MyUtils;
import com.wasllabank.utils.SharedPrefKey;

import java.io.File;

import bassiouny.ahmed.genericmanager.SharedPrefManager;

import com.wasllabank.api.apiModel.requests.UserSignUpRequest;
import com.wasllabank.interfaces.BaseResponseInterface;

/**
 * Created by bassiouny on 30/03/18.
 */

public class SignUpUserDetailsController {

    private Context context;
    public final int MY_PERMISSIONS_REQUEST_IMAGE = 2;

    public SignUpUserDetailsController(Context context) {
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    public ArrayAdapter getCitiesAdapter() {
        // get array adapter contain cities
        return new ArrayAdapter(context, com.wasllabank.R.layout.spinner_row, context.getResources().getStringArray(com.wasllabank.R.array.cities));
    }

    public void registerUser(String identifyNumber, String gender, String city, File image, BaseResponseInterface anInterface) {
        // get user data from shared pref
        UserSignUpRequest.Builder builder = SharedPrefManager.getObject(SharedPrefKey.USER_REGISTER,UserSignUpRequest.Builder.class);
        builder.identifyNumber(identifyNumber);
        builder.gender(gender);
        builder.city(city);
        builder.notificationToken(SharedPrefManager.getString(SharedPrefKey.TOKEN));
        // save user data in shared pref
        if(image == null)
            ApiRequests.signUp(builder.build(),null, anInterface);
        else
            ApiRequests.signUp(builder.build(), MyUtils.convertFileToPart(image), anInterface);
    }
}
