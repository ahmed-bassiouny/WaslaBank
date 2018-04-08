package bassiouny.ahmed.waslabank.fragments.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bassiouny.ahmed.waslabank.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {


    private static AboutFragment mInstance;

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

}
