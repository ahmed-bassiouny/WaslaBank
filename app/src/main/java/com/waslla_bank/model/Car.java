package com.waslla_bank.model;

import com.waslla_bank.api.apiModel.ApiKey;
import com.waslla_bank.utils.MyUtils;
import com.google.gson.annotations.SerializedName;

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
