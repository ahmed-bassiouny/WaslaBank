package bassiouny.ahmed.waslabank.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
        return MultipartBody.Part.createFormData("profileImage", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
    }

}
