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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import bassiouny.ahmed.wasllabank.R;
import bassiouny.ahmed.wasllabank.fragments.controller.SignUpCarController;
import bassiouny.ahmed.wasllabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.wasllabank.utils.MyUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpCarFragment extends Fragment {


    // view variable
    private LinearLayout linearCarSize;
    private Spinner spCarSize;
    private EditText etCarNumber;
    private EditText etLicenseNumber;
    private Button btnNext;
    private ProgressBar progress;
    // local variable
    private SignUpCarController controller;
    //----------------------------------------------
    // methods auto generated

    public SignUpCarFragment() {
        // Required empty public constructor
    }


    private SignUpCarController getController() {
        if (controller == null)
            controller = new SignUpCarController(getContext());
        return controller;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_car, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        onClick();
    }


    // ----------------------------------------------------
    // generate method by me

    private void onClick() {
        // set click on linear to open car size spinner
        linearCarSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spCarSize.performClick();

            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etCarNumber.getText().toString().trim().isEmpty()) {
                    etCarNumber.setError(getString(R.string.invalid_car_number));
                } else if (etLicenseNumber.getText().toString().trim().isEmpty()) {
                    etLicenseNumber.setError(getString(R.string.invalid_license_number));
                } else {
                    loading(true);
                    getController().checkLicenseCarNumber(etCarNumber.getText().toString(),
                            etLicenseNumber.getText().toString(), new BaseResponseInterface() {
                                @Override
                                public void onSuccess(Object o) {
                                    getController().saveUserObject(etCarNumber.getText().toString()
                                            , etLicenseNumber.getText().toString(), spCarSize.getSelectedItem().toString());
                                    loading(false);
                                    MyUtils.openFragment(R.id.container, getActivity(), new SignUpUserDetailsFragment(), true, null);
                                }

                                @Override
                                public void onFailed(String errorMessage) {
                                    Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                                    loading(false);
                                }
                            });
                }
            }
        });
    }

    private void findView(View view) {
        linearCarSize = view.findViewById(R.id.linear_car_size);
        spCarSize = view.findViewById(R.id.sp_car_size);
        etCarNumber = view.findViewById(R.id.et_car_number);
        etLicenseNumber = view.findViewById(R.id.et_license_number);
        btnNext = view.findViewById(R.id.btn_next);
        progress = view.findViewById(R.id.progress);

        // set data in spinner
        spCarSize.setAdapter(getController().getCarSizeAdapter());
        // make spinner start from botton
        spCarSize.setDropDownVerticalOffset(135);
    }
    // start loading
    // todo i will remove this method
    private void loading(boolean start){
        if(start){
            progress.setVisibility(View.VISIBLE);
            btnNext.setEnabled(false);
        }else {
            progress.setVisibility(View.INVISIBLE);
            btnNext.setEnabled(true);
        }
    }

}