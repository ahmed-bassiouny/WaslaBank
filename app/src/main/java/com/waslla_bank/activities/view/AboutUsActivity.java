package com.waslla_bank.activities.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.webkit.WebView;
import android.widget.Toast;

import com.waslla_bank.api.ApiRequests;
import com.waslla_bank.interfaces.BaseResponseInterface;
import com.waslla_bank.model.About;
import com.waslla_bank.utils.MyToolbar;

public class AboutUsActivity extends MyToolbar {


    private ViewStub viewStubProgress;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.waslla_bank.R.layout.activity_about_us);
        initToolbar(getString(com.waslla_bank.R.string.about_us), true);
        addBackImage();
        addNotificationImage();
        addSupportActionbar();
        viewStubProgress = findViewById(com.waslla_bank.R.id.view_stub_progress);
        webView = findViewById(com.waslla_bank.R.id.web_view);
        viewStubProgress.setVisibility(View.VISIBLE);
        ApiRequests.getAbout(new BaseResponseInterface<About>() {
            @Override
            public void onSuccess(About about) {
                webView.loadDataWithBaseURL("", about.getBody(), "text/html", "UTF-8", "");
                webView.setVisibility(View.VISIBLE);
                viewStubProgress.setVisibility(View.GONE);
            }

            @Override
            public void onFailed(String errorMessage) {
                Toast.makeText(AboutUsActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                viewStubProgress.setVisibility(View.GONE);
            }
        });
    }
}
