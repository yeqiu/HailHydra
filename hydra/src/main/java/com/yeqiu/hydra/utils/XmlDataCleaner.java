package com.yeqiu.hydra.utils;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * @project：CarWash
 * @author：小卷子
 * @date 2019/3/15
 * @describe：清除xml中默认的数据
 * @fix：
 */
public class XmlDataCleaner {


    public static void clean(TextView... textViews) {

        for (int i = 0; i < textViews.length; i++) {
            textViews[i].setText("");
        }

    }


    public static void clean(ImageView... imageViews) {

        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i].setImageBitmap(null);
        }

    }


}
