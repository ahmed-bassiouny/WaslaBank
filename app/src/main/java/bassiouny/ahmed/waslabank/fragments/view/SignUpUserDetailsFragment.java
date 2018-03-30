package bassiouny.ahmed.waslabank.fragments.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;

import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.fragments.controller.SignUpUserDetailsController;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpUserDetailsFragment extends Fragment {


    // view variable
    private LinearLayout linearCities;
    private Spinner spCities;
    // local variable
    private SignUpUserDetailsController controller;

    public SignUpUserDetailsFragment() {
        // Required empty public constructor
    }

    private SignUpUserDetailsController getController(){
        if(controller == null)
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

    private void onClick() {
        linearCities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spCities.performClick();
            }
        });
    }

    private void findView(View view) {
        linearCities = view.findViewById(R.id.linear_cities);
        spCities = view.findViewById(R.id.sp_cities);
        // set data in spinner
        spCities.setAdapter(getController().getCitiesAdapter());
        // make spinner start from botton
        spCities.setDropDownVerticalOffset(120);
    }
}
