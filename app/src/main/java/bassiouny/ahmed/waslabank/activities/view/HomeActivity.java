package bassiouny.ahmed.waslabank.activities.view;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.adapter.HomeMenuItem;
import bassiouny.ahmed.waslabank.interfaces.ItemClickInterface;
import bassiouny.ahmed.waslabank.utils.MyToolbar;
import bassiouny.ahmed.waslabank.utils.SharedPrefKey;

public class HomeActivity extends MyToolbar implements ItemClickInterface {

    private RecyclerView recyclerView;
    private int[] menuImages;
    private String[] menuStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initToolbar("John Deo");
        addProfileImage();
        addNotificationImage();
        addSupportActionbar();
        findView();
        initObject();
        onClick();
    }

    private void initObject() {
        // set menu image array
        menuImages = new int[]{R.drawable.car_plus, R.drawable.car, R.drawable.ic_call, R.drawable.logo_w, R.drawable.ic_call_missed};
        // set menu string array
        menuStrings = getResources().getStringArray(R.array.menu);
        // check image and string array
        // if not equal throw exception to make app crush
        if(menuImages.length != menuStrings.length)
            throw new RuntimeException("Image Array Not Equal String Array");
        // set item menu in recycler view
        setHomeMenu();
    }

    private void onClick() {
    }

    private void findView() {
        recyclerView = findViewById(R.id.recycler);
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
        if(position == 4){
            SharedPrefManager.clearSharedPref();
            startActivity(new Intent(HomeActivity.this,SplashActivity.class));
            finish();
        }
    }
}
