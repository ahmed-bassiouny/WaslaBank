package bassiouny.ahmed.waslabank.fragments.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;

import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.fragments.controller.SignUpCarController;
import bassiouny.ahmed.waslabank.utils.MyUtils;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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
                    //Todo send request to check if car number and license number unique
                    // todo if unique save in shared pref else stop this step
                    getController().saveUserObject(etCarNumber.getText().toString()
                            , etLicenseNumber.getText().toString(), spCarSize.getSelectedItem().toString());
                    MyUtils.openFragment(R.id.container, getActivity(), new SignUpUserDetailsFragment(), true, null);
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
