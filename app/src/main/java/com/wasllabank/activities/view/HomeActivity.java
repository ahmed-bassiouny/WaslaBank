package com.wasllabank.activities.view;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wasllabank.R;
import com.wasllabank.adapter.HomeMenuItem;
import com.wasllabank.api.ApiRequests;
import com.wasllabank.interfaces.ItemClickInterface;
import com.wasllabank.model.User;
import com.wasllabank.model.UserInfo;
import com.wasllabank.utils.MyApplication;
import com.wasllabank.utils.SharedPrefKey;

import bassiouny.ahmed.genericmanager.SharedPrefManager;

import com.wasllabank.interfaces.BaseResponseInterface;
import com.wasllabank.utils.MyToolbar;

public class HomeActivity extends MyToolbar implements ItemClickInterface {

    // view
    private RecyclerView recyclerView;
    private TextView tvPoint, tvRequests, tvOrders;
    private RatingBar rating;
    private Button btnCurrentRequest;
    //local variable
    private int[] menuImages;
    private String[] menuStrings;
    private int currentRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.wasllabank.R.layout.activity_home);
        // add toolbar title
        initToolbar("", false);
        // add profile image button
        addProfileImage();
        // add notification image button
        addNotificationImage();
        // set tool bar
        addSupportActionbar();
        findView();
        initObject();
        onClick();
    }

    private void onClick() {
        findViewById(com.wasllabank.R.id.linear_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,ViewOrdersActivity.class));
            }
        });
        btnCurrentRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentRequest == 0)
                    return;
                Intent intent = new Intent(HomeActivity.this,RequestInfoActivity.class);
                intent.putExtra("TRIP_ID",currentRequest);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // get user information from shared pref
        setUserData(SharedPrefManager.getObject(SharedPrefKey.USER_INFO, UserInfo.class));
        // refresh user information
        loadUserInformation();
        // set user name in toolbar in center
        updateTitle(SharedPrefManager.getObject(SharedPrefKey.USER, User.class).getName());
    }

    // set user information in layout
    private void setUserData(UserInfo userInfo) {
        if (userInfo == null)
            userInfo = new UserInfo();
        tvRequests.setText(String.valueOf(userInfo.getRequests()));
        tvPoint.setText(String.valueOf(userInfo.getPoint()));
        tvOrders.setText(String.valueOf(userInfo.getOrders()));
        rating.setRating(userInfo.getRate());
    }

    private void initObject() {
        // set menu image array
        menuImages = new int[]{com.wasllabank.R.drawable.car_plus, com.wasllabank.R.drawable.car, com.wasllabank.R.drawable.contactus, com.wasllabank.R.drawable.about, com.wasllabank.R.drawable.logout};
        // set menu string array
        menuStrings = getResources().getStringArray(com.wasllabank.R.array.menu);
        // check image and string array
        // if not equal throw exception to make app crush
        if (menuImages.length != menuStrings.length)
            throw new RuntimeException("Image Array Not Equal String Array");
        // set item menu in recycler view
        setHomeMenu();
    }

    private void findView() {
        recyclerView = findViewById(com.wasllabank.R.id.recycler);
        tvPoint = findViewById(com.wasllabank.R.id.tv_point);
        tvRequests = findViewById(com.wasllabank.R.id.tv_requests);
        tvOrders = findViewById(com.wasllabank.R.id.tv_order);
        rating = findViewById(com.wasllabank.R.id.rating);
        btnCurrentRequest = findViewById(R.id.btn_current_request);
    }

    /*
    * this method create menu string and image
    * create layout manager
    * create adapter
    * */
    private void setHomeMenu() {
        // create gridlayout manager
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        // create span size
        // if last row is one item make it in middle
        // if last row is two item make it in normal
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (menuImages.length % 2 != 0) {
                    return (position == menuImages.length - 1) ? 2 : 1;
                } else {
                    return 1;
                }
            }
        });
        // create adapter
        HomeMenuItem adapter = new HomeMenuItem(this, menuStrings, menuImages);
        // set layout manager
        recyclerView.setLayoutManager(manager);
        // set adapter
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void getItem(@Nullable Object o, int position) {
        switch (position) {
            // create services
            case 0:
                startActivity(new Intent(HomeActivity.this, CreateRequestActivity.class));
                break;
            // all services
            case 1:
                startActivity(new Intent(HomeActivity.this, RequestsActivity.class));
                break;
            // contact us
            case 2:
                startActivity(new Intent(HomeActivity.this, ContactUsActivity.class));
                break;
            // about
            case 3:
                startActivity(new Intent(HomeActivity.this, AboutUsActivity.class));
                break;
            // log out
            case 4:
                // save token in variable to clear shared pref
                // after clear it save token in shared pref
                String token = SharedPrefManager.getString(SharedPrefKey.TOKEN);
                SharedPrefManager.clearSharedPref();
                startActivity(new Intent(HomeActivity.this, SplashActivity.class));
                MyApplication.setUserToken("");
                SharedPrefManager.setString(SharedPrefKey.TOKEN,token);
                finish();
                break;
        }
    }

    // load user information from server
    private void loadUserInformation() {
        ApiRequests.getUserInfo(new BaseResponseInterface<UserInfo>() {
            @Override
            public void onSuccess(UserInfo userInfo) {
                currentRequest = userInfo.getCurrentRequest();
                if(currentRequest == 0){
                    btnCurrentRequest.setVisibility(View.GONE);
                }else {
                    btnCurrentRequest.setVisibility(View.VISIBLE);
                }
                SharedPrefManager.setObject(SharedPrefKey.USER_INFO, userInfo);
                setUserData(userInfo);
            }

            @Override
            public void onFailed(String errorMessage) {

            }
        });
    }
}
