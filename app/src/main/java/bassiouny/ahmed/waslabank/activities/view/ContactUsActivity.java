package bassiouny.ahmed.waslabank.activities.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.utils.MyToolbar;

public class ContactUsActivity extends MyToolbar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        initToolbar(getString(R.string.contact_us));
        addBackImage();
        addNotificationImage();
        addSupportActionbar();
    }
}
