package bassiouny.ahmed.waslabank.fragments.view;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.activities.view.SignInActivity;
import bassiouny.ahmed.waslabank.activities.view.WaitingAdminActivity;
import bassiouny.ahmed.waslabank.fragments.controller.SignUpUserDetailsController;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.waslabank.model.User;
import bassiouny.ahmed.waslabank.utils.Constant;
import bassiouny.ahmed.waslabank.utils.FilePath;
import bassiouny.ahmed.waslabank.utils.SharedPrefKey;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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
        if (resultCode == Activity.RESULT_OK && data.getData() != null && requestCode == getController().PICK_IMAGE) {
                image = new File(FilePath.getRealPathFromURI(getContext(),data.getData()));
                ivAvatar.setImageURI(data.getData());
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
                if (etIdentifyNumber.getText().toString().trim().isEmpty()) {
                    etIdentifyNumber.setError(getString(R.string.invalid_identify_number));
                } else {
                    loading(true);
                    String gender = rbMale.isChecked() ? Constant.MALE : Constant.FEMALE;
                    getController().registerUser(etIdentifyNumber.getText().toString(), gender, spCities.getSelectedItem().toString(),
                            image, new BaseResponseInterface<User>(){
                                @Override
                                public void onSuccess(User user) {
                                    // save user data in shared pref
                                    SharedPrefManager.setObject(SharedPrefKey.USER,user);
                                    // create intent
                                    Intent intent = new Intent(getContext(),WaitingAdminActivity.class);
                                    // close splash screen activity
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    // stop loading
                                    loading(false);
                                    startActivity(intent);
                                    getActivity().finish();
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
                getController().selectImage(SignUpUserDetailsFragment.this);
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
    // todo i will remove this method
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
