package bassiouny.ahmed.wasllabank.model;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.wasllabank.api.apiModel.ApiKey;
import bassiouny.ahmed.wasllabank.utils.MyUtils;

/**
 * Created by bassiouny on 31/03/18.
 */

@SuppressWarnings({"UnusedDeclaration"})
public class Car {
    @SerializedName(ApiKey.CAR_NUMBER)
    private String carNumber;
    @SerializedName(ApiKey.LICENSE_NUMBER)
    private String licenseNumber;
    @SerializedName(ApiKey.CAR_SIZE)
    private String carSize;

    public String getCarNumber() {
        return MyUtils.getString(carNumber);
    }

    public String getLicenseNumber() {
        return MyUtils.getString(licenseNumber);
    }

    public String getCarSize() {
        return MyUtils.getString(carSize);
    }
}
