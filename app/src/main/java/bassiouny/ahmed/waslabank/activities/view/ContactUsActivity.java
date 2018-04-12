package bassiouny.ahmed.waslabank.activities.view;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ProgressBar;
import android.widget.Toast;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.api.ApiRequests;
import bassiouny.ahmed.waslabank.api.apiModel.requests.ContactUsRequest;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.waslabank.model.TripDetails;
import bassiouny.ahmed.waslabank.model.User;
import bassiouny.ahmed.waslabank.utils.MyToolbar;
import bassiouny.ahmed.waslabank.utils.SharedPrefKey;
import de.hdodenhof.circleimageview.CircleImageView;

public class ContactUsActivity extends MyToolbar {

    private TextInputEditText etName;
    private TextInputEditText etPhone;
    private TextInputEditText etSubject;
    private TextInputEditText etMessage;
    private CircleImageView ivSend;
    private ViewStub viewStubProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        initToolbar(getString(R.string.contact_us));
        addBackImage();
        addNotificationImage();
        addSupportActionbar();
        findView();
        onClick();
    }

    private void onClick() {
        ivSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etName.getText().toString().isEmpty()) {
                    etName.setError(getString(R.string.invalid_name));
                } else if (etPhone.getText().toString().length() != 11) {
                    etPhone.setError(getString(R.string.invalid_phone_length));
                } else if (etSubject.getText().toString().isEmpty()) {
                    etSubject.setError(getString(R.string.invalid_subject));
                } else if (etMessage.getText().toString().isEmpty()) {
                    etMessage.setError(getString(R.string.invalid_message));
                } else {
                    loading(true);
                    // create contact us request object
                    ContactUsRequest.Builder contactUsRequest = new ContactUsRequest.Builder();
                    contactUsRequest.userId(SharedPrefManager.getObject(SharedPrefKey.USER, User.class).getId());
                    contactUsRequest.name(etName.getText().toString());
                    contactUsRequest.phone(etPhone.getText().toString());
                    contactUsRequest.subject(etSubject.getText().toString());
                    contactUsRequest.message(etMessage.getText().toString());
                    // send data to server
                    ApiRequests.contactUs(contactUsRequest.build(), new BaseResponseInterface() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(ContactUsActivity.this, getString(R.string.thanks), Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailed(String errorMessage) {
                            Toast.makeText(ContactUsActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                            loading(false);
                        }
                    });
                }
            }
        });
    }

    private void loading(boolean isLoading) {
        if (isLoading) {
            viewStubProgress.setVisibility(View.VISIBLE);
            ivSend.setVisibility(View.GONE);
        } else {
            viewStubProgress.setVisibility(View.GONE);
            ivSend.setVisibility(View.VISIBLE);
        }
    }

    private void findView() {
        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_phone);
        etSubject = findViewById(R.id.et_subject);
        etMessage = findViewById(R.id.et_message);
        ivSend = findViewById(R.id.iv_send);
        viewStubProgress = findViewById(R.id.view_stub_progress);
    }
}
