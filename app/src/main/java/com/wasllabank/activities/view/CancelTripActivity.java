package com.wasllabank.activities.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wasllabank.R;

public class CancelTripActivity extends AppCompatActivity {

    private EditText etComment;
    private Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_trip);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        etComment = findViewById(R.id.et_comment);
        btnSend = findViewById(R.id.btn_send);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etComment.getText().toString().isEmpty()){
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("comment", etComment.getText().toString());
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
            }
        });

    }
}
