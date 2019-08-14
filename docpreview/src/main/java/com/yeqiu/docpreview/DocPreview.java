package com.yeqiu.docpreview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsReaderView;

import java.io.File;

/**
 * @project：
 * @author：小卷子
 * @date 2019-08-14
 * @describe：x5加载本地文档，调用前请确认已申请储存权限
 * @fix：
 */
public class DocPreview extends FrameLayout {


    private TbsReaderView tbsReaderView;

    public DocPreview(Context context) {
        this(context, null);
    }

    public DocPreview(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public DocPreview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public static void init(final Context context) {
        QbSdk.initX5Environment(context, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {

            }

            @Override
            public void onViewInitFinished(boolean b) {

                Toast.makeText(context, "加载" + b, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void openFile(Activity activity, String path) {

        if (tbsReaderView == null) {

            tbsReaderView = new TbsReaderView(activity, new TbsReaderView.ReaderCallback() {
                @Override
                public void onCallBackAction(Integer integer, Object o, Object o1) {

                }
            });

            addView(tbsReaderView, new LinearLayout.LayoutParams(-1, -1));
        }

        String bsReaderTemp = "/storage/emulated/0/TbsReaderTemp";
        File bsReaderTempFile = new File(bsReaderTemp);
        if (!bsReaderTempFile.exists()) {
            boolean mkdir = bsReaderTempFile.mkdir();
            if (!mkdir) {
                Toast.makeText(getContext(), "发生了错误，请稍后重试", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        //加载文件
        Bundle localBundle = new Bundle();
        localBundle.putString("filePath", path);
        localBundle.putString("tempPath", Environment.getExternalStorageDirectory() + "/" +
                "TbsReaderTemp");

        boolean canOpen = tbsReaderView.preOpen(getFileType(path), false);

        if (canOpen) {
            tbsReaderView.openFile(localBundle);
        } else {
            Toast.makeText(getContext(), "无法打开此文件", Toast.LENGTH_SHORT).show();
        }
    }


    /***
     * 获取文件类型
     *
     * @param paramString
     * @return
     */
    private String getFileType(String paramString) {
        String str = "";

        if (TextUtils.isEmpty(paramString)) {
            return str;
        }
        int i = paramString.lastIndexOf('.');
        if (i <= -1) {

            return str;
        }
        str = paramString.substring(i + 1);
        return str;
    }


    public void onDestroy() {
        if (tbsReaderView != null) {
            tbsReaderView.onStop();
        }
    }

}

