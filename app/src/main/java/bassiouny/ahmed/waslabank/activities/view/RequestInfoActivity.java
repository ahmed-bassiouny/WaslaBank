package bassiouny.ahmed.waslabank.activities.view;

import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import android.view.ViewStub;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.activities.controller.RequestInfoController;
import bassiouny.ahmed.waslabank.fragments.view.AboutDriverFragment;
import bassiouny.ahmed.waslabank.fragments.view.FeedbackFragment;
import bassiouny.ahmed.waslabank.fragments.view.TripDetailsFragment;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.waslabank.interfaces.MyObserverInterface;
import bassiouny.ahmed.waslabank.interfaces.ObserverInterface;
import bassiouny.ahmed.waslabank.model.TripDetails;
import bassiouny.ahmed.waslabank.utils.MyToolbar;

public class RequestInfoActivity extends MyToolbar implements MyObserverInterface<TripDetails> {

    // view
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private ViewStub viewStubProgress;
    private TabLayout tabLayout;


    // local variable
    private int tripId;
    private RequestInfoController controller;
    private List<ObserverInterface> observerInterfaces;
    private TripDetails tripDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_info);
        initToolbar(getString(R.string.request), true);
        addBackImage();
        addNotificationImage();
        addSupportActionbar();
        findView();
        initObjects();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (tripDetails != null)
            notifyObservers(tripDetails);
    }

    private void initObjects() {
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);


        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        observerInterfaces = new ArrayList<>();
        // get trip id from intent
        tripId = getIntent().getIntExtra("TRIP_ID", 0);
        if (tripId > 0)
            getTripDetails();
    }

    private void findView() {
        mViewPager = findViewById(R.id.container);
        viewStubProgress = findViewById(R.id.view_stub_progress);
        tabLayout = findViewById(R.id.tabs);

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
    public void notifyObservers(TripDetails tripDetails) {
        if (observerInterfaces.size() == 0)
            return;
        // TripDetailsFragment ==> trip details object
        observerInterfaces.get(0).update(tripDetails);
        // AboutDriverFragment ==> user object
        observerInterfaces.get(1).update(tripDetails.getDriver());
        // FeedbackFragment ==> feedback list object
        observerInterfaces.get(2).update(tripDetails.getFeedbacks());
    }


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
                    register(TripDetailsFragment.getInstance());
                    return TripDetailsFragment.getInstance();
                case 1:
                    register(AboutDriverFragment.getInstance());
                    return AboutDriverFragment.getInstance();
                case 2:
                    register(FeedbackFragment.getInstance());
                    return FeedbackFragment.getInstance();

            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }

    private RequestInfoController getController() {
        if (controller == null)
            controller = new RequestInfoController(this);
        return controller;
    }

    private void getTripDetails() {
        loading(true);
        getController().getTripRequestById(tripId, new BaseResponseInterface<TripDetails>() {
            @Override
            public void onSuccess(TripDetails tripDetails) {
                //parsingInterface.parseObject(true);
                loading(false);
                notifyObservers(tripDetails);
                RequestInfoActivity.this.tripDetails = tripDetails;
            }

            @Override
            public void onFailed(String errorMessage) {
                viewStubProgress.setVisibility(View.INVISIBLE);
                Toast.makeText(RequestInfoActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loading(boolean isLoading) {
        if (isLoading) {
            viewStubProgress.setVisibility(View.VISIBLE);
            tabLayout.setVisibility(View.INVISIBLE);
            mViewPager.setVisibility(View.INVISIBLE);
        } else {
            viewStubProgress.setVisibility(View.INVISIBLE);
            tabLayout.setVisibility(View.VISIBLE);
            mViewPager.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregister(TripDetailsFragment.getInstance());
        unregister(AboutDriverFragment.getInstance());
        unregister(FeedbackFragment.getInstance());
    }
}
