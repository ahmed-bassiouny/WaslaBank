package bassiouny.ahmed.waslabank.fragments.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.interfaces.ObserverInterface;
import bassiouny.ahmed.waslabank.model.TripDetails;
import bassiouny.ahmed.waslabank.model.User;
import bassiouny.ahmed.waslabank.utils.MyGlideApp;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutDriverFragment extends Fragment implements ObserverInterface<User> {


    private static AboutDriverFragment mInstance;
    // layout
    private CircleImageView ivAvatar;
    private TextView tvUserName;
    private RatingBar rating;
    private TextView tvAbout;
    private TextView tvPhone;
    private TextView tvEmail;
    private TextView tvCarNumber;


    public AboutDriverFragment() {
        // Required empty public constructor
    }

    public static AboutDriverFragment getInstance() {
        if (mInstance == null) {
            mInstance = new AboutDriverFragment();
        }
        return mInstance;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_driver, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
    }

    private void findView(View view) {
        ivAvatar = view.findViewById(R.id.iv_avatar);
        tvUserName = view.findViewById(R.id.tv_user_name);
        rating = view.findViewById(R.id.rating);
        tvAbout = view.findViewById(R.id.tv_about);
        tvPhone = view.findViewById(R.id.tv_phone);
        tvEmail = view.findViewById(R.id.tv_email);
        tvCarNumber = view.findViewById(R.id.tv_car_number);
    }

    @Override
    public void update(User driver) {
        tvUserName.setText(driver.getName());
        tvEmail.setText(driver.getEmail());
        tvPhone.setText(driver.getPhone());
        tvAbout.setText(driver.getUserDetails().getInteresting());
        tvCarNumber.setText(driver.getUserDetails().getCar().getCarNumber());
        rating.setRating(driver.getUserDetails().getTotalRate());
        MyGlideApp.setImage(getContext(),ivAvatar,driver.getUserDetails().getImage());

    }
}
