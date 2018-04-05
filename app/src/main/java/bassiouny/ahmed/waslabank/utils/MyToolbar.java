package bassiouny.ahmed.waslabank.utils;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import bassiouny.ahmed.waslabank.R;

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
        // create back image view
        ImageView back = new ImageView(this);
        // back image view src
        back.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_back));
        // handle click item
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

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
        // create title in center
        TextView title = new TextView(this);
        // set text for title
        title.setText(toolbarTitle);
        // change text color
        title.setTextColor(getResources().getColor(R.color.white));
        // set icons and title to frame layout
        frameLayout.addView(back,getMarginForView(Gravity.START));
        frameLayout.addView(notification,getMarginForView(Gravity.END));
        frameLayout.addView(title,getMarginForView(Gravity.CENTER));
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
