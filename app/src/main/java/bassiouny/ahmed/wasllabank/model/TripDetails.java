package bassiouny.ahmed.wasllabank.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import bassiouny.ahmed.genericmanager.DateTimeManager;
import bassiouny.ahmed.wasllabank.api.apiModel.ApiKey;
import bassiouny.ahmed.wasllabank.utils.DateTimeFormat;
import bassiouny.ahmed.wasllabank.utils.MyUtils;

/**
 * Created by bassiouny on 11/04/18.
 */

public class TripDetails {

    @SerializedName(ApiKey.REQUEST_ID)
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
    @SerializedName(value = ApiKey.DATE_TIME, alternate = ApiKey.START_AT)
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
    @SerializedName(ApiKey.USERS_IN_TRIP)
    private List<UserInTrip> userInTrip;
    @SerializedName(ApiKey.IS_ACCEPTED)
    private boolean isAccepted;

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
        return MyUtils.getString(image);
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
        return DateTimeManager.changeDateFormat(getDateTime(), DateTimeFormat.DATE_TIME_24_FORMAT, DateTimeFormat.TIME_24_WITHOUT_SECOND_FORMAT);
    }

    public String getDateTime() {
        if (dateTime == null)
            dateTime = "";
        return dateTime;
    }

    public String getDate() {
        return DateTimeManager.changeDateFormat(getDateTime(), DateTimeFormat.DATE_TIME_24_FORMAT, DateTimeFormat.DATE);
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

    public void setIsRunning() {
        this.isRunning = 1;
    }

    public void setIsFinished(boolean isFinished) {
        if (isFinished)
            this.isFinished = 1;
        else this.isFinished = 0;
    }

    public List<UserInTrip> getUserInTrip() {
        if (userInTrip == null)
            userInTrip = new ArrayList<>();
        return userInTrip;
    }

    public boolean isAccepted() {
        return isAccepted;
    }
}
