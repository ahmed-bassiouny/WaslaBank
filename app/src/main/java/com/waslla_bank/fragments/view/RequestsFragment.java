package com.waslla_bank.fragments.view;


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
import android.widget.TextView;
import android.widget.Toast;

import com.waslla_bank.activities.view.RequestInfoActivity;
import com.waslla_bank.model.TripDetails;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;
import java.util.List;

import com.waslla_bank.adapter.RequestsItem;
import com.waslla_bank.fragments.controller.RequestsController;
import com.waslla_bank.interfaces.BaseResponseInterface;
import com.waslla_bank.interfaces.ItemClickInterface;

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
    private int currentPage = 10; // item per page first = 10 second page = 20 third page = 30 .. etc
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
        return inflater.inflate(com.waslla_bank.R.layout.fragment_requests, container, false);
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
                    recyclerView.setVisibility(View.GONE);
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
        recyclerView = view.findViewById(com.waslla_bank.R.id.recycler);
        calendarView = view.findViewById(com.waslla_bank.R.id.calendarView);
        viewStubProgress = view.findViewById(com.waslla_bank.R.id.view_stub_progress);
        tvNoTrip = view.findViewById(com.waslla_bank.R.id.tv_no_trip);
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
            recyclerView.setVisibility(View.GONE);
            tvNoTrip.setVisibility(View.GONE);
        } else {
            viewStubProgress.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}
