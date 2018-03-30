package bassiouny.ahmed.waslabank.activities.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.utils.SlideAnimationUtil;

public class SignInActivity extends AppCompatActivity {


    private EditText etPhone;
    private EditText etPassword;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        findView();
        onClick();
    }

    private void onClick() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void findView() {

        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        btnSignIn = findViewById(R.id.btn_sign_in);
        //SlideAnimationUtil.slideInFromRight(this, etPhone);
        //SlideAnimationUtil.slideInFromRight(this, etPassword);
        //SlideAnimationUtil.slideInFromRight(this, btnSignIn);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
