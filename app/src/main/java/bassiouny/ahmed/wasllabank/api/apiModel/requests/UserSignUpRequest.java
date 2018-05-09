package bassiouny.ahmed.wasllabank.api.apiModel.requests;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.wasllabank.api.apiModel.ApiKey;

/**
 * Created by bassiouny on 30/03/18.
 */

@SuppressWarnings({"UnusedDeclaration"})
public class UserSignUpRequest extends UserLoginRequest {

    @SerializedName(ApiKey.EMAIL)
    private String email;
    @SerializedName(ApiKey.NAME)
    private String name;
    @SerializedName(ApiKey.CAR_NUMBER)
    private String carNumber;
    @SerializedName(ApiKey.LICENSE_NUMBER)
    private String licenseNumber;
    @SerializedName(ApiKey.CAR_SIZE)
    private String carSize;
    @SerializedName(ApiKey.GENDER)
    private String gender;
    @SerializedName(ApiKey.CITY)
    private String city;
    @SerializedName(ApiKey.IDENTIFY_NUMBER)
    private String identifyNumber;
    @SerializedName(ApiKey.INTERESTING)
    private String interesting;

    private UserSignUpRequest(Builder builder) {
        phone = builder.phone;
        password = builder.password;
        email = builder.email;
        name = builder.name;
        notificationToken = builder.notificationToken;
        carNumber = builder.carNumber;
        licenseNumber = builder.licenseNumber;
        carSize = builder.carSize;
        gender = builder.gender;
        city = builder.city;
        identifyNumber = builder.identifyNumber;
        interesting = builder.interesting;
    }


    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getCarSize() {
        return carSize;
    }

    public String getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }

    public String getIdentifyNumber() {
        return identifyNumber;
    }

    public String getInteresting() {
        return interesting;
    }

    public static final class Builder {
        private String phone;
        private String password;
        private String email;
        private String name;
        private String notificationToken;
        private String carNumber;
        private String licenseNumber;
        private String carSize;
        private String gender;
        private String city;
        private String identifyNumber;
        private String interesting;

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

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder notificationToken(String val) {
            notificationToken = val;
            return this;
        }

        public Builder carNumber(String val) {
            carNumber = val;
            return this;
        }

        public Builder licenseNumber(String val) {
            licenseNumber = val;
            return this;
        }

        public Builder carSize(String val) {
            carSize = val;
            return this;
        }

        public Builder gender(String val) {
            gender = val;
            return this;
        }

        public Builder city(String val) {
            city = val;
            return this;
        }

        public Builder identifyNumber(String val) {
            identifyNumber = val;
            return this;
        }

        public Builder interesting(String val) {
            interesting = val;
            return this;
        }

        public UserSignUpRequest build() {
            return new UserSignUpRequest(this);
        }
    }
}
