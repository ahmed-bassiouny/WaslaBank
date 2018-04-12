package bassiouny.ahmed.waslabank.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import bassiouny.ahmed.genericmanager.DateTimeManager;
import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;
import bassiouny.ahmed.waslabank.utils.DateTimeKey;
import bassiouny.ahmed.waslabank.utils.MyUtils;

/**
 * Created by bassiouny on 11/04/18.
 */

public class TripDetails {

    @SerializedName(ApiKey.ID)
    private int id;
    @SerializedName(ApiKey.START_POINT_ADDRESS)
    private String startPointText;
    @SerializedName(ApiKey.START_POINT_LAT)
    private String startPointLat;
    @SerializedName(ApiKey.START_POINT_LNG)
    private String startPointLng;
    @SerializedName(ApiKey.END_POINT_ADDRESS)
    private String endPointText;
    @SerializedName(ApiKey.END_POINT_LAT)
    private String endPointLat;
    @SerializedName(ApiKey.END_POINT_LNG)
    private String endPointLng;
    @SerializedName(ApiKey.IMAGE)
    private String image;
    @SerializedName(ApiKey.IS_CANCELED)
    private String Canceled;
    @SerializedName(ApiKey.DATE_TIME)
    private String dateTime;
    @SerializedName(ApiKey.DRIVER)
    private User driver;
    @SerializedName(ApiKey.FEEDBACK)
    private List<Feedback> feedbacks;

    public int getId() {
        return id;
    }

    public String getStartPointText() {
        return MyUtils.getString(startPointText);
    }

    public String getStartPointLat() {
        return startPointLat;
    }

    public String getStartPointLng() {
        return startPointLng;
    }

    public String getEndPointText() {
        return MyUtils.getString(endPointText);
    }

    public String getEndPointLat() {
        return endPointLat;
    }

    public String getEndPointLng() {
        return endPointLng;
    }

    public String getImage() {
        return image;
    }

    public String getCanceled() {
        return Canceled;
    }

    public User getDriver() {
        if(driver == null)
            driver = new User();
        return driver;
    }

    public List<Feedback> getFeedbacks() {
        if(feedbacks == null)
            feedbacks = new ArrayList<>();
        return feedbacks;
    }

    public String getDateTime() {
        return DateTimeManager.changeDateFormat(dateTime, DateTimeKey.DATE_TIME_24_FORMAT,DateTimeKey.TIME_24_WITHOUT_SECOND_FORMAT);
    }
}
