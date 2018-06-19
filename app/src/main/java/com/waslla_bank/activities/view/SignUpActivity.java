package com.waslla_bank.activities.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.waslla_bank.fragments.view.SignUpFragment;
import com.waslla_bank.utils.MyUtils;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.waslla_bank.R.layout.activity_sign_up);
        MyUtils.openFragment(com.waslla_bank.R.id.container,this,new SignUpFragment(),false,null);
        onClick();
    }

    private void onClick() {
        findViewById(com.waslla_bank.R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
