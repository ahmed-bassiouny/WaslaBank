package com.waslla_bank.activities.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import android.widget.Toast;

import com.waslla_bank.model.Notification;

import java.util.List;

import com.waslla_bank.adapter.NotificationItem;
import com.waslla_bank.api.ApiRequests;
import com.waslla_bank.interfaces.BaseResponseInterface;
import com.waslla_bank.utils.MyToolbar;
import com.waslla_bank.utils.SimpleDividerItemDecoration;

public class NotificationActivity extends MyToolbar {


    private ViewStub viewStubProgress;
    private RecyclerView recycler;
    private TextView noNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.waslla_bank.R.layout.activity_notification);
        initToolbar(getString(com.waslla_bank.R.string.notifications),true);
        addBackImage();
        addSupportActionbar();
        findView();
        loadNotification();
    }

    private void findView() {
        viewStubProgress = findViewById(com.waslla_bank.R.id.view_stub_progress);
        recycler = findViewById(com.waslla_bank.R.id.recycler);
        noNotification = findViewById(com.waslla_bank.R.id.no_notification);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.addItemDecoration(new SimpleDividerItemDecoration(this));
    }

    private void loadNotification(){
        viewStubProgress.setVisibility(View.VISIBLE);
        recycler.setVisibility(View.INVISIBLE);
        noNotification.setVisibility(View.INVISIBLE);
        ApiRequests.getNotification(new BaseResponseInterface<List<Notification>>() {
            @Override
            public void onSuccess(List<Notification> notifications) {
                if(notifications.size() == 0){
                    noNotification.setVisibility(View.VISIBLE);
                }else {
                    recycler.setAdapter(new NotificationItem(NotificationActivity.this,notifications));
                    recycler.setVisibility(View.VISIBLE);
                }
                viewStubProgress.setVisibility(View.GONE);
            }

            @Override
            public void onFailed(String errorMessage) {
                Toast.makeText(NotificationActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                viewStubProgress.setVisibility(View.GONE);
            }
        });
    }
}
