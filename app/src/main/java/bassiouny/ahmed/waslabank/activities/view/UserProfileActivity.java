package bassiouny.ahmed.waslabank.activities.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.fragments.view.AboutFragment;
import bassiouny.ahmed.waslabank.fragments.view.FeedbackFragment;
import bassiouny.ahmed.waslabank.model.UserInfo;
import bassiouny.ahmed.waslabank.utils.MyToolbar;
import bassiouny.ahmed.waslabank.utils.SharedPrefKey;
import de.hdodenhof.circleimageview.CircleImageView;

// this activity view my profile
// view profile user use app
public class UserProfileActivity extends MyToolbar {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private CircleImageView ivAvatar;
    private TextView tvUserName;
    private RatingBar rating;
    private TextView tvRequests;
    private TextView tvPoint;
    private TextView tvOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        // set tool bar title
        initToolbar(getString(R.string.profile));
        // set back image button
        addBackImage();
        // set notification image button
        addNotificationImage();
        // set toolbar
        addSupportActionbar();
        // find view by id
        findView();
        // set user data information
        setUserData();

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    private void findView() {
        ivAvatar = findViewById(R.id.iv_avatar);
        tvUserName = findViewById(R.id.tv_user_name);
        rating = findViewById(R.id.rating);
        tvRequests = findViewById(R.id.tv_requests);
        tvPoint = findViewById(R.id.tv_point);
        tvOrder = findViewById(R.id.tv_order);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return AboutFragment.getInstance();
                case 1:
                    return FeedbackFragment.getInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }

    // set user information in layout
    private void setUserData() {
        UserInfo userInfo = SharedPrefManager.getObject(SharedPrefKey.USER_INFO, UserInfo.class);
        if (userInfo == null)
            return;
        tvRequests.setText(String.valueOf(userInfo.getRequests())+"\n"+getString(R.string.requests));
        tvPoint.setText(String.valueOf(userInfo.getPoint())+"\n"+getString(R.string.points));
        tvOrder.setText(String.valueOf(userInfo.getOrders())+"\n"+getString(R.string.orders));
        rating.setRating(userInfo.getRate());
    }

}
