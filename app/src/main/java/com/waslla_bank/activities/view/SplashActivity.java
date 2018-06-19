package com.waslla_bank.activities.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.waslla_bank.model.User;
import com.waslla_bank.utils.MyUtils;
import com.waslla_bank.utils.SharedPrefKey;

import bassiouny.ahmed.genericmanager.SharedPrefManager;

public class SplashActivity extends AppCompatActivity {

    Button btnSignIn,btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.waslla_bank.R.layout.activity_splash);
        findView();
        onClick();
        checkUserAccess();
    }

    private void findView() {
        btnSignIn = findViewById(com.waslla_bank.R.id.btn_sign_in);
        btnSignUp = findViewById(com.waslla_bank.R.id.btn_sign_up);
    }

    private void onClick() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashActivity.this,SignInActivity.class));
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashActivity.this,SignUpActivity.class));
            }
        });
    }
    private void checkUserAccess(){
        User user = SharedPrefManager.getObject(SharedPrefKey.USER,User.class);
        if(user != null){
            MyUtils.openHomeScreen(SplashActivity.this,user);
            finish();
        }
    }
}
