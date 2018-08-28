package com.yeqiu.hailhydra.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestOptions;
import com.yeqiu.hailhydra.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * 图片加载框架
 */
public class ImageUtils {


    private static RequestOptions getRequestOptions() {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.hydra)
                .error(R.drawable.hydra);

        return options;
    }


    private static boolean checkUrl(Context context, String url) {

        if (TextUtils.isEmpty(url) || context == null) {
            return false;
        }

        return true;

    }

    /**
     * 加载图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void setImage(Context context, String url, ImageView imageView) {

        if (!checkUrl(context, url)) {
            return;
        }

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

        if (!checkUrl(context, url)) {
            return;
        }

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

        if (!checkUrl(context, url)) {
            return;
        }

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

        if (!checkUrl(context, url)) {
            return;
        }

        RequestOptions options = getRequestOptions()
                .centerCrop();

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }


    /**
     * 保存图片并插入到图库
     *
     * @param context
     * @param url
     */
    public static void savaPic(final Context context, final String url) {


        if (!checkUrl(context, url)) {
            return;
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FutureTarget<Bitmap> submit = Glide.with(context)
                            .asBitmap()
                            .load(url)
                            .submit();

                    Bitmap bitmap = submit.get();
                    saveImageToGallery(context, bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }


    public static void saveImageToGallery(Context context, Bitmap bitmap) {

        String name = System.currentTimeMillis() + ".jpg";

        File path = new File(Environment.getExternalStorageDirectory().getPath() + "/XB");
        if (!path.exists()) {
            path.mkdirs();
        }

        File imgFile = new File(path, name);


        FileOutputStream out;
        try {
            out = new FileOutputStream(imgFile);
            // 格式为 JPEG，照相机拍出的图片为JPEG格式的，PNG格式的不能显示在相册中
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)) {
                out.flush();
                out.close();
//                // 插入图库
//                MediaStore.Images.Media.insertImage(context.getContentResolver(), imgFile
//                        .getAbsolutePath(), imgFile.getName(), null);

                // 发送广播，通知刷新图库的显示
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse
                        ("file://" + imgFile.getAbsolutePath())));

                UIHelper.showToast(context, "保存成功");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * 随机生产文件名
     *
     * @return
     */
    private static String getPicName() {
        return UUID.randomUUID().toString() + ".jpg";
    }


}
