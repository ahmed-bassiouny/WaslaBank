package bassiouny.ahmed.waslabank.activities.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.adapter.NotificationItem;
import bassiouny.ahmed.waslabank.api.ApiRequests;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.waslabank.model.Notification;
import bassiouny.ahmed.waslabank.utils.MyToolbar;
import bassiouny.ahmed.waslabank.utils.SimpleDividerItemDecoration;

public class NotificationActivity extends MyToolbar {


    private ViewStub viewStubProgress;
    private RecyclerView recycler;
    private TextView noNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initToolbar(getString(R.string.notifications),true);
        addBackImage();
        addSupportActionbar();
        findView();
        loadNotification();
    }

    private void findView() {
        viewStubProgress = findViewById(R.id.view_stub_progress);
        recycler = findViewById(R.id.recycler);
        noNotification = findViewById(R.id.no_notification);
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
