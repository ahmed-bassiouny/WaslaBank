package bassiouny.ahmed.waslabank.activities.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.fragments.view.SplashFragment;
import bassiouny.ahmed.waslabank.utils.MyUtils;

public class AuthenticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        MyUtils.openFragment(R.id.container,this,new SplashFragment(),
                false,null);
    }
}
