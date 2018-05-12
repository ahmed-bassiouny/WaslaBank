package com.wasllabank.activities.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wasllabank.fragments.view.SignUpFragment;
import com.wasllabank.utils.MyUtils;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.wasllabank.R.layout.activity_sign_up);
        MyUtils.openFragment(com.wasllabank.R.id.container,this,new SignUpFragment(),false,null);
        onClick();
    }

    private void onClick() {
        findViewById(com.wasllabank.R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
