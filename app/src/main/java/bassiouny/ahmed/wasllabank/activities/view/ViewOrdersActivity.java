package bassiouny.ahmed.wasllabank.activities.view;

import android.os.Bundle;

import bassiouny.ahmed.wasllabank.R;
import bassiouny.ahmed.wasllabank.fragments.view.PastRequestsFragment;
import bassiouny.ahmed.wasllabank.utils.MyToolbar;
import bassiouny.ahmed.wasllabank.utils.MyUtils;

public class ViewOrdersActivity extends MyToolbar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);
        // set tool bar title
        initToolbar(getString(R.string.orders),true);
        // set back image button
        addBackImage();
        // set notification image button
        addNotificationImage();
        // set toolbar
        addSupportActionbar();
        // set past request fragment
        // past request fragment show two screen
            // first past requests parameter true
            // second order requests parameter false
        MyUtils.openFragment(R.id.frameLayout,this, PastRequestsFragment.getInstance(false),false,null);
    }
}
