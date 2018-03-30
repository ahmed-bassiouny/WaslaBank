package bassiouny.ahmed.waslabank.utils;

import android.app.Application;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.api.ApiConfig;

/**
 * Created by bassiouny on 26/03/18.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPrefManager.init(this,getString(R.string.app_name));
        ApiConfig.initRetrofitConfig();
    }
}
