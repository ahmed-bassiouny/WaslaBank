package bassiouny.ahmed.waslabank.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.activities.view.HomeActivity;
import bassiouny.ahmed.waslabank.activities.view.UserProfileActivity;

/**
 * Created by bassiouny on 05/04/18.
 */

public class MyToolbar extends AppCompatActivity {

    private Toolbar toolbar;
    private FrameLayout frameLayout;
    public void initToolbar(String toolbarTitle) {
        toolbar = findViewById(R.id.toolbar);
        // create frame layout
        frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        ));

        // create title in center
        TextView title = new TextView(this);
        // set text for title
        title.setText(toolbarTitle);
        // change text color
        title.setTextColor(getResources().getColor(R.color.white));
        // text size
        title.setTextSize(18);
        // set icons and title to frame layout
        frameLayout.addView(title,getMarginForView(Gravity.CENTER));
    }

    public void addBackImage(){

        // create back image view
        ImageView back = new ImageView(this);
        // back image view src
        back.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_back));
        // add tint mode
        back.setColorFilter(Color.argb(255, 255, 255, 255));
        // handle click item
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        // set icons to frame layout
        frameLayout.addView(back,getMarginForView(Gravity.START));
    }
    public void addProfileImage(){

        // create back image view
        ImageView profile = new ImageView(this);
        // back image view src
        profile.setImageDrawable(getResources().getDrawable(R.drawable.ic_person));
        // add tint mode
        profile.setColorFilter(Color.argb(255, 255, 255, 255));
        // handle click item
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyToolbar.this, UserProfileActivity.class));

            }
        });
        // set icons to frame layout
        frameLayout.addView(profile,getMarginForView(Gravity.START));
    }
    public void addNotificationImage(){
        // create notification image view
        ImageView notification = new ImageView(this);
        // notification image view src
        notification.setImageDrawable(getResources().getDrawable(R.drawable.ic_notifications_none));
        // handle click item
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        // set icons to frame layout
        frameLayout.addView(notification,getMarginForView(Gravity.END));
    }
    public void addView(View view){
        toolbar.addView(view);
    }
    public void addSupportActionbar(){
        if(toolbar != null) {
            toolbar.addView(frameLayout);
            setSupportActionBar(toolbar);
        }
    }

    private FrameLayout.LayoutParams getMarginForView(int gravity) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,gravity);
        params.setMargins(10, 10, 50, 10);
        return params;
    }
}
