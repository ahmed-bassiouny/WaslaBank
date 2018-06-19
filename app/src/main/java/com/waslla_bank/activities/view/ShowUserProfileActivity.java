package com.waslla_bank.activities.view;

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

import com.waslla_bank.utils.MyUtils;
import com.waslla_bank.adapter.FeedbackItem;
import com.waslla_bank.api.ApiRequests;
import com.waslla_bank.interfaces.BaseResponseInterface;
import com.waslla_bank.model.User;
import com.waslla_bank.utils.MyGlideApp;

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
        setContentView(com.waslla_bank.R.layout.activity_show_user_profile);
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
        linear = findViewById(com.waslla_bank.R.id.linear);
        viewStubProgress = findViewById(com.waslla_bank.R.id.view_stub_progress);
        ivAvatar = findViewById(com.waslla_bank.R.id.iv_avatar);
        tvUserName = findViewById(com.waslla_bank.R.id.tv_user_name);
        rating = findViewById(com.waslla_bank.R.id.rating);
        tvUserPhone = findViewById(com.waslla_bank.R.id.tv_user_phone);
        tvUserInteresting = findViewById(com.waslla_bank.R.id.tv_user_interesting);
        recycler = findViewById(com.waslla_bank.R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));

    }
}
