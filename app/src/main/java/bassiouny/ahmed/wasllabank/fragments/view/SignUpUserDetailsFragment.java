package bassiouny.ahmed.wasllabank.fragments.view;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import bassiouny.ahmed.wasllabank.R;
import bassiouny.ahmed.wasllabank.fragments.controller.SignUpUserDetailsController;
import bassiouny.ahmed.wasllabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.wasllabank.model.User;
import bassiouny.ahmed.wasllabank.utils.Constant;
import bassiouny.ahmed.wasllabank.utils.MyUtils;
import bassiouny.ahmed.wasllabank.utils.SharedPrefKey;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpUserDetailsFragment extends Fragment {


    // view variable
    private LinearLayout linearCities;
    private Spinner spCities;
    private ImageView ivAvatar;
    private EditText etIdentifyNumber;
    private AppCompatRadioButton rbMale;
    private Button btnSignUp;
    private ProgressBar progress;
    private File image;

    // local variable
    private SignUpUserDetailsController controller;
    //----------------------------------------------
    // methods auto generated

    public SignUpUserDetailsFragment() {
        // Required empty public constructor
    }

    private SignUpUserDetailsController getController() {
        if (controller == null)
            controller = new SignUpUserDetailsController(getContext());
        return controller;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_user_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        onClick();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Some error handling
                Toast.makeText(getActivity(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                image = imageFile;
                Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
                ivAvatar.setImageBitmap(myBitmap);
            }

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == getController().MY_PERMISSIONS_REQUEST_IMAGE && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            MyUtils.openChooserWithGallery(SignUpUserDetailsFragment.this);
        }
    }

    // ----------------------------------------------------
    // generate method by me

    private void onClick() {
        linearCities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spCities.performClick();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etIdentifyNumber.getText().toString().length() != 14) {
                    etIdentifyNumber.setError(getString(R.string.invalid_identify_number));
                } else {
                    loading(true);
                    String gender = rbMale.isChecked() ? Constant.MALE : Constant.FEMALE;
                    getController().registerUser(etIdentifyNumber.getText().toString(), gender, spCities.getSelectedItem().toString(),
                            image, new BaseResponseInterface<User>() {
                                @Override
                                public void onSuccess(User user) {
                                    // save user data in shared pref
                                    SharedPrefManager.setObject(SharedPrefKey.USER, user);
                                    MyUtils.openHomeScreen(getActivity(),user);
                                }

                                @Override
                                public void onFailed(String errorMessage) {
                                    Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                                    loading(false);
                                }
                            });
                }
            }
        });
        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getContext() == null)
                    return;
                if (ActivityCompat.checkSelfPermission(getContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                            getController().MY_PERMISSIONS_REQUEST_IMAGE);
                    return;
                }
                MyUtils.openChooserWithGallery(SignUpUserDetailsFragment.this);
            }
        });
    }

    private void findView(View view) {
        linearCities = view.findViewById(R.id.linear_cities);
        spCities = view.findViewById(R.id.sp_cities);
        ivAvatar = view.findViewById(R.id.iv_avatar);
        etIdentifyNumber = view.findViewById(R.id.et_identify_number);
        rbMale = view.findViewById(R.id.rb_male);
        btnSignUp = view.findViewById(R.id.btn_sign_up);
        progress = view.findViewById(R.id.progress);

        // set data in spinner
        spCities.setAdapter(getController().getCitiesAdapter());
        // make spinner start from botton
        spCities.setDropDownVerticalOffset(120);
    }

    // start loading
    private void loading(boolean start) {
        if (start) {
            progress.setVisibility(View.VISIBLE);
            btnSignUp.setEnabled(false);
        } else {
            progress.setVisibility(View.INVISIBLE);
            btnSignUp.setEnabled(true);
        }
    }
}
