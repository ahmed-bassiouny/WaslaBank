package bassiouny.ahmed.waslabank.activities.view;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.activities.controller.CreateRequestController;
import bassiouny.ahmed.waslabank.api.apiModel.requests.CreateTripRequest;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.waslabank.model.User;
import bassiouny.ahmed.waslabank.utils.MyToolbar;
import bassiouny.ahmed.waslabank.utils.SharedPrefKey;

public class CreateRequestActivity extends MyToolbar {

    //view
    private MaterialCalendarView calendarView;
    private EditText etFrom;
    private EditText etTo;
    private EditText etTime;
    private Button btnCreate;
    private ViewStub viewStubProgress;
    // local variable
    private final int PLACE_PICKER_REQUEST_FROM = 1;
    private final int PLACE_PICKER_REQUEST_TO = 2;
    private PlacePicker.IntentBuilder builder;
    private CreateTripRequest.Builder builderCreateTripRequest;
    private CreateRequestController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);
        // set tool bar
        initToolbar("");
        addBackImagePrimary();
        addNotificationImagePrimary();
        addSupportActionbar();
        findView();
        onClick();
        initObjects();

    }

    private void initObjects() {
        builder = new PlacePicker.IntentBuilder();
        builderCreateTripRequest = new CreateTripRequest.Builder();
    }

    private void onClick() {
        etFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivityForResult(builder.build(CreateRequestActivity.this), PLACE_PICKER_REQUEST_FROM);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
        etTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivityForResult(builder.build(CreateRequestActivity.this), PLACE_PICKER_REQUEST_TO);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(CreateRequestActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        builderCreateTripRequest.Time(i + ":" + i1);
                        etTime.setText(i + ":" + i1);

                    }
                }, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), true);
                timePickerDialog.show();
            }
        });
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                builderCreateTripRequest.date(getValidStringDateFromInt(date.getYear(),date.getMonth(),date.getDay()));

            }
        });
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateTripRequest object = builderCreateTripRequest.build();
                if (object.getStartPointText().isEmpty()) {
                    etFrom.setError(getString(R.string.invalid_location));
                } else if (object.getEndPointText().isEmpty()) {
                    etFrom.setError(null);
                    etTo.setError(getString(R.string.invalid_location));
                } else if (object.getDate().isEmpty()) {
                    etFrom.setError(null);
                    etTo.setError(null);
                    Toast.makeText(CreateRequestActivity.this, getString(R.string.invalid_date), Toast.LENGTH_SHORT).show();
                } else if (object.getTime().isEmpty()) {
                    etFrom.setError(null);
                    etTo.setError(null);
                    etTime.setError(getString(R.string.invalid_time));
                } else {
                    etFrom.setError(null);
                    etTo.setError(null);
                    etTime.setError(null);
                    loading(true);
                    builderCreateTripRequest.driverId(SharedPrefManager.getObject(SharedPrefKey.USER, User.class).getId());
                    getController().createTrip(builderCreateTripRequest, new BaseResponseInterface() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(CreateRequestActivity.this, getString(R.string.trip_created), Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailed(String errorMessage) {
                            Toast.makeText(CreateRequestActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                            loading(false);
                        }
                    });
                }
            }
        });
    }

    private void findView() {
        calendarView = findViewById(R.id.calendarView);
        etFrom = findViewById(R.id.et_from);
        etTo = findViewById(R.id.et_to);
        etTime = findViewById(R.id.et_time);
        btnCreate = findViewById(R.id.btn_create);
        viewStubProgress = findViewById(R.id.view_stub_progress);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK)
            return;
        if (requestCode == PLACE_PICKER_REQUEST_FROM) {
            Place place = PlacePicker.getPlace(this, data);
            builderCreateTripRequest.startPointLat(place.getLatLng().latitude);
            builderCreateTripRequest.startPointLng(place.getLatLng().longitude);
            builderCreateTripRequest.startPointText(place.getAddress().toString());
            etFrom.setText(place.getAddress().toString());
        } else if (requestCode == PLACE_PICKER_REQUEST_TO) {
            Place place = PlacePicker.getPlace(this, data);
            builderCreateTripRequest.endPointLat(place.getLatLng().latitude);
            builderCreateTripRequest.endPointLng(place.getLatLng().longitude);
            builderCreateTripRequest.endPointText(place.getAddress().toString());
            etTo.setText(place.getAddress().toString());
        }
    }

    private CreateRequestController getController() {
        if (controller == null)
            controller = new CreateRequestController();
        return controller;
    }

    private void loading(boolean isLoading) {
        if (isLoading) {
            viewStubProgress.setVisibility(View.VISIBLE);
            btnCreate.setVisibility(View.INVISIBLE);
        } else {
            viewStubProgress.setVisibility(View.GONE);
            btnCreate.setVisibility(View.VISIBLE);
        }
    }

    private String getValidStringDateFromInt(int year,int month,int day){
        month++;
        String dayStr = day>10?String.valueOf(day):"0"+String.valueOf(day);
        String monthStr = month>10?String.valueOf(month):"0"+String.valueOf(month);
        return year+"-"+monthStr+"-"+dayStr;
    }
}
