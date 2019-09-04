package com.yeqiu.hydra.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.FileProvider;

import com.yeqiu.hydra.HydraUtilsManager;

import java.io.File;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019-08-21
 * @describe：
 * @fix：
 */
public class FileProviderUtils {

    public static Uri getUriForFile( String path) {

        Context context = HydraUtilsManager.getInstance().getContext();

        Uri fileUri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            fileUri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider",
                    new File(path));
        } else {
            fileUri = Uri.fromFile(new File(path));
        }
        return fileUri;
    }

    public static Intent getIntent(String type, String path){

        Context context = HydraUtilsManager.getInstance().getContext();

        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            intent.setDataAndType(getUriForFile( path), type);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            intent.setDataAndType(Uri.fromFile(new File(path)), type);
        }

        return intent;

    }





}
