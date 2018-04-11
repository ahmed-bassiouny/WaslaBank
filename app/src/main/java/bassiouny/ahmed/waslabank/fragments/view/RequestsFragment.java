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

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.List;

import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.adapter.RequestsItem;
import bassiouny.ahmed.waslabank.fragments.controller.RequestsController;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.waslabank.model.TripDetails;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestsFragment extends Fragment {

    private RecyclerView recyclerView;
    private MaterialCalendarView calendarView;
    private RequestsController controller;
    private int currentPage = 10;

    public RequestsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_requests, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        onCLick();
    }

    private void onCLick() {
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                getController().getTripsByDate(date.getYear(), date.getMonth()+1, date.getDay(), currentPage, new BaseResponseInterface<List<TripDetails>>() {

                    @Override
                    public void onSuccess(List<TripDetails> tripDetails) {

                    }

                    @Override
                    public void onFailed(String errorMessage) {

                    }
                });
            }
        });
    }

    private void findView(View view) {
        recyclerView = view.findViewById(R.id.recycler);
        calendarView = view.findViewById(R.id.calendarView);
        // set layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // set adapter for recycler view
        recyclerView.setAdapter(new RequestsItem());
    }

    private RequestsController getController() {
        if (controller == null)
            controller = new RequestsController(this);
        return controller;
    }
}
