package com.waslla_bank.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;

import java.io.File;

import com.waslla_bank.R;
import com.waslla_bank.activities.view.HomeActivity;
import com.waslla_bank.activities.view.SignUpUserImagesActivity;
import com.waslla_bank.activities.view.WaitingAdminActivity;
import com.waslla_bank.api.apiModel.ApiKey;
import com.waslla_bank.model.User;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * Created by ahmed bassiouny on 26/03/18.
 */

public class MyUtils {


    public static String getString(String str) {
        // this method check if string is null
        if (str == null)
            // return empty string if string is null
            return "";
        // else return the same string
        return str;
    }

    // value => i will send string from activity to another
    public static void openFragment(int container, FragmentActivity fragmentActivity,
                                    Fragment fragment, boolean supportBack,
                                    @Nullable Bundle bundle) {

        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(container, fragment);
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        if (supportBack)
            fragmentTransaction.addToBackStack("back");
        fragmentTransaction.commit();
    }

    // check return type from server and convert int type to boolean
    public static boolean getBoolean(int item) {
        if (item == 1)
            return true;
        return false;
    }

    // convert normal image as file to part for upload to server
    public static MultipartBody.Part convertFileToPart(File file) {
        return MultipartBody.Part.createFormData(ApiKey.IMAGE, file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
    }

    public static RequestBody createPartFromString(String str) {
        return RequestBody.create(okhttp3.MultipartBody.FORM, str);
    }

    public static void openHomeScreen(Activity activity, User user) {
        // create intent
        Intent intent;

        if (user.getApproved() == Constant.OPEN_HOME) {
            intent = new Intent(activity, HomeActivity.class);
        } else if (user.getApproved() == Constant.WAITING_ADMIN) {
            intent = new Intent(activity, WaitingAdminActivity.class);
        }else {
            intent = new Intent(activity, SignUpUserImagesActivity.class);
        }
        // set token in my application
        MyApplication.setUserToken(user.getToken());
        // close splash screen activity
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    // open Chooser With Gallery fragment
    public static void openChooserWithGallery(Fragment fragment) {
        EasyImage.openChooserWithGallery(fragment, "Select Picture", EasyImage.REQ_SOURCE_CHOOSER);
    }

    // open Chooser With Gallery activity
    public static void openChooserWithGallery(Activity activity) {
        EasyImage.openChooserWithGallery(activity, "Select Picture", EasyImage.REQ_SOURCE_CHOOSER);
    }

    // check ig gps enabled
    public static boolean isGpsEnable(Context context) {
        android.location.LocationManager lm = (android.location.LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (lm == null)
            return false;
        return lm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
    }
    // ask open gps activity
    public static void showSettingsAlert(final Context context) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(context.getString(R.string.open_gps));
        alertDialog.setCancelable(false);

        alertDialog.setPositiveButton(context.getString(R.string.setting), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }
    // ask open gps fragment
    public static void showSettingsAlert(final Fragment context) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context.getContext());
        alertDialog.setTitle(context.getString(R.string.open_gps));
        alertDialog.setCancelable(false);

        alertDialog.setPositiveButton(context.getString(R.string.setting), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public static void dialContactPhone(Context context, String phoneNumber) {
        context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
}
