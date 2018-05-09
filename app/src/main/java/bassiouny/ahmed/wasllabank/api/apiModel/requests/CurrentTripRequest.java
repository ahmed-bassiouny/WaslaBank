package bassiouny.ahmed.wasllabank.api.apiModel.requests;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.wasllabank.api.apiModel.ApiKey;

/**
 * Created by bassiouny on 21/04/18.
 */

public class CurrentTripRequest {
    
    @SerializedName(ApiKey.START_POINT_LAT)
    private double startPointLat;
    @SerializedName(ApiKey.START_POINT_LNG)
    private double startPointLng;
    private String startDateTime;
    @SerializedName(ApiKey.END_POINT_LAT)
    private double endPointLat;
    @SerializedName(ApiKey.END_POINT_LNG)
    private double endPointLng;
    private String endDateTime;

    public CurrentTripRequest(double startPointLat, double startPointLng, String startDateTime) {
        this.startPointLat = startPointLat;
        this.startPointLng = startPointLng;
        this.startDateTime = startDateTime;
    }
}
