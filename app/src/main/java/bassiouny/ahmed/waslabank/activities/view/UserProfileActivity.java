package bassiouny.ahmed.waslabank.activities.view;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.api.ApiRequests;
import bassiouny.ahmed.waslabank.fragments.view.AboutFragment;
import bassiouny.ahmed.waslabank.fragments.view.FeedbackFragment;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.waslabank.interfaces.MyObserverInterface;
import bassiouny.ahmed.waslabank.interfaces.ObserverInterface;
import bassiouny.ahmed.waslabank.model.User;
import bassiouny.ahmed.waslabank.model.UserInfo;
import bassiouny.ahmed.waslabank.utils.MyGlideApp;
import bassiouny.ahmed.waslabank.utils.MyToolbar;
import bassiouny.ahmed.waslabank.utils.SharedPrefKey;
import de.hdodenhof.circleimageview.CircleImageView;

// this activity view my profile
// view profile user use app
public class UserProfileActivity extends MyToolbar implements MyObserverInterface<UserInfo> {

    // view
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private CircleImageView ivAvatar;
    private ImageView imgCover;
    private TextView tvUserName;
    private RatingBar rating;
    private TextView tvRequests;
    private TextView tvPoint;
    private TextView tvOrder;
    private ViewStub viewStubProgress;
    private UserInfo userInfo;
    private User user;
    // local variable
    private List<ObserverInterface> observerInterfaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        // set tool bar title
        initToolbar("",false);
        // set back image button
        addBackImage();
        // set notification image button
        addNotificationImage();
        // set edit icon
        addView(editImage(), layoutParamEditIcon());
        // set toolbar
        addSupportActionbar();
        // find view by id
        findView();
        // init objects
        initObjects();
        // set user information
        // get total rate , point , order from user information object
        userInfo = SharedPrefManager.getObject(SharedPrefKey.USER_INFO, UserInfo.class);
        setUserInformation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // set user data
        // get name and image from user object
        user = SharedPrefManager.getObject(SharedPrefKey.USER, User.class);
        setUserData();
        // load user information with feedback from server
        // refresh feedback
        loadUserInfo();
    }

    private void initObjects() {
        observerInterfaces = new ArrayList<>();
    }

    private void findView() {
        ivAvatar = findViewById(R.id.iv_avatar);
        tvUserName = findViewById(R.id.tv_user_name);
        rating = findViewById(R.id.rating);
        tvRequests = findViewById(R.id.tv_requests);
        tvPoint = findViewById(R.id.tv_point);
        tvOrder = findViewById(R.id.tv_order);
        viewStubProgress = findViewById(R.id.view_stub_progress);
        imgCover = findViewById(R.id.img_cover);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setOffscreenPageLimit(2);
        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }

    @Override
    public void register(ObserverInterface observer) {
        observerInterfaces.add(observer);
    }

    @Override
    public void unregister(ObserverInterface observer) {
        observerInterfaces.remove(observer);
    }

    @Override
    public void notifyObservers(UserInfo userInfo) {
        if (observerInterfaces.size() == 0)
            return;
        // AboutFragment ==> user object
        observerInterfaces.get(0).update(user);
        if (userInfo != null)
            // FeedbackFragment ==> feedback list object
            observerInterfaces.get(1).update(userInfo.getFeedbacks());
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
                    register(AboutFragment.getInstance());
                    return AboutFragment.getInstance();
                case 1:
                    register(FeedbackFragment.getInstance());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregister(AboutFragment.getInstance());
        unregister(FeedbackFragment.getInstance());
    }

    // set user information in layout
    private void setUserInformation() {
        if (userInfo == null)
            return;
        tvRequests.setText(String.valueOf(userInfo.getRequests()));
        tvPoint.setText(String.valueOf(userInfo.getPoint()));
        tvOrder.setText(String.valueOf(userInfo.getOrders()));
        rating.setRating(userInfo.getRate());
    }

    // set user data in layout
    private void setUserData() {
        // get user data
        if (user == null)
            return;
        tvUserName.setText(user.getName());
        MyGlideApp.setImageCenterInside(this, ivAvatar, user.getUserDetails().getImage());
        MyGlideApp.setImageCenterCrop(this, imgCover, user.getUserDetails().getImage());
    }

    private void loadUserInfo() {
        loading(true);
        ApiRequests.getUserInfoWithFeedback(new BaseResponseInterface<UserInfo>() {
            @Override
            public void onSuccess(UserInfo userInfo) {
                // refresh user information
                setUserInformation();
                // set user data in about fragment
                // set feedback to fragment
                notifyObservers(userInfo);
                // create new object to save user info without feedback
                SharedPrefManager.setObject(SharedPrefKey.USER_INFO, new UserInfo(userInfo));
                loading(false);
            }

            @Override
            public void onFailed(String errorMessage) {
                // set user data in about fragments
                notifyObservers(null);
                Toast.makeText(UserProfileActivity.this, "Sorry we can\'t load feedback", Toast.LENGTH_SHORT).show();
                loading(false);
            }
        });
    }

    private void loading(boolean isLoading) {
        if (isLoading) {
            viewStubProgress.setVisibility(View.VISIBLE);
            mViewPager.setVisibility(View.INVISIBLE);
        } else {
            viewStubProgress.setVisibility(View.INVISIBLE);
            mViewPager.setVisibility(View.VISIBLE);
        }
    }

    private ImageView editImage() {

        // create back image view
        ImageView edit = new ImageView(this);
        // back image view src
        edit.setImageDrawable(getResources().getDrawable(R.drawable.ic_edit));
        // add tint mode
        edit.setColorFilter(Color.argb(255, 255, 255, 255));
        // handle click item
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserProfileActivity.this, EditProfileActivity.class));
            }
        });
        return edit;

    }

    private FrameLayout.LayoutParams layoutParamEditIcon() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.END);
        params.setMargins(10, 10, 150, 10);
        return params;
    }

}
