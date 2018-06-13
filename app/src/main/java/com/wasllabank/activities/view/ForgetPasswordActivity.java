package com.wasllabank.activities.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.Toast;

import com.wasllabank.R;
import com.wasllabank.api.ApiRequests;
import com.wasllabank.interfaces.BaseResponseInterface;

public class ForgetPasswordActivity extends AppCompatActivity {

    private TextInputEditText etEmail;
    private Button btnSend;
    private ViewStub viewStubProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        findView();
        onClick();
    }

    private void onClick() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etEmail.getText().toString().trim().isEmpty()){
                    etEmail.setError(getString(R.string.invalid_email));
                }else {
                    // send request
                    btnSend.setVisibility(View.GONE);
                    viewStubProgress.setVisibility(View.VISIBLE);
                    ApiRequests.forgetPassword(etEmail.getText().toString(), new BaseResponseInterface<String>() {
                        @Override
                        public void onSuccess(String s) {
                            finishRequest(s);
                            finish();
                        }

                        @Override
                        public void onFailed(String errorMessage) {
                            finishRequest(errorMessage);
                        }
                    });
                }
            }
        });
    }


    public void findView() {
        etEmail = findViewById(R.id.et_email);
        btnSend = findViewById(R.id.btn_send);
        viewStubProgress = findViewById(R.id.view_stub_progress);
    }
    public void finishRequest(String msg){
        btnSend.setVisibility(View.VISIBLE);
        viewStubProgress.setVisibility(View.GONE);
        Toast.makeText(ForgetPasswordActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

}
