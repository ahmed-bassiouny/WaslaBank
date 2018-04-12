package bassiouny.ahmed.waslabank.model;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;
import bassiouny.ahmed.waslabank.utils.MyUtils;

/**
 * Created by bassiouny on 31/03/18.
 */

@SuppressWarnings({"UnusedDeclaration"})
public class UserDetails {
    @SerializedName(ApiKey.CURRENT_REQUEST_ID)
    private String currentRequest;
    @SerializedName(ApiKey.IMAGE)
    private String image;
    @SerializedName(ApiKey.IDENTIFY_NUMBER)
    private String identifyNumber;
    @SerializedName(ApiKey.INTERESTING)
    private String interesting;
    @SerializedName(ApiKey.TOTAL_POINT)
    private String totalPoints;
    @SerializedName(ApiKey.TOTAL_RATE)
    private String totalRate;
    @SerializedName(ApiKey.CITY)
    private String city;
    @SerializedName(ApiKey.GENDER)
    private String gender;
    @SerializedName(ApiKey.VERIFICATION_CODE)
    private String verificationCode;
    @SerializedName(ApiKey.CAR)
    private Car car;

    public String getCurrentRequest() {
        return MyUtils.getString(currentRequest);
    }

    public String getImage() {
        return MyUtils.getString(image);
    }

    public String getIdentifyNumber() {
        return MyUtils.getString(identifyNumber);
    }

    public String getInteresting() {
        return MyUtils.getString(interesting);
    }

    public String getTotalPoints() {
        return MyUtils.getString(totalPoints);
    }

    public float getTotalRate() {
        try {
            return Float.parseFloat(MyUtils.getString(totalRate));
        }catch (Exception e){
            return 0;
        }
    }

    public String getCity() {
        return MyUtils.getString(city);
    }

    public String getGender() {
        return MyUtils.getString(gender);
    }

    public String getVerificationCode() {
        return MyUtils.getString(verificationCode);
    }

    public Car getCar() {
        if(car == null)
            car = new Car();
        return car;
    }
}
