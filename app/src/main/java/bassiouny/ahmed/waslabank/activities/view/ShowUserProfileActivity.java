package bassiouny.ahmed.waslabank.activities.view;

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

import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.adapter.FeedbackItem;
import bassiouny.ahmed.waslabank.api.ApiRequests;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.waslabank.model.User;
import bassiouny.ahmed.waslabank.utils.MyGlideApp;
import bassiouny.ahmed.waslabank.utils.MyToolbar;
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
        setContentView(R.layout.activity_show_user_profile);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        userId = getIntent().getIntExtra("USER_ID", 0);
        if (userId == 0)
            findView();
        findView();
        loadUserData();
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
        linear = findViewById(R.id.linear);
        viewStubProgress = findViewById(R.id.view_stub_progress);
        ivAvatar = findViewById(R.id.iv_avatar);
        tvUserName = findViewById(R.id.tv_user_name);
        rating = findViewById(R.id.rating);
        tvUserPhone = findViewById(R.id.tv_user_phone);
        tvUserInteresting = findViewById(R.id.tv_user_interesting);
        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));

    }
}
