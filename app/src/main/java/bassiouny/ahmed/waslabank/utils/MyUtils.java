package bassiouny.ahmed.waslabank.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.io.File;

import bassiouny.ahmed.waslabank.activities.view.HomeActivity;
import bassiouny.ahmed.waslabank.activities.view.SignInActivity;
import bassiouny.ahmed.waslabank.activities.view.WaitingAdminActivity;
import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;
import bassiouny.ahmed.waslabank.model.User;
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
    public static MultipartBody.Part convertFileToPart(File file){
        return MultipartBody.Part.createFormData(ApiKey.IMAGE, file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
    }

    public static RequestBody createPartFromString(String str) {
        return RequestBody.create(okhttp3.MultipartBody.FORM, str);
    }

    public static void openHomeScreen(Activity activity, User user){
        // create intent
        Intent intent;

        if(user.getApproved()){
            intent = new Intent(activity,HomeActivity.class);
        }else {
            intent = new Intent(activity,WaitingAdminActivity.class);
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
    public static void openChooserWithGallery(Fragment fragment){
        EasyImage.openChooserWithGallery(fragment,"Select Picture", EasyImage.REQ_SOURCE_CHOOSER);
    }
    // open Chooser With Gallery activity
    public static void openChooserWithGallery(Activity activity){
        EasyImage.openChooserWithGallery(activity,"Select Picture", EasyImage.REQ_SOURCE_CHOOSER);
    }
}
