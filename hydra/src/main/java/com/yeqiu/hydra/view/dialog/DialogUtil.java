package com.yeqiu.hydra.view.dialog;

import android.widget.TextView;

import androidx.annotation.ColorRes;

import com.yeqiu.hydra.utils.ResourceUtil;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019-08-26
 * @describe：
 * @fix：
 */
public class DialogUtil {


    public static void setTextView(TextView tv, String text, int size, @ColorRes int colorId) {

        tv.setText(text);
        tv.setTextSize(size);
        tv.setTextColor(ResourceUtil.getColor(colorId));
    }


}
