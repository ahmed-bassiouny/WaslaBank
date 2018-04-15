package bassiouny.ahmed.waslabank.activities.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.activities.controller.EditProfileController;
import bassiouny.ahmed.waslabank.fragments.view.SignUpUserDetailsFragment;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.waslabank.model.User;
import bassiouny.ahmed.waslabank.utils.MyGlideApp;
import bassiouny.ahmed.waslabank.utils.MyToolbar;
import bassiouny.ahmed.waslabank.utils.MyUtils;
import bassiouny.ahmed.waslabank.utils.SharedPrefKey;
import de.hdodenhof.circleimageview.CircleImageView;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class EditProfileActivity extends MyToolbar {

    // local variable
    private User user;
    private EditProfileController controller;
    private File image;
    // view
    private CircleImageView ivAvatar;
    private TextInputEditText etName;
    private TextInputEditText etInteresting;
    private Button btnUpdate;
    private Button btnChangePassword;
    private ViewStub viewStubProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        // set title toolbar
        initToolbar(getString(R.string.profile));
        addBackImage();
        addSupportActionbar();
        findView();
        onClick();
        setUserData();
    }

    private void setUserData() {
        user = SharedPrefManager.getObject(SharedPrefKey.USER, User.class);
        if (user == null)
            finish();
        etName.setText(user.getName());
        etInteresting.setText(user.getUserDetails().getInteresting());
        MyGlideApp.setImage(this, ivAvatar, user.getUserDetails().getImage());
    }

    private void onClick() {
        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(EditProfileActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(EditProfileActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                            getController().MY_PERMISSIONS_REQUEST_IMAGE);
                    return;
                }
                MyUtils.openChooserWithGallery(EditProfileActivity.this);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etName.getText().toString().trim().isEmpty()) {
                    etName.setError(getString(R.string.invalid_name));
                } else {
                    loading(true);
                    getController().editProfile(image,user.getId(), etName.getText().toString(), etInteresting.getText().toString(), new BaseResponseInterface<User>() {
                        @Override
                        public void onSuccess(User user) {
                            // save user data in shared pref
                            /*EditProfileActivity.this.user.setName(etName.getText().toString());
                            EditProfileActivity.this.user.getUserDetails().setInteresting(etInteresting.getText().toString());
                            */SharedPrefManager.setObject(SharedPrefKey.USER, user);
                            Toast.makeText(EditProfileActivity.this, getText(R.string.profile_updated), Toast.LENGTH_SHORT).show();
                            loading(false);
                        }

                        @Override
                        public void onFailed(String errorMessage) {
                            Toast.makeText(EditProfileActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                            loading(false);
                        }
                    });
                }
            }
        });
    }

    private void findView() {
        ivAvatar = findViewById(R.id.iv_avatar);
        etName = findViewById(R.id.et_name);
        etInteresting = findViewById(R.id.et_interesting);
        btnUpdate = findViewById(R.id.btn_update);
        btnChangePassword = findViewById(R.id.btn_change_password);
        viewStubProgress = findViewById(R.id.view_stub_progress);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == getController().MY_PERMISSIONS_REQUEST_IMAGE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            MyUtils.openChooserWithGallery(this);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Some error handling
                Toast.makeText(EditProfileActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                image = imageFile;
                Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
                ivAvatar.setImageBitmap(myBitmap);
            }

        });
    }

    private EditProfileController getController() {
        if (controller == null)
            controller = new EditProfileController(this);
        return controller;
    }

    private void loading(boolean isLoading) {
        if (isLoading) {
            viewStubProgress.setVisibility(View.VISIBLE);
            btnUpdate.setVisibility(View.INVISIBLE);
            btnChangePassword.setVisibility(View.INVISIBLE);
        } else {
            viewStubProgress.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.VISIBLE);
            btnChangePassword.setVisibility(View.VISIBLE);
        }

    }

}
