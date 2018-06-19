package com.waslla_bank.activities.view;

import android.os.Bundle;

import com.waslla_bank.utils.MyUtils;
import com.waslla_bank.fragments.view.PastRequestsFragment;
import com.waslla_bank.utils.MyToolbar;

public class ViewOrdersActivity extends MyToolbar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.waslla_bank.R.layout.activity_view_orders);
        // set tool bar title
        initToolbar(getString(com.waslla_bank.R.string.orders),true);
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
        MyUtils.openFragment(com.waslla_bank.R.id.frameLayout,this, PastRequestsFragment.getInstance(),false,null);
    }
}
