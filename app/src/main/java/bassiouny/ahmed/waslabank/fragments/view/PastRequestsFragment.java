package bassiouny.ahmed.waslabank.fragments.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.activities.view.RequestInfoActivity;
import bassiouny.ahmed.waslabank.adapter.RequestsItem;
import bassiouny.ahmed.waslabank.api.ApiRequests;
import bassiouny.ahmed.waslabank.api.apiModel.requests.TripsByDate;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.waslabank.interfaces.ItemClickInterface;
import bassiouny.ahmed.waslabank.model.TripDetails;
import bassiouny.ahmed.waslabank.model.User;
import bassiouny.ahmed.waslabank.utils.SharedPrefKey;

/**
 * A simple {@link Fragment} subclass.
 */
public class PastRequestsFragment extends Fragment implements ItemClickInterface<Integer> {


    // local
    private static PastRequestsFragment mInstance;
    private RequestsItem requestsItem;
    private static String currentUrl;
    private int currentPage = 10; // item per page first = 10 second page = 20 third page = 30 .. etc
    // this variable show if this view for orders or past requests
    private static boolean pastRequestsView = false;
    // view
    private SwipeRefreshLayout refresh;
    private RecyclerView recycler;
    private TextView tvNoTrip;

    public PastRequestsFragment() {
        // Required empty public constructor
    }

    public static PastRequestsFragment getInstance(boolean pastRequests) {
        pastRequestsView = pastRequests;
        if (mInstance == null) {
            mInstance = new PastRequestsFragment();
        }
        if (pastRequestsView) {
            currentUrl = "requests/past";
        } else {
            currentUrl = "requests/orders";
        }
        return mInstance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_past_requests, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        initObject();
        onClick();
        fetchData();

    }

    private void onClick() {
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 10;
                fetchData();
            }
        });
    }

    private void initObject() {
        requestsItem = new RequestsItem(this);
        recycler.setAdapter(requestsItem);
    }

    private void findView(View view) {
        refresh = view.findViewById(R.id.refresh);
        tvNoTrip = view.findViewById(R.id.tv_no_trip);
        recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void fetchData() {
        refresh.setRefreshing(true);
        TripsByDate.Builder builder = new TripsByDate.Builder();
        builder.userId(SharedPrefManager.getObject(SharedPrefKey.USER, User.class).getId());
        builder.page(currentPage);
        ApiRequests.getPastTrips(currentUrl, builder.build(), new BaseResponseInterface<List<TripDetails>>() {
            @Override
            public void onSuccess(List<TripDetails> tripDetails) {
                if (tripDetails.size() == 0) {
                    recycler.setVisibility(View.INVISIBLE);
                    tvNoTrip.setVisibility(View.VISIBLE);
                    refresh.setRefreshing(false);
                    return;
                }
                if (currentPage == 10) {
                    // in this case list dont have item
                    // set item in list
                    requestsItem.setList(tripDetails);
                } else {
                    // add more items to list
                    requestsItem.addList(tripDetails);
                }
                refresh.setRefreshing(false);
                recycler.setVisibility(View.VISIBLE);
                tvNoTrip.setVisibility(View.GONE);
            }

            @Override
            public void onFailed(String errorMessage) {
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                refresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void getItem(@Nullable Integer tripId, int position) {
        if (getActivity() == null || pastRequestsView)
            return;
        Intent intent = new Intent(getActivity(), RequestInfoActivity.class);
        intent.putExtra("TRIP_ID", tripId);
        startActivity(intent);
    }
}
