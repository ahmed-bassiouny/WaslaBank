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
import android.view.ViewStub;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.List;

import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.adapter.RequestsItem;
import bassiouny.ahmed.waslabank.fragments.controller.RequestsController;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.waslabank.interfaces.ItemClickInterface;
import bassiouny.ahmed.waslabank.model.TripDetails;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestsFragment extends Fragment implements ItemClickInterface<Integer> {

    // view
    private RecyclerView recyclerView;
    private MaterialCalendarView calendarView;
    private ViewStub viewStubProgress;
    private View line;
    // objects
    private RequestsController controller;
    private int currentPage = 10;
    private RequestsItem adapter;

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
        initObject();
    }

    private void initObject() {
        // init adapter
        adapter = new RequestsItem(this);
        // set adapter for recycler view
        recyclerView.setAdapter(adapter);
    }

    private void onCLick() {

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                loading(true);
                getController().getTripsByDate(date.getYear(), date.getMonth() + 1, date.getDay(), currentPage, new BaseResponseInterface<List<TripDetails>>() {

                    @Override
                    public void onSuccess(List<TripDetails> tripDetails) {
                        adapter.setList(tripDetails);
                        loading(false);
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
        });
    }

    private void findView(View view) {
        recyclerView = view.findViewById(R.id.recycler);
        calendarView = view.findViewById(R.id.calendarView);
        viewStubProgress = view.findViewById(R.id.view_stub_progress);
        line = view.findViewById(R.id.line);
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

    }
    private void loading(boolean isLoading){
        if(isLoading) {
            viewStubProgress.setVisibility(View.VISIBLE);
            line.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }else {
            viewStubProgress.setVisibility(View.INVISIBLE);
            line.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}
