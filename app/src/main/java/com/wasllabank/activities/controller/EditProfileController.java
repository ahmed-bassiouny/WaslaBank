package com.wasllabank.activities.controller;

import android.app.Activity;

import java.io.File;

import com.wasllabank.api.ApiRequests;
import com.wasllabank.api.apiModel.requests.UserSignUpRequest;
import com.wasllabank.interfaces.BaseResponseInterface;
import com.wasllabank.utils.MyUtils;

/**
 * Created by bassiouny on 15/04/18.
 */

public class EditProfileController {
    private Activity activity;
    public final int MY_PERMISSIONS_REQUEST_IMAGE = 2;

    public EditProfileController(Activity activity) {
        this.activity = activity;
    }

    public void editProfile(File file,int UserId, String name, String interesting, BaseResponseInterface anInterface) {
        UserSignUpRequest.Builder builder = new UserSignUpRequest.Builder();
        builder.name(name);
        builder.interesting(interesting);
        if (file == null)
            ApiRequests.editProfile(builder.build(),String.valueOf(UserId), null, anInterface);
        else
            ApiRequests.editProfile(builder.build(),String.valueOf(UserId), MyUtils.convertFileToPart(file), anInterface);
    }
}
