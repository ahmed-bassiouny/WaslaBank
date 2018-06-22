package com.waslla_bank.activities.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.waslla_bank.R;
import com.waslla_bank.api.ApiRequests;
import com.waslla_bank.interfaces.BaseResponseInterface;
import com.waslla_bank.utils.MyToolbar;
import com.waslla_bank.utils.MyUtils;

import java.io.File;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class SignUpUserImagesActivity extends MyToolbar implements BaseResponseInterface {


    private ViewStub viewStubProgress;
    private TextView tvTitle;
    private ImageView img;
    private LinearLayout linear;
    private File image;
    private Button btnUpload;
    private int i = 0; // 0 => national id , 1 => car license , 2 => driving license

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user_images);
        btnUpload = findViewById(R.id.btn_upload);
        initToolbar("Add your documents", true);
        addSupportActionbar();
        findView();
        onClick();
        setTitle();
    }

    private void onClick() {
        findViewById(R.id.btn_select_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.openChooserWithGallery(SignUpUserImagesActivity.this);
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (image == null) {
                    Toast.makeText(SignUpUserImagesActivity.this, R.string.select_photo, Toast.LENGTH_SHORT).show();
                } else {
                    uploadImage();
                }
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.openChooserWithGallery(SignUpUserImagesActivity.this);
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
                btnUpload.setVisibility(View.VISIBLE);
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
            linear.setVisibility(View.INVISIBLE);
            viewStubProgress.setVisibility(View.VISIBLE);
        } else {
            linear.setVisibility(View.VISIBLE);
            viewStubProgress.setVisibility(View.INVISIBLE);
        }
    }

    private void setTitle() {
        image = null;
        img.setImageDrawable(getResources().getDrawable(R.drawable.placeholder));
        btnUpload.setVisibility(View.INVISIBLE);
        switch (i) {
            case 0:
                tvTitle.setText(getString(R.string.select_national_id));
                break;
            case 1:
                tvTitle.setText(getString(R.string.select_car_license));
                break;
            case 2:
                tvTitle.setText(getString(R.string.select_driving_license));
                break;
            default:
                startActivity(new Intent(SignUpUserImagesActivity.this, WaitingAdminActivity.class));
                finish();
        }
    }

    private void uploadImage() {
        loading(true);
        switch (i) {
            case 0:
                ApiRequests.uploadNationalId(MyUtils.convertFileToPart(image), this);
                break;
            case 1:
                ApiRequests.uploadCarLicense(MyUtils.convertFileToPart(image), this);
                break;
            case 2:
                ApiRequests.uploadDrivingLicense(MyUtils.convertFileToPart(image), this);
                break;
        }
    }

    @Override
    public void onSuccess(Object o) {
        loading(false);
        i++;
        setTitle();
    }

    @Override
    public void onFailed(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        loading(false);
    }
}
