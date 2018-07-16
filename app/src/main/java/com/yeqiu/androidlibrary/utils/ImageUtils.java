package com.yeqiu.androidlibrary.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yeqiu.androiddome.R;

/**
 * 图片加载框架
 */
public class ImageUtils {


    public static RequestOptions getRequestOptions() {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.hydra)
                .error(R.drawable.hydra);

        return options;
    }


    /**
     * 加载图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void setImage(Context context, String url, ImageView imageView) {


        RequestOptions options = getRequestOptions();
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);


    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void setHeadImage(final Context context, String url, final ImageView imageView) {

        RequestOptions options = getRequestOptions()
                .circleCrop();


        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);

    }


    /**
     * FitCenter
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void setImageFitCenter(Context context, String url, ImageView imageView) {

        RequestOptions options = getRequestOptions()
                .fitCenter();


        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }


    /**
     * FitCenter
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void setImageCenterCrop(Context context, String url, ImageView imageView) {
        RequestOptions options = getRequestOptions()
                .centerCrop();

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }


}
