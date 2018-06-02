package com.wasllabank.activities.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wasllabank.R;
import com.wasllabank.utils.MyToolbar;
import com.wasllabank.utils.MyUtils;

import java.io.File;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class SignUpUserImagesActivity extends MyToolbar {


    private ViewStub viewStubProgress;
    private TextView tvTitle;
    private ImageView img;
    private LinearLayout linear;
    private File image;
    private int i = 0; // 0 => national id , 1 => user license , 2 => driving license

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user_images);
        initToolbar("Select Image",true);
        addSupportActionbar();
        findView();
        onClick();
    }

    private void onClick() {
        findViewById(R.id.btn_select_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.openChooserWithGallery(SignUpUserImagesActivity.this);
            }
        });
        findViewById(R.id.btn_upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (image == null) {
                    Toast.makeText(SignUpUserImagesActivity.this, R.string.select_photo, Toast.LENGTH_SHORT).show();
                } else {
                    // todo when uploaded image done
                    loading(false);
                    i++;
                    setTitle();
                }
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Some error handling
                Toast.makeText(SignUpUserImagesActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                image = imageFile;
                Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
                img.setImageBitmap(myBitmap);
            }

        });
    }

    private void findView() {
        viewStubProgress = findViewById(R.id.view_stub_progress);
        tvTitle = findViewById(R.id.tv_title);
        img = findViewById(R.id.img);
        linear = findViewById(R.id.linear);
    }

    private void loading(boolean start) {
        if (start) {
            linear.setVisibility(View.GONE);
            viewStubProgress.setVisibility(View.VISIBLE);
        } else {
            linear.setVisibility(View.VISIBLE);
            viewStubProgress.setVisibility(View.GONE);
        }
    }

    private void setTitle() {
        switch (i) {
            case 0:
                tvTitle.setText(getString(R.string.select_national_id));
                break;
            case 1:
                tvTitle.setText(getString(R.string.select_driving_license));
                break;
            case 2:
                tvTitle.setText(getString(R.string.select_car_license));
                break;
            default:
                startActivity(new Intent(SignUpUserImagesActivity.this,WaitingAdminActivity.class));
                finish();
        }
    }
}