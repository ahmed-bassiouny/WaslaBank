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
import android.widget.Toast;

import java.util.List;

import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.activities.view.ShowUserProfileActivity;
import bassiouny.ahmed.waslabank.adapter.UsersTripDetailsItem;
import bassiouny.ahmed.waslabank.api.ApiRequests;
import bassiouny.ahmed.waslabank.api.apiModel.requests.AcceptRejectUser;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.waslabank.interfaces.ObserverInterface;
import bassiouny.ahmed.waslabank.interfaces.UserTripDetailsInterface;
import bassiouny.ahmed.waslabank.model.TripDetails;
import bassiouny.ahmed.waslabank.model.User;
import bassiouny.ahmed.waslabank.model.UserInTrip;
import bassiouny.ahmed.waslabank.utils.SimpleDividerItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserTripDetailsFragment extends Fragment implements ObserverInterface<TripDetails>, UserTripDetailsInterface {


    private static UserTripDetailsFragment mInstance;
    private RecyclerView recycler;
    private List<UserInTrip> userInTrips;
    private int tripId;
    private UsersTripDetailsItem adapter;

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
        recycler.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
    }

    @Override
    public void update(TripDetails tripDetails) {
        this.userInTrips = tripDetails.getUserInTrip();
        tripId = tripDetails.getId();
        adapter = new UsersTripDetailsItem(this, userInTrips);
        recycler.setAdapter(adapter);

    }

    @Override
    public void accept(final int position) {
        final UserInTrip user = userInTrips.get(position);
        if (!user.isAccepted() && tripId != 0) {
            // make loading progress visible
            user.setLoading(true);
            adapter.changeItem(user, position);
            // create request object
            AcceptRejectUser.Builder builder = new AcceptRejectUser.Builder();
            builder.requestId(tripId);
            builder.userId(user.getUserId());
            builder.isAccept(true);
            ApiRequests.acceptRejectUser(builder.build(), new BaseResponseInterface() {
                @Override
                public void onSuccess(Object o) {
                    // accept user
                    user.setAccepted();
                    // make loading progress hidden
                    user.setLoading(false);
                    // update current array
                    userInTrips.set(position, user);
                    // change item in adapter
                    adapter.changeItem(user, position);
                }

                @Override
                public void onFailed(String errorMessage) {
                    Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                    // make loading progress hidden
                    user.setLoading(false);
                    // change item in adapter
                    adapter.changeItem(user, position);
                }
            });
        }
    }

    @Override
    public void reject(final int position) {
        final UserInTrip user = userInTrips.get(position);
        if (tripId != 0 && !user.isAccepted()) {
            // make loading progress visible
            user.setLoading(true);
            adapter.changeItem(user, position);
            // create request object
            AcceptRejectUser.Builder builder = new AcceptRejectUser.Builder();
            builder.requestId(tripId);
            builder.userId(user.getUserId());
            builder.isAccept(false);
            ApiRequests.acceptRejectUser(builder.build(), new BaseResponseInterface() {
                @Override
                public void onSuccess(Object o) {
                    // remove current item from array
                    userInTrips.remove(position);
                    // remove item in adapter
                    adapter.removeItem(position);
                }

                @Override
                public void onFailed(String errorMessage) {
                    Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                    // make loading progress hidden
                    user.setLoading(false);
                    // change item in adapter
                    adapter.changeItem(user, position);
                }
            });
        }
    }

    @Override
    public void viewProfile(int position) {
        Intent intent = new Intent(getContext(), ShowUserProfileActivity.class);
        intent.putExtra("USER_ID", userInTrips.get(position).getUserId());
        startActivity(intent);
    }
}
