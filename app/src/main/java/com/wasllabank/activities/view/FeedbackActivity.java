package com.wasllabank.activities.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.wasllabank.api.apiModel.requests.FeedbackRequest;
import com.wasllabank.R;
import com.wasllabank.api.ApiRequests;
import com.wasllabank.interfaces.BaseResponseInterface;

public class FeedbackActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private TextInputEditText etComment;
    private ViewStub viewStub;
    private Button btnSend;
    private FeedbackRequest.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        builder = getIntent().getParcelableExtra("FEEDBACK");
        if(builder == null)
            finish();
        ratingBar = findViewById(R.id.ratingBar);
        etComment = findViewById(R.id.et_comment);
        viewStub = findViewById(R.id.view_stub_progress);
        btnSend = findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewStub.setVisibility(View.VISIBLE);
                btnSend.setVisibility(View.GONE);
                builder.rate((int) ratingBar.getRating());
                builder.comment(etComment.getText().toString());
                ApiRequests.sendFeedback(builder.build(), new BaseResponseInterface() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(FeedbackActivity.this, "Thanks", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailed(String errorMessage) {
                        Toast.makeText(FeedbackActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                        viewStub.setVisibility(View.GONE);
                        btnSend.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }
}
