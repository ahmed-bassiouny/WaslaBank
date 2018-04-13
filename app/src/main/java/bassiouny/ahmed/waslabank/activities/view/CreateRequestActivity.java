package bassiouny.ahmed.waslabank.activities.view;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import bassiouny.ahmed.waslabank.R;

public class CreateRequestActivity extends AppCompatActivity {

    //view
    private MaterialCalendarView calendarView;
    private EditText etFrom;
    private EditText etTo;
    private EditText etTime;
    private Button btnCreate;

    // local variable
    private final int PLACE_PICKER_REQUEST_FROM = 1;
    private final int PLACE_PICKER_REQUEST_TO = 2;
    private PlacePicker.IntentBuilder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);
        findView();
        onClick();
        initObjects();

    }

    private void initObjects() {
        builder = new PlacePicker.IntentBuilder();
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

                    }
                },5,5,false);
                timePickerDialog.show();
            }
        });
    }

    private void findView() {
        calendarView = findViewById(R.id.calendarView);
        etFrom = findViewById(R.id.et_from);
        etTo = findViewById(R.id.et_to);
        etTime = findViewById(R.id.et_time);
        btnCreate = findViewById(R.id.btn_create);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK)
            return;
        if (requestCode == PLACE_PICKER_REQUEST_FROM) {
            Place place = PlacePicker.getPlace(this, data);
        } else if (requestCode == PLACE_PICKER_REQUEST_TO) {
            Place place = PlacePicker.getPlace(this, data);
        }
    }
}
