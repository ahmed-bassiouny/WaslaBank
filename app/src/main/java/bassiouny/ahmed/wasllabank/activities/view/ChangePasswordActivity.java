package bassiouny.ahmed.wasllabank.activities.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.Toast;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import bassiouny.ahmed.wasllabank.R;
import bassiouny.ahmed.wasllabank.api.ApiRequests;
import bassiouny.ahmed.wasllabank.api.apiModel.requests.ChangePasswordRequest;
import bassiouny.ahmed.wasllabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.wasllabank.model.User;
import bassiouny.ahmed.wasllabank.utils.SharedPrefKey;

public class ChangePasswordActivity extends AppCompatActivity {


    private TextInputEditText etCurrentPassword;
    private TextInputEditText etPassword;
    private TextInputEditText etConfirmPassword;
    private Button btnUpdate;
    private ViewStub viewStubProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        findView();
        onClick();
    }

    private void onClick() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etCurrentPassword.getText().toString().length() < 6)
                    etCurrentPassword.setError(getString(R.string.invalid_password_length));
                else if (etPassword.getText().toString().length() < 6)
                    etPassword.setError(getString(R.string.invalid_password_length));
                else if (!etPassword.getText().toString().equals(etConfirmPassword.getText().toString()))
                    etConfirmPassword.setError(getString(R.string.invalid_confirm));
                else {
                    viewStubProgress.setVisibility(View.VISIBLE);
                    btnUpdate.setVisibility(View.GONE);
                    // create change password object
                    ChangePasswordRequest object = new ChangePasswordRequest(SharedPrefManager.getObject(SharedPrefKey.USER, User.class).getId()
                    ,etCurrentPassword.getText().toString(),etPassword.getText().toString());
                    ApiRequests.changePassword(object, new BaseResponseInterface() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(ChangePasswordActivity.this, "Your Password Updated", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailed(String errorMessage) {
                            Toast.makeText(ChangePasswordActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                            viewStubProgress.setVisibility(View.GONE);
                            btnUpdate.setVisibility(View.VISIBLE);
                        }
                    });
                }
            }
        });
    }

    private void findView() {
        etCurrentPassword = findViewById(R.id.et_current_password);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btnUpdate = findViewById(R.id.btn_update);
        viewStubProgress = findViewById(R.id.view_stub_progress);
    }
}
