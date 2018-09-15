package com.yeqiu.hydrautils.common;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.yeqiu.hydrautils.HydraUtilsManager;
import com.yeqiu.hydrautils.ui.UiConfig;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/4/14
 * @describe：图片加载框架
 * @fix：
 */
public class ImageUtils {

    private static RequestOptions getRequestOptions() {

        return new RequestOptions()
                .placeholder(UiConfig.getInstance().getImgPlaceholder())
                .error(UiConfig.getInstance().getImgError())
                .diskCacheStrategy(DiskCacheStrategy.ALL);
    }


    /**
     * 以填满整个ImageView，将原图的中心对准ImageView的中心，等比例放大原图，直到填满ImageView为止（ImageView
     * 的宽和高都要填满），原图超过ImageView的部分作裁剪处理。
     *
     * @param url
     * @param imageView
     */
    public static void setImageWithCenerCrop(String url, ImageView imageView) {

        if (TextUtils.isEmpty(url)) {
            return;
        }

        Context context = HydraUtilsManager.getInstance().getContext();

        RequestOptions options = getRequestOptions().centerCrop();

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    /**
     * 以填满整个ImageView，将原图的中心对准ImageView的中心，等比例放大原图，直到填满ImageView为止（ImageView
     * 的宽和高都要填满），原图超过ImageView的部分作裁剪处理。
     *
     * @param ImgId
     * @param imageView
     */
    public static void setImageWithCenerCrop(int ImgId, ImageView imageView) {


        Context context = HydraUtilsManager.getInstance().getContext();

        RequestOptions options = getRequestOptions().centerCrop();

        Glide.with(context)
                .load(ImgId)
                .apply(options)
                .into(imageView);
    }


    /**
     * 保持原图比例放大图片去填充View
     *
     * @param url
     * @param imageView
     */
    public static void setImageWithfitCenter(String url, ImageView imageView) {

        if (TextUtils.isEmpty(url)) {
            return;
        }

        Context context = HydraUtilsManager.getInstance().getContext();

        RequestOptions options = getRequestOptions().fitCenter();

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }


    /**
     * 裁剪成圆图
     *
     * @param url
     * @param imageView
     */
    public static void setCircleImage(String url, final ImageView
            imageView) {

        if (TextUtils.isEmpty(url)) {
            return;
        }

        Context context = HydraUtilsManager.getInstance().getContext();

        RequestOptions options = getRequestOptions().circleCrop();

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);

    }


    /**
     * 设置背景
     * @param url
     * @param imageView
     */
    public static void setBg(String url, final ImageView imageView) {

        Glide.with(HydraUtilsManager.getInstance().getContext())
                .load(url)
                .apply(getRequestOptions())
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition
                            <? super Drawable> transition) {

                        if (Build.VERSION.SDK_INT >= 16) {
                            imageView.setBackground(resource);
                        } else {
                            imageView.setBackgroundDrawable(resource);
                        }

                    }
                });
    }
    /**
     * 设置背景
     * @param id
     * @param imageView
     */
    public static void setBg(int id, final ImageView imageView) {

        Glide.with(HydraUtilsManager.getInstance().getContext())
                .load(id)
                .apply(getRequestOptions())
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition
                            <? super Drawable> transition) {

                        if (Build.VERSION.SDK_INT >= 16) {
                            imageView.setBackground(resource);
                        } else {
                            imageView.setBackgroundDrawable(resource);
                        }

                    }
                });
    }


}
