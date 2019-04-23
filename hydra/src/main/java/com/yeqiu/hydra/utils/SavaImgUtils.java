package com.yeqiu.hydra.utils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.yeqiu.hydra.HydraUtilsManager;
import com.yeqiu.hydra.utils.thread.ThreadUtil;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @project：XMGJ
 * @author：小卷子
 * @date 2018/12/21
 * @describe：保存图片到本地
 * @fix：
 */
public class SavaImgUtils {


    public static void saveBitmapToGallery(final String url, final SavaListener savaListener) {


        if (TextUtils.isEmpty(url)) {
            if (savaListener != null) {
                savaListener.onFail("url can't be empty !!!");
            }
            return;
        }


        ThreadUtil.runOnChildThread(new Runnable() {
            @Override
            public void run() {
                try {
                    FutureTarget<Bitmap> submit = Glide.with(HydraUtilsManager.getInstance()
                            .getContext())
                            .asBitmap()
                            .load(url)
                            .submit();

                    final Bitmap bitmap = submit.get();

                    saveBitmapToGallery(bitmap, savaListener);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public static void saveBitmapToGallery(Bitmap bitmap, SavaListener savaListener) {


        String name = System.currentTimeMillis() + ".jpg";

        File path = new File(Environment.getExternalStorageDirectory().getPath() +
                "/" + APPInfoUtil.getPackageNameLast());
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
                // 插入图库
//                MediaStore.Images.Media.insertImage(context.getContentResolver(), imgFile
//                        .getAbsolutePath(), imgFile.getName(), null);
                // 发送广播，通知刷新图库的显示
                HydraUtilsManager.getInstance().getContext().sendBroadcast(new Intent(Intent
                        .ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse
                        ("file://" + imgFile.getAbsolutePath())));

                if (savaListener != null) {
                    savaListener.onSuccess();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (savaListener != null) {
                savaListener.onFail("sava erro");
            }

        }

    }


    public interface SavaListener {
        void onSuccess();

        void onFail(String msg);
    }


}
