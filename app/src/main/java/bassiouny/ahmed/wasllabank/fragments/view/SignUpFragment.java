package bassiouny.ahmed.wasllabank.fragments.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import bassiouny.ahmed.wasllabank.R;
import bassiouny.ahmed.wasllabank.fragments.controller.SignUpController;
import bassiouny.ahmed.wasllabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.wasllabank.utils.MyUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    // view variable
    private EditText etName;
    private EditText etPhone;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button btnNext;
    private ProgressBar progress;
    // local variable
    private SignUpController controller;

    //----------------------------------------------
    // methods auto generated

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        onCLick();
    }


    // ----------------------------------------------------
    // generate method by me

    private SignUpController getController() {
        if (controller == null)
            controller = new SignUpController();
        return controller;
    }

    private void findView(View view) {
        etName = view.findViewById(R.id.et_name);
        etPhone = view.findViewById(R.id.et_phone);
        etEmail = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);
        etConfirmPassword = view.findViewById(R.id.et_confirm_password);
        btnNext = view.findViewById(R.id.btn_next);
        progress = view.findViewById(R.id.progress);
    }

    private void onCLick() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etName.getText().toString().trim().isEmpty()) {
                    etName.setError(getString(R.string.invalid_name));
                } else if (etPhone.getText().toString().length() != 11) {
                    etPhone.setError(getString(R.string.invalid_phone_length));
                } else if (etPassword.getText().toString().length() < 6) {
                    etPassword.setError(getString(R.string.invalid_password_length));
                } else if (!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
                    etConfirmPassword.setError(getString(R.string.invalid_confirm));
                } else {
                    loading(true);
                    // check email and phone are unique or not
                    getController().checkEmailAndPhone(etEmail.getText().toString(),
                            etPhone.getText().toString(), new BaseResponseInterface() {
                                @Override
                                public void onSuccess(Object o) {
                                    // save user data object in shared pref
                                    getController().saveUserObject(etName.getText().toString()
                                            , etPhone.getText().toString(), etEmail.getText().toString()
                                            , etPassword.getText().toString());
                                    loading(false);
                                    // open next screen
                                    MyUtils.openFragment(R.id.container, getActivity(), new SignUpCarFragment(), true, null);
                                }

                                @Override
                                public void onFailed(String errorMessage) {
                                    loading(false);
                                    Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }

    // start loading
    // todo i will remove this method
    private void loading(boolean start) {
        if (start) {
            progress.setVisibility(View.VISIBLE);
            btnNext.setEnabled(false);
        } else {
            progress.setVisibility(View.INVISIBLE);
            btnNext.setEnabled(true);
        }
    }
}
