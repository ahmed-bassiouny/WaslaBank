package com.wasllabank.fragments.controller;

import android.support.v4.app.Fragment;

import com.wasllabank.api.ApiRequests;
import com.wasllabank.api.apiModel.requests.TripsByDate;
import com.wasllabank.interfaces.BaseResponseInterface;

/**
 * Created by bassiouny on 11/04/18.
 */

public class RequestsController {
    // current fragment
    private Fragment fragment;
    // item count per requests
    // what number of trips i needed per requests
    protected final int REQUESTS_COUNT = 10;

    public RequestsController(Fragment fragment) {
        this.fragment = fragment;
    }
    public void getTripsByDate(int year , int month , int day , int page , final BaseResponseInterface anInterface){
        TripsByDate.Builder builder = new TripsByDate.Builder();
        builder.date(createDateWithSpecialFormat(year,month,day));
        builder.page(page);
        ApiRequests.getTripsByDate(builder.build(),anInterface);
    }
    /*
    * this method take year ,month , day as parameter
    * return date string with special format
    * */
    public String createDateWithSpecialFormat(int year , int month , int day){
        String monthStr , dayStr;
        if(month < 10)
            monthStr = "0"+month;
        else
            monthStr= String.valueOf(month);
        if(day < 10)
            dayStr = "0"+day;
        else
            dayStr= String.valueOf(day);
        return String.valueOf(year)+"-"+monthStr+"-"+dayStr;
    }
}
