package bassiouny.ahmed.waslabank.fragments.controller;

import android.content.Context;
import android.widget.ArrayAdapter;

import bassiouny.ahmed.waslabank.R;

/**
 * Created by bassiouny on 30/03/18.
 */

public class SignUpUserDetailsController {

    private Context context;

    public SignUpUserDetailsController(Context context) {
        this.context = context;
    }
    @SuppressWarnings("unchecked")
    public ArrayAdapter getCitiesAdapter(){
        // get array adapter contain cities
        return new ArrayAdapter(context, R.layout.spinner_row,context.getResources().getStringArray(R.array.cities));
    }
}
