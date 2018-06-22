package com.waslla_bank.model;

import com.waslla_bank.utils.MyUtils;
import com.google.gson.annotations.SerializedName;

import com.waslla_bank.api.apiModel.ApiKey;

/**
 * Created by bassiouny on 30/03/18.
 */

@SuppressWarnings({"UnusedDeclaration"})
public class User extends UserInfo {

    @SerializedName(value = ApiKey.USER_ID, alternate = ApiKey.ID)
    private int id;
    @SerializedName(ApiKey.NAME)
    private String name;
    @SerializedName(ApiKey.IMAGE)
    private String image;
    @SerializedName(ApiKey.PHONE)
    private String phone;
    @SerializedName(ApiKey.EMAIL)
    private String email;
    @SerializedName(ApiKey.CITY)
    private String city;
    @SerializedName(ApiKey.CAR_NUMBER)
    private String carNumber;
    @SerializedName(ApiKey.ONLINE)
    private int online;
    @SerializedName(ApiKey.ACTIVE)
    private int active;
    @SerializedName(ApiKey.APPROVED)
    private int approved;
    @SerializedName(ApiKey.BLOCKED)
    private int blocked;
    @SerializedName(ApiKey.TOKEN)
    private String token;
    @SerializedName(ApiKey.INTERESTING)
    private String interesting;


    public int getId() {
        return id;
    }

    public String getName() {
        return MyUtils.getString(name);
    }

    public String getPhone() {
        return MyUtils.getString(phone);
    }

    public String getEmail() {
        return MyUtils.getString(email);
    }

    public boolean getOnline() {
        return MyUtils.getBoolean(online);
    }

    public boolean getActive() {
        return MyUtils.getBoolean(active);
    }

    public int getApproved() {
        return approved;
    }

    public boolean getBlocked() {
        return MyUtils.getBoolean(blocked);
    }

    public String getToken() {
        return MyUtils.getString(token);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return MyUtils.getString(image);
    }

    public String getCity() {
        return MyUtils.getString(city);
    }

    public String getCarNumber() {
        return MyUtils.getString(carNumber);
    }

    public String getInteresting() {
        return MyUtils.getString(interesting);
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }
}
