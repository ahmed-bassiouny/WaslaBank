package com.wasllabank.activities.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.wasllabank.utils.MyUtils;
import com.wasllabank.adapter.FeedbackItem;
import com.wasllabank.api.ApiRequests;
import com.wasllabank.interfaces.BaseResponseInterface;
import com.wasllabank.model.User;
import com.wasllabank.utils.MyGlideApp;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShowUserProfileActivity extends AppCompatActivity {

    private ViewStub viewStubProgress;
    private CircleImageView ivAvatar;
    private TextView tvUserName;
    private RatingBar rating;
    private TextView tvUserPhone;
    private TextView tvUserInteresting;
    private RecyclerView recycler;
    private LinearLayout linear;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.wasllabank.R.layout.activity_show_user_profile);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        userId = getIntent().getIntExtra("USER_ID", 0);
        if (userId == 0)
            findView();
        findView();
        loadUserData();
        onClick();
    }

    private void onClick() {
        tvUserPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.dialContactPhone(ShowUserProfileActivity.this,tvUserPhone.getText().toString());
            }
        });
    }

    private void loadUserData() {
        viewStubProgress.setVisibility(View.VISIBLE);
        linear.setVisibility(View.INVISIBLE);
        ApiRequests.getUserProfile(userId, new BaseResponseInterface<User>() {
            @Override
            public void onSuccess(User user) {
                setUserData(user);
                viewStubProgress.setVisibility(View.GONE);
            }

            @Override
            public void onFailed(String errorMessage) {
                Toast.makeText(ShowUserProfileActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                viewStubProgress.setVisibility(View.GONE);
            }
        });
    }

    private void setUserData(User user) {
        tvUserName.setText(user.getName());
        tvUserPhone.setText(user.getPhone());
        tvUserInteresting.setText(user.getInteresting());
        rating.setRating(user.getRate());
        MyGlideApp.setImageCenterInside(this, ivAvatar, user.getImage());
        recycler.setAdapter(new FeedbackItem(this,user.getFeedbacks()));
        linear.setVisibility(View.VISIBLE);
    }

    private void findView() {
        linear = findViewById(com.wasllabank.R.id.linear);
        viewStubProgress = findViewById(com.wasllabank.R.id.view_stub_progress);
        ivAvatar = findViewById(com.wasllabank.R.id.iv_avatar);
        tvUserName = findViewById(com.wasllabank.R.id.tv_user_name);
        rating = findViewById(com.wasllabank.R.id.rating);
        tvUserPhone = findViewById(com.wasllabank.R.id.tv_user_phone);
        tvUserInteresting = findViewById(com.wasllabank.R.id.tv_user_interesting);
        recycler = findViewById(com.wasllabank.R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));

    }
}
