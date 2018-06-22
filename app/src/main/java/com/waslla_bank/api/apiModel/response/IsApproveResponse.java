package com.waslla_bank.api.apiModel.response;

import com.google.gson.annotations.SerializedName;
import com.waslla_bank.api.apiModel.ApiKey;
import com.waslla_bank.model.About;
import com.waslla_bank.utils.Constant;

public class IsApproveResponse extends ParentResponse<Integer> {

    @SerializedName(ApiKey.DATA)
    private Integer approved;

    @Override
    public Integer getObject() {
        if (approved == null)
            return Constant.WAITING_ADMIN;
        return approved;
    }
}
