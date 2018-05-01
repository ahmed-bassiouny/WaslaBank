package bassiouny.ahmed.waslabank.fragments.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.adapter.UsersTripDetailsItem;
import bassiouny.ahmed.waslabank.interfaces.ObserverInterface;
import bassiouny.ahmed.waslabank.interfaces.UserTripDetailsInterface;
import bassiouny.ahmed.waslabank.model.User;
import bassiouny.ahmed.waslabank.model.UserInTrip;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserTripDetailsFragment extends Fragment implements ObserverInterface<List<UserInTrip>>,UserTripDetailsInterface {



    private static UserTripDetailsFragment mInstance;
    private RecyclerView recycler;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void update(List<UserInTrip> userInTrips) {
        recycler.setAdapter(new UsersTripDetailsItem(this,userInTrips));

    }

    @Override
    public void accept(int position) {
        
    }

    @Override
    public void reject(int position) {

    }

    @Override
    public void viewProfile(int position) {

    }
}
