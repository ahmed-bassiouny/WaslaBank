package bassiouny.ahmed.wasllabank.fragments.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bassiouny.ahmed.wasllabank.R;
import bassiouny.ahmed.wasllabank.interfaces.ObserverInterface;
import bassiouny.ahmed.wasllabank.model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment implements ObserverInterface<User> {


    private static AboutFragment mInstance;

    // view

    private TextView tvAbout;
    private TextView tvPhone;
    private TextView tvEmail;
    private TextView tvCarNumber;


    public AboutFragment() {
        // Required empty public constructor
    }

    public static AboutFragment getInstance() {
        if (mInstance == null) {
            mInstance = new AboutFragment();
        }
        return mInstance;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvAbout = view.findViewById(R.id.tv_about);
        tvPhone = view.findViewById(R.id.tv_phone);
        tvEmail = view.findViewById(R.id.tv_email);
        tvCarNumber = view.findViewById(R.id.tv_car_number);
    }

    @Override
    public void update(User user) {
        tvEmail.setText(user.getEmail());
        tvPhone.setText(user.getPhone());
        tvAbout.setText(user.getInteresting());
        tvCarNumber.setText(user.getCarNumber());
    }
}
