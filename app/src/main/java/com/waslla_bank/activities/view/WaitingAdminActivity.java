package com.waslla_bank.activities.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.waslla_bank.R;
import com.waslla_bank.utils.MyApplication;
import com.waslla_bank.utils.SharedPrefKey;

import bassiouny.ahmed.genericmanager.SharedPrefManager;

public class WaitingAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.waslla_bank.R.layout.activity_waiting_admin);
        findViewById(R.id.btn_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = SharedPrefManager.getString(SharedPrefKey.TOKEN);
                SharedPrefManager.clearSharedPref();
                startActivity(new Intent(WaitingAdminActivity.this, SplashActivity.class));
                MyApplication.setUserToken("");
                SharedPrefManager.setString(SharedPrefKey.TOKEN,token);
                finish();
            }
        });
    }
}