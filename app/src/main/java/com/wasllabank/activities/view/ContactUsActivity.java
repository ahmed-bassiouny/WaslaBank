package com.wasllabank.activities.view;

import android.support.design.widget.TextInputEditText;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.Toast;

import com.wasllabank.api.ApiRequests;
import com.wasllabank.api.apiModel.requests.ContactUsRequest;
import com.wasllabank.interfaces.BaseResponseInterface;
import com.wasllabank.model.User;
import com.wasllabank.utils.MyToolbar;
import com.wasllabank.utils.SharedPrefKey;

import bassiouny.ahmed.genericmanager.SharedPrefManager;

public class ContactUsActivity extends MyToolbar {

    private TextInputEditText etName;
    private TextInputEditText etPhone;
    private TextInputEditText etSubject;
    private TextInputEditText etMessage;
    private Button btnSend;
    private ViewStub viewStubProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.wasllabank.R.layout.activity_contact_us);
        initToolbar(getString(com.wasllabank.R.string.contact_us),true);
        addBackImage();
        addNotificationImage();
        addSupportActionbar();
        findView();
        onClick();
        getUserDataToSetInLayout();
    }

    private void getUserDataToSetInLayout() {
        User user = SharedPrefManager.getObject(SharedPrefKey.USER,User.class);
        etName.setText(user.getName());
        etPhone.setText(user.getPhone());
    }

    private void onClick() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etName.getText().toString().isEmpty()) {
                    etName.setError(getString(com.wasllabank.R.string.invalid_name));
                } else if (etPhone.getText().toString().length() != 11) {
                    etPhone.setError(getString(com.wasllabank.R.string.invalid_phone_length));
                } else if (etSubject.getText().toString().isEmpty()) {
                    etSubject.setError(getString(com.wasllabank.R.string.invalid_subject));
                } else if (etMessage.getText().toString().isEmpty()) {
                    etMessage.setError(getString(com.wasllabank.R.string.invalid_message));
                } else {
                    loading(true);
                    // create contact us request object
                    ContactUsRequest.Builder contactUsRequest = new ContactUsRequest.Builder();
                    contactUsRequest.userId(SharedPrefManager.getObject(SharedPrefKey.USER, User.class).getId());
                    contactUsRequest.name(etName.getText().toString());
                    contactUsRequest.phone(etPhone.getText().toString());
                    contactUsRequest.subject(etSubject.getText().toString());
                    contactUsRequest.message(etMessage.getText().toString());
                    // send data to server
                    ApiRequests.contactUs(contactUsRequest.build(), new BaseResponseInterface() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(ContactUsActivity.this, getString(com.wasllabank.R.string.thanks), Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailed(String errorMessage) {
                            Toast.makeText(ContactUsActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                            loading(false);
                        }
                    });
                }
            }
        });
    }

    private void loading(boolean isLoading) {
        if (isLoading) {
            viewStubProgress.setVisibility(View.VISIBLE);
            btnSend.setVisibility(View.GONE);
        } else {
            viewStubProgress.setVisibility(View.GONE);
            btnSend.setVisibility(View.VISIBLE);
        }
    }

    private void findView() {
        etName = findViewById(com.wasllabank.R.id.et_name);
        etPhone = findViewById(com.wasllabank.R.id.et_phone);
        etSubject = findViewById(com.wasllabank.R.id.et_subject);
        etMessage = findViewById(com.wasllabank.R.id.et_message);
        btnSend = findViewById(com.wasllabank.R.id.btn_send);
        viewStubProgress = findViewById(com.wasllabank.R.id.view_stub_progress);
    }
}
