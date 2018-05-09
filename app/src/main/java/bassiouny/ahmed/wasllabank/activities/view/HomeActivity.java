package bassiouny.ahmed.wasllabank.activities.view;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import bassiouny.ahmed.wasllabank.R;
import bassiouny.ahmed.wasllabank.adapter.HomeMenuItem;
import bassiouny.ahmed.wasllabank.api.ApiRequests;
import bassiouny.ahmed.wasllabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.wasllabank.interfaces.ItemClickInterface;
import bassiouny.ahmed.wasllabank.model.User;
import bassiouny.ahmed.wasllabank.model.UserInfo;
import bassiouny.ahmed.wasllabank.utils.MyApplication;
import bassiouny.ahmed.wasllabank.utils.MyToolbar;
import bassiouny.ahmed.wasllabank.utils.SharedPrefKey;

public class HomeActivity extends MyToolbar implements ItemClickInterface {

    // view
    private RecyclerView recyclerView;
    private TextView tvPoint, tvRequests, tvOrders;
    private RatingBar rating;
    //local variable
    private int[] menuImages;
    private String[] menuStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
        findViewById(R.id.linear_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,ViewOrdersActivity.class));
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
        menuImages = new int[]{R.drawable.car_plus, R.drawable.car, R.drawable.contactus, R.drawable.about, R.drawable.logout};
        // set menu string array
        menuStrings = getResources().getStringArray(R.array.menu);
        // check image and string array
        // if not equal throw exception to make app crush
        if (menuImages.length != menuStrings.length)
            throw new RuntimeException("Image Array Not Equal String Array");
        // set item menu in recycler view
        setHomeMenu();
    }

    private void findView() {
        recyclerView = findViewById(R.id.recycler);
        tvPoint = findViewById(R.id.tv_point);
        tvRequests = findViewById(R.id.tv_requests);
        tvOrders = findViewById(R.id.tv_order);
        rating = findViewById(R.id.rating);
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
                SharedPrefManager.setObject(SharedPrefKey.USER_INFO, userInfo);
                setUserData(userInfo);
            }

            @Override
            public void onFailed(String errorMessage) {

            }
        });
    }
}