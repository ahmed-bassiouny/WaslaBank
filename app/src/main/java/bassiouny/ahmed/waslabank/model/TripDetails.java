package bassiouny.ahmed.waslabank.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import bassiouny.ahmed.genericmanager.DateTimeManager;
import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;
import bassiouny.ahmed.waslabank.utils.DateTimeFormat;
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
    @SerializedName(ApiKey.AVAILABLE_PLACE)
    private String availablePlaces;
    @SerializedName(ApiKey.DRIVER)
    private User driver;
    @SerializedName(ApiKey.FEEDBACK)
    private List<Feedback> feedbacks;
    @SerializedName(ApiKey.IS_JOINED)
    private int isJoined;
    @SerializedName(ApiKey.IS_RUNNING)
    private int isRunning;
    @SerializedName(ApiKey.IS_FINISHED)
    private int isFinished;

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
        if (driver == null)
            driver = new User();
        return driver;
    }

    public List<Feedback> getFeedbacks() {
        if (feedbacks == null)
            feedbacks = new ArrayList<>();
        return feedbacks;
    }

    public String getTime() {
        return DateTimeManager.changeDateFormat(dateTime, DateTimeFormat.DATE_TIME_24_FORMAT, DateTimeFormat.TIME_24_WITHOUT_SECOND_FORMAT);
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getDate() {
        return DateTimeManager.changeDateFormat(dateTime, DateTimeFormat.DATE_TIME_24_FORMAT, DateTimeFormat.DATE);
    }

    public String getAvailablePlaces() {
        return MyUtils.getString(availablePlaces);
    }

    public boolean getIsJoined() {
        if (isJoined == 1)
            return true;
        return false;
    }

    public boolean getIsRunning() {
        if (isRunning == 1)
            return true;
        return false;
    }

    public boolean getIsFinished() {
        if (isFinished == 1)
            return true;
        return false;
    }

    public void setIsJoined(boolean isJoined) {
        if (isJoined)
            this.isJoined = 1;
        else this.isJoined = 0;
    }

    public void setIsRunning(boolean isRunning) {
        if (isRunning)
            this.isRunning = 1;
        else this.isRunning = 0;
    }

    public void setIsFinished(boolean isFinished) {
        if (isFinished)
            this.isFinished = 1;
        else this.isFinished = 0;
    }
}
