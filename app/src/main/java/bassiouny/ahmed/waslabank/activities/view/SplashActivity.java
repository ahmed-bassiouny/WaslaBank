package bassiouny.ahmed.waslabank.activities.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.model.User;
import bassiouny.ahmed.waslabank.utils.SharedPrefKey;
import bassiouny.ahmed.waslabank.utils.SlideAnimationUtil;

public class SplashActivity extends AppCompatActivity {

    Button btnSignIn,btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        findView();
        onClick();
        checkUserLoged();
    }

    private void findView() {
        btnSignIn = findViewById(R.id.btn_sign_in);
        btnSignUp = findViewById(R.id.btn_sign_up);
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
    private void checkUserLoged(){
        User user = SharedPrefManager.getObject(SharedPrefKey.USER,User.class);
        if(user != null){
            Intent intent = new Intent(SplashActivity.this,WaitingAdminActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
