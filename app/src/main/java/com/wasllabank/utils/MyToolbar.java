package com.wasllabank.utils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.wasllabank.activities.view.NotificationActivity;
import com.wasllabank.activities.view.UserProfileActivity;

/**
 * Created by bassiouny on 05/04/18.
 */

@SuppressLint("Registered")
public class MyToolbar extends AppCompatActivity {

    private Toolbar toolbar;
    private FrameLayout frameLayout;
    private TextView title;
    public void initToolbar(String toolbarTitle,boolean supportBackground) {
        toolbar = findViewById(com.wasllabank.R.id.toolbar);
        // create frame layout
        frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        ));
        if(supportBackground)
            toolbar.setBackground(getResources().getDrawable(com.wasllabank.R.drawable.toolbar));

        // create title in center
        title = new TextView(this);
        // set text for title
        title.setText(toolbarTitle);
        // change text color
        title.setTextColor(getResources().getColor(com.wasllabank.R.color.white));
        // text size
        title.setTextSize(18);
        // set icons and title to frame layout
        frameLayout.addView(title,getMarginForView(Gravity.CENTER));
    }
    public void updateTitle(String toolbarTitle){
        title.setText(toolbarTitle);
    }

    public void addBackImage(){

        // create back image view
        ImageView back = new ImageView(this);
        // back image view src
        back.setImageDrawable(getResources().getDrawable(com.wasllabank.R.drawable.ic_arrow_back));
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
    public void addBackImagePrimary(){

        // create back image view
        ImageView back = new ImageView(this);
        // back image view src
        back.setImageDrawable(getResources().getDrawable(com.wasllabank.R.drawable.ic_arrow_back));
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
        profile.setImageDrawable(getResources().getDrawable(com.wasllabank.R.drawable.ic_person));
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
        notification.setImageDrawable(getResources().getDrawable(com.wasllabank.R.drawable.ic_notifications_none));
        // handle click item
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyToolbar.this, NotificationActivity.class));
            }
        });
        // set icons to frame layout
        frameLayout.addView(notification,getMarginForView(Gravity.END));
    }
    public void addNotificationImagePrimary(){
        // create notification image view
        ImageView notification = new ImageView(this);
        // notification image view src
        notification.setImageDrawable(getResources().getDrawable(com.wasllabank.R.drawable.ic_notifications_none));
        // add tint mode
        notification.setColorFilter(Color.argb(255, 100, 56, 125));
        // handle click item
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyToolbar.this, NotificationActivity.class));
            }
        });
        // set icons to frame layout
        frameLayout.addView(notification,getMarginForView(Gravity.END));
    }
    public void addView(View view, FrameLayout.LayoutParams params){
        frameLayout.addView(view,params);
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
