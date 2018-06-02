package com.wasllabank.activities.view;

import android.os.Bundle;

import com.wasllabank.utils.MyUtils;
import com.wasllabank.fragments.view.PastRequestsFragment;
import com.wasllabank.utils.MyToolbar;

public class ViewOrdersActivity extends MyToolbar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.wasllabank.R.layout.activity_view_orders);
        // set tool bar title
        initToolbar(getString(com.wasllabank.R.string.orders),true);
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
        MyUtils.openFragment(com.wasllabank.R.id.frameLayout,this, PastRequestsFragment.getInstance(false),false,null);
    }
}
