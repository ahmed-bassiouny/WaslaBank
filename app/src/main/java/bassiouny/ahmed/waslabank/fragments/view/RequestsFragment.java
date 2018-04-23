package bassiouny.ahmed.waslabank.fragments.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import bassiouny.ahmed.genericmanager.DateTimeManager;
import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.activities.view.RequestInfoActivity;
import bassiouny.ahmed.waslabank.adapter.RequestsItem;
import bassiouny.ahmed.waslabank.fragments.controller.RequestsController;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.waslabank.interfaces.ItemClickInterface;
import bassiouny.ahmed.waslabank.model.TripDetails;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestsFragment extends Fragment implements ItemClickInterface<Integer> {

    private static RequestsFragment mInstance;
    // view
    private RecyclerView recyclerView;
    private MaterialCalendarView calendarView;
    private ViewStub viewStubProgress;
    private TextView tvNoTrip;
    // local variable
    private RequestsController controller;
    private int currentPage = 10;
    private RequestsItem adapter;
    private Calendar cal;

    public RequestsFragment() {
        // Required empty public constructor
    }

    public static RequestsFragment getInstance() {
        if (mInstance == null) {
            mInstance = new RequestsFragment();
        }
        return mInstance;
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
        initObject();
        cal = Calendar.getInstance();
        // set today selected day
        calendarView.setSelectedDate(cal);
        loadData(calendarView.getSelectedDate().getYear(),calendarView.getSelectedDate().getMonth(),calendarView.getSelectedDate().getDay());
    }


    private void initObject() {
        // init adapter
        adapter = new RequestsItem(this);
        // set adapter for recycler view
        recyclerView.setAdapter(adapter);
        // set in calendar current date
        calendarView.setCurrentDate(Calendar.getInstance());
    }

    private void loadData(int year, int month, int day) {
        loading(true);
        getController().getTripsByDate(year, month + 1, day, currentPage, new BaseResponseInterface<List<TripDetails>>() {

            @Override
            public void onSuccess(List<TripDetails> tripDetails) {
                loading(false);
                if (tripDetails.size() != 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    tvNoTrip.setVisibility(View.INVISIBLE);
                    adapter.setList(tripDetails);
                }else {
                    recyclerView.setVisibility(View.INVISIBLE);
                    tvNoTrip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailed(String errorMessage) {
                if (getActivity() != null) {
                    Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                    loading(false);
                }
            }
        });
    }

    private void onCLick() {

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                loadData(date.getYear(), date.getMonth(), date.getDay());
            }
        });
    }

    private void findView(View view) {
        recyclerView = view.findViewById(R.id.recycler);
        calendarView = view.findViewById(R.id.calendarView);
        viewStubProgress = view.findViewById(R.id.view_stub_progress);
        tvNoTrip = view.findViewById(R.id.tv_no_trip);
        // set layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private RequestsController getController() {
        if (controller == null)
            controller = new RequestsController(this);
        return controller;
    }

    @Override
    public void getItem(@Nullable Integer tripId, int position) {
        if (getActivity() == null)
            return;
        Intent intent = new Intent(getActivity(), RequestInfoActivity.class);
        intent.putExtra("TRIP_ID", tripId);
        startActivity(intent);

    }

    private void loading(boolean isLoading) {
        if (isLoading) {
            viewStubProgress.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
            tvNoTrip.setVisibility(View.INVISIBLE);
        } else {
            viewStubProgress.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}
