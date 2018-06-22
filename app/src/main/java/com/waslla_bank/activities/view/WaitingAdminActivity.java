package com.waslla_bank.activities.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.waslla_bank.R;
import com.waslla_bank.api.ApiRequests;
import com.waslla_bank.interfaces.BaseResponseInterface;
import com.waslla_bank.model.User;
import com.waslla_bank.utils.Constant;
import com.waslla_bank.utils.MyApplication;
import com.waslla_bank.utils.SharedPrefKey;

import bassiouny.ahmed.genericmanager.SharedPrefManager;

public class WaitingAdminActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.waslla_bank.R.layout.activity_waiting_admin);
        // get current user
        user = SharedPrefManager.getObject(SharedPrefKey.USER, User.class);
        // log out
        findViewById(R.id.btn_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = SharedPrefManager.getString(SharedPrefKey.TOKEN);
                SharedPrefManager.clearSharedPref();
                startActivity(new Intent(WaitingAdminActivity.this, SplashActivity.class));
                MyApplication.setUserToken("");
                SharedPrefManager.setString(SharedPrefKey.TOKEN, token);
                finish();
            }
        });
        // check if admin approved for this user
        ApiRequests.isApproved(user.getId(), new BaseResponseInterface<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                if (integer == Constant.CHOOSE_IMAGE) {
                    startActivity(new Intent(WaitingAdminActivity.this, SignUpUserImagesActivity.class));
                } else if (integer == Constant.OPEN_HOME) {
                    startActivity(new Intent(WaitingAdminActivity.this, HomeActivity.class));
                } else {
                    return;
                }
                user.setApproved(integer);
                SharedPrefManager.setObject(SharedPrefKey.USER, user);
                finish();
            }

            @Override
            public void onFailed(String errorMessage) {
            }
        });
    }
}
