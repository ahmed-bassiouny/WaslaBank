package bassiouny.ahmed.waslabank.fragments.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.interfaces.ObserverInterface;
import bassiouny.ahmed.waslabank.model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserTripDetailsFragment extends Fragment implements ObserverInterface<List<User>> {


    private static UserTripDetailsFragment mInstance;

    public UserTripDetailsFragment() {
        // Required empty public constructor
    }

    public static UserTripDetailsFragment getInstance() {
        if (mInstance == null) {
            mInstance = new UserTripDetailsFragment();
        }
        return mInstance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_trip_details, container, false);
    }

    @Override
    public void update(List<User> users) {

    }
}
