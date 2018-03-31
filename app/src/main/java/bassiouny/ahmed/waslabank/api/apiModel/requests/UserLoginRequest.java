package bassiouny.ahmed.waslabank.api.apiModel.requests;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;

/**
 * Created by bassiouny on 30/03/18.
 */

@SuppressWarnings({"UnusedDeclaration"})
public class UserLoginRequest {
    @SerializedName(ApiKey.PHONE)
    protected String phone;
    @SerializedName(ApiKey.PASSWORD)
    protected String password;
    @SerializedName(ApiKey.NOTIFICATION_TOKEN)
    protected String notificationToken;

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getNotificationToken() {
        return notificationToken;
    }

    private UserLoginRequest(Builder builder) {
        phone = builder.phone;
        password = builder.password;
        notificationToken = builder.notificationToken;
    }

    public UserLoginRequest() {
    }

    public static final class Builder {
        private String phone;
        private String password;
        private String notificationToken;

        public Builder() {
        }

        public Builder phone(String val) {
            phone = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder notificationToken(String val) {
            notificationToken = val;
            return this;
        }

        public UserLoginRequest build() {
            return new UserLoginRequest(this);
        }
    }
}
