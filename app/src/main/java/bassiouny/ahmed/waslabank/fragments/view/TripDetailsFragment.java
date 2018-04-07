package bassiouny.ahmed.waslabank.fragments.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bassiouny.ahmed.waslabank.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TripDetailsFragment extends Fragment {


    private static TripDetailsFragment mInstance;

    public TripDetailsFragment() {
        // Required empty public constructor
    }

    public static TripDetailsFragment getInstance() {
        if (mInstance == null) {
            mInstance = new TripDetailsFragment();
        }
        return mInstance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trip_details, container, false);
    }

}
