package com.waslla_bank.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by bassiouny on 14/04/18.
 */

public class MyGlideApp {

    public static void setImage(Context context, ImageView image, String url) {
        if (checkValideData(context, url, image))
            Glide.with(context).load(url)
                    .apply(new RequestOptions().placeholder(com.waslla_bank.R.drawable.person).error(com.waslla_bank.R.drawable.person).dontAnimate())
                    .into(image);
    }

    public static void setImageWithoutPlaceholder(Context context, ImageView image, String url) {
        if (checkValideData(context, url, image))
            Glide.with(context).load(url)
                    .into(image);
    }

    public static void setImageCenterCrop(Context context, ImageView image, String url) {
        if (checkValideData(context, url, image))
            Glide.with(context).load(url)
                    .apply(new RequestOptions().placeholder(com.waslla_bank.R.drawable.person).error(com.waslla_bank.R.drawable.person).dontAnimate().centerCrop())
                    .into(image);
    }

    public static void setImageCenterInside(Context context, ImageView image, String url) {
        if (checkValideData(context, url, image))
            Glide.with(context).load(url)
                    .apply(new RequestOptions().placeholder(com.waslla_bank.R.drawable.person).error(com.waslla_bank.R.drawable.person).dontAnimate().centerInside())
                    .into(image);

    }

    private static boolean checkValideData(Context context, String url, ImageView imageView) {
        if (context == null || url == null || url.isEmpty() || imageView == null)
            return false;
        return true;
    }
}
