package bassiouny.ahmed.waslabank.fragments.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
    // local variable
    private SignUpCarController controller;

    public SignUpCarFragment() {
        // Required empty public constructor
    }


    private SignUpCarController getController(){
        if(controller == null)
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
        view.findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.openFragment(R.id.container,getActivity(),new SignUpUserDetailsFragment(),true,null);
            }
        });
        findView(view);
        onClick();
    }

    private void onClick() {
        // set click on linear to open car size spinner
        linearCarSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spCarSize.performClick();

            }
        });
    }

    private void findView(View view) {
        linearCarSize =  view.findViewById(R.id.linear_car_size);
        spCarSize = view.findViewById(R.id.sp_car_size);
        // set data in spinner
        spCarSize.setAdapter(getController().getCarSizeAdapter());
        // make spinner start from botton
        spCarSize.setDropDownVerticalOffset(135);
    }

}
