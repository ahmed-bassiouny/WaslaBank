package com.waslla_bank.utils;

import android.app.Application;

import bassiouny.ahmed.genericmanager.SharedPrefManager;

import com.waslla_bank.api.ApiConfig;

import com.waslla_bank.model.User;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by bassiouny on 26/03/18.
 */

public class MyApplication extends Application {
    private static String BEARER_TOKEN = "Bearer ";
    private static String USER_TOKEN = "";

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        SharedPrefManager.init(this, getString(com.waslla_bank.R.string.app_name));
        ApiConfig.initRetrofitConfig();
    }

    public static String getUserToken() {
        if (USER_TOKEN.isEmpty())
            USER_TOKEN = SharedPrefManager.getObject(SharedPrefKey.USER, User.class).getToken();
        return BEARER_TOKEN + USER_TOKEN;
    }
    public static void setUserToken(String token) {
        USER_TOKEN =  token;
    }
}
