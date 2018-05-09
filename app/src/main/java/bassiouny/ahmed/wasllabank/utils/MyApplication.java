package bassiouny.ahmed.wasllabank.utils;

import android.app.Application;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import bassiouny.ahmed.wasllabank.R;
import bassiouny.ahmed.wasllabank.api.ApiConfig;

import com.crashlytics.android.Crashlytics;

import bassiouny.ahmed.wasllabank.model.User;
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
        SharedPrefManager.init(this, getString(R.string.app_name));
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