package bassiouny.ahmed.waslabank.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;

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
    @SerializedName(ApiKey.DRIVER)
    private User driver;
    @SerializedName(ApiKey.FEEDBACK)
    private List<Feedback> feedbacks;

}
