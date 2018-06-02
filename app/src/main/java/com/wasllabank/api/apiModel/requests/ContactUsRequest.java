package com.wasllabank.api.apiModel.requests;

import com.google.gson.annotations.SerializedName;

import com.wasllabank.api.apiModel.ApiKey;

/**
 * Created by bassiouny on 12/04/18.
 */

@SuppressWarnings({"UnusedDeclaration"})
public class ContactUsRequest {

    @SerializedName(ApiKey.USER_ID)
    private int userId;
    @SerializedName(ApiKey.NAME)
    private String name;
    @SerializedName(ApiKey.PHONE)
    private String phone;
    @SerializedName(ApiKey.SUBJECT)
    private String subject;
    @SerializedName(ApiKey.MESSAGE)
    private String message;

    private ContactUsRequest(Builder builder) {
        userId = builder.userId;
        name = builder.name;
        phone = builder.phone;
        subject = builder.subject;
        message = builder.message;
    }

    public static final class Builder {
        private int userId;
        private String name;
        private String phone;
        private String subject;
        private String message;

        public Builder() {
        }

        public Builder userId(int val) {
            userId = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder phone(String val) {
            phone = val;
            return this;
        }

        public Builder subject(String val) {
            subject = val;
            return this;
        }

        public Builder message(String val) {
            message = val;
            return this;
        }

        public ContactUsRequest build() {
            return new ContactUsRequest(this);
        }
    }
}
