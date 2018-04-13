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
public class BlankFragment2 extends Fragment {


    private static BlankFragment2 mInstance;

    public BlankFragment2() {
        // Required empty public constructor
    }

    public static BlankFragment2 getInstance() {
        if (mInstance == null) {
            mInstance = new BlankFragment2();
        }
        return mInstance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
    }

}
