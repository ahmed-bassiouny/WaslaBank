package bassiouny.ahmed.waslabank.activities.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.utils.MyToolbar;

public class NotificationActivity extends MyToolbar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initToolbar(getString(R.string.notifications),true);
        addBackImage();
        addSupportActionbar();
    }
}
