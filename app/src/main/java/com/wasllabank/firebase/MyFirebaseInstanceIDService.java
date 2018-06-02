package com.wasllabank.firebase;

import android.util.Log;

import com.wasllabank.utils.SharedPrefKey;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import bassiouny.ahmed.genericmanager.SharedPrefManager;

/**
 * Created by bassiouny on 25/04/18.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        if (refreshedToken != null)
            SharedPrefManager.setString(SharedPrefKey.TOKEN, refreshedToken);
        Log.e("onTokenRefresh: ", refreshedToken);

    }
}
