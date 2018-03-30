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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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
}
