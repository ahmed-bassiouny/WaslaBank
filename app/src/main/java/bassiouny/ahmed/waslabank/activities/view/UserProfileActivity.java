package bassiouny.ahmed.waslabank.activities.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.utils.MyToolbar;

// this activity view my profile
// view profile user use app
public class UserProfileActivity extends MyToolbar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        initToolbar("Profile");
        addBackImage();
        addNotificationImage();
        addSupportActionbar();
    }
}
