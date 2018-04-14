package bassiouny.ahmed.waslabank.fragments.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.interfaces.ObserverInterface;
import bassiouny.ahmed.waslabank.model.TripDetails;

/**
 * A simple {@link Fragment} subclass.
 */
public class TripDetailsFragment extends Fragment implements ObserverInterface<TripDetails>{


    private static TripDetailsFragment mInstance;

    //view

    private TextView tvFrom;
    private TextView tvTo;
    private TextView tvAvailablePlaces;
    private TextView tvDate,tvTime;
    private Button btnJoinTrip;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        onClick();
    }

    private void onClick() {

        btnJoinTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void findView(View view) {
        tvFrom = view.findViewById(R.id.tv_from);
        tvTo = view.findViewById(R.id.tv_to);
        tvAvailablePlaces = view.findViewById(R.id.tv_available_places);
        tvDate = view.findViewById(R.id.tv_date);
        tvTime = view.findViewById(R.id.tv_time);
        btnJoinTrip = view.findViewById(R.id.btn_join_trip);
    }

    @Override
    public void update(TripDetails tripDetails) {
        tvFrom.setText(tripDetails.getStartPointText());
        tvTo.setText(tripDetails.getEndPointText());
        tvDate.setText(tripDetails.getDate());
        tvTime.setText(tripDetails.getTime());
        tvAvailablePlaces.setText(tripDetails.getAvailablePlaces());
    }
}
