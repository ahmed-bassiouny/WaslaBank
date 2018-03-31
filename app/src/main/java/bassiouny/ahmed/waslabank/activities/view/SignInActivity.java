package bassiouny.ahmed.waslabank.activities.view;

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.activities.controller.SignInController;
import bassiouny.ahmed.waslabank.api.apiModel.ParentResponse;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.waslabank.utils.SlideAnimationUtil;

public class SignInActivity extends AppCompatActivity {


    // view variable
    private EditText etPhone;
    private EditText etPassword;
    private Button btnSignIn;
    private ProgressBar progress;

    // local variable
    private SignInController signInController;
    //----------------------------------------------
    // methods auto generated

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        findView();
        onClick();
    }


    // ----------------------------------------------------
    // generate method by me
    private void onClick() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPhone.getText().toString().length() != 11) {
                    etPhone.setError(getString(R.string.invalid_phone_lenght));
                } else if (etPassword.getText().toString().length() < 6) {
                    etPassword.setError(getString(R.string.invalid_password_lenght));
                } else {
                    // start login process
                    loading(true);
                    getController().login(etPhone.getText().toString(),
                            etPassword.getText().toString(), new BaseResponseInterface() {
                                @Override
                                public void onSuccess(Object o) {
                                    Toast.makeText(SignInActivity.this, "Done", Toast.LENGTH_SHORT).show();
                                    loading(false);
                                }

                                @Override
                                public void onFailed(String errorMessage) {
                                    Toast.makeText(SignInActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                                    loading(false);

                                }
                            });
                }
            }
        });
    }

    private void findView() {
        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        btnSignIn = findViewById(R.id.btn_sign_in);
        progress = findViewById(R.id.progress);
    }

    private SignInController getController() {
        if (signInController == null)
            signInController = new SignInController(this);
        return signInController;
    }

    // start loading
    // todo i will remove this method
    private void loading(boolean start){
        if(start){
            progress.setVisibility(View.VISIBLE);
            btnSignIn.setEnabled(false);
        }else {
            progress.setVisibility(View.INVISIBLE);
            btnSignIn.setEnabled(true);
        }
    }
}

