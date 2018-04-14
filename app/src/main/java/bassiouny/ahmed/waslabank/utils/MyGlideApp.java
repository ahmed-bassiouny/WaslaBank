package bassiouny.ahmed.waslabank.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import bassiouny.ahmed.waslabank.R;

/**
 * Created by bassiouny on 14/04/18.
 */

public class MyGlideApp {

    public static void setImage(Context context, ImageView image, String url) {
        if (url.isEmpty())
            return;
        Glide.with(context).load(url)
                .apply(new RequestOptions().placeholder(R.drawable.person).error(R.drawable.person).dontAnimate())
                .into(image);
    }
}
