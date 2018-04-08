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
public class AboutDriverFragment extends Fragment {


    private static AboutDriverFragment mInstance;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_driver, container, false);
    }

}
