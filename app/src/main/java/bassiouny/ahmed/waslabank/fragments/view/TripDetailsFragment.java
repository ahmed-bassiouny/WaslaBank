package bassiouny.ahmed.waslabank.fragments.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.interfaces.ParsingInterface;
import bassiouny.ahmed.waslabank.model.TripDetails;

/**
 * A simple {@link Fragment} subclass.
 */
public class TripDetailsFragment extends Fragment implements ParsingInterface<TripDetails>{


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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trip_details, container, false);
    }

    @Override
    public void parseObject(@Nullable TripDetails tripDetails) {

    }
}
