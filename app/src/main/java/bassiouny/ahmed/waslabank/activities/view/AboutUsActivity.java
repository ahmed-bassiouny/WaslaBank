package bassiouny.ahmed.waslabank.activities.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.webkit.WebView;
import android.widget.Toast;

import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.api.ApiRequests;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.waslabank.model.About;
import bassiouny.ahmed.waslabank.model.TripDetails;
import bassiouny.ahmed.waslabank.utils.MyToolbar;

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
