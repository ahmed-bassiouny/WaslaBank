package com.wasllabank.fragments.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wasllabank.adapter.FeedbackItem;
import com.wasllabank.interfaces.ObserverInterface;
import com.wasllabank.model.Feedback;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackFragment extends Fragment implements ObserverInterface<List<Feedback>> {


    private static FeedbackFragment mInstance;
    private RecyclerView recyclerView;

    public FeedbackFragment() {
        // Required empty public constructor
    }

    public static FeedbackFragment getInstance() {
        if (mInstance == null) {
            mInstance = new FeedbackFragment();
        }
        return mInstance;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(com.wasllabank.R.layout.fragment_feedback, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
    }

    private void findView(View view) {
        recyclerView = view.findViewById(com.wasllabank.R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void update(List<Feedback> feedbackList) {
        recyclerView.setAdapter(new FeedbackItem(getContext(),feedbackList));
    }
}
