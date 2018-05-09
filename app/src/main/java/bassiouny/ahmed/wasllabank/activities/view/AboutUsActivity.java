package bassiouny.ahmed.wasllabank.activities.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.webkit.WebView;
import android.widget.Toast;

import bassiouny.ahmed.wasllabank.R;
import bassiouny.ahmed.wasllabank.api.ApiRequests;
import bassiouny.ahmed.wasllabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.wasllabank.model.About;
import bassiouny.ahmed.wasllabank.utils.MyToolbar;

public class AboutUsActivity extends MyToolbar {


    private ViewStub viewStubProgress;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        initToolbar(getString(R.string.about_us), true);
        addBackImage();
        addNotificationImage();
        addSupportActionbar();
        viewStubProgress = findViewById(R.id.view_stub_progress);
        webView = findViewById(R.id.web_view);
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
