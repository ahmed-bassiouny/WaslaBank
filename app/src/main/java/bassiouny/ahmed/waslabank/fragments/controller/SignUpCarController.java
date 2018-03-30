package bassiouny.ahmed.waslabank.fragments.controller;

import android.content.Context;
import android.widget.ArrayAdapter;

import bassiouny.ahmed.waslabank.R;

/**
 * Created by bassiouny on 30/03/18.
 */

public class SignUpCarController {
    private Context context;

    public SignUpCarController(Context context) {
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    public ArrayAdapter getCarSizeAdapter(){
        // get array adapter contain array size
        return new ArrayAdapter(context, R.layout.spinner_row,context.getResources().getStringArray(R.array.car_size));
    }
}
