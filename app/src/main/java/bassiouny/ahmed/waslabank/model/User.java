package bassiouny.ahmed.waslabank.model;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;
import bassiouny.ahmed.waslabank.utils.MyUtils;

/**
 * Created by bassiouny on 30/03/18.
 */

@SuppressWarnings({"UnusedDeclaration"})
public class User {

    @SerializedName(ApiKey.ID)
    private int id;
    @SerializedName(ApiKey.NAME)
    private String name;
    @SerializedName(ApiKey.PHONE)
    private String phone;
    @SerializedName(ApiKey.EMAIL)
    private String email;
    @SerializedName(ApiKey.PASSWORD)
    private String password;
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
    @SerializedName(ApiKey.USER_DETAILS)
    private UserDetails userDetails;

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

    public String getPassword() {
        return MyUtils.getString(password);
    }
    public boolean getOnline() {
        return MyUtils.getBoolean(online);
    }

    public boolean getActive() {
        return MyUtils.getBoolean(active);
    }

    public boolean getApproved() {
        return MyUtils.getBoolean(approved);
    }

    public boolean getBlocked() {
        return MyUtils.getBoolean(blocked);
    }

    public String getToken() {
        return MyUtils.getString(token);
    }

    public UserDetails getUserDetails() {
        if(userDetails == null)
            userDetails = new UserDetails();
        return userDetails;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
