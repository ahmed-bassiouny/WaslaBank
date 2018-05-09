package bassiouny.ahmed.wasllabank.activities.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import bassiouny.ahmed.wasllabank.R;
import bassiouny.ahmed.wasllabank.fragments.view.SignUpFragment;
import bassiouny.ahmed.wasllabank.utils.MyUtils;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        MyUtils.openFragment(R.id.container,this,new SignUpFragment(),false,null);
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
}