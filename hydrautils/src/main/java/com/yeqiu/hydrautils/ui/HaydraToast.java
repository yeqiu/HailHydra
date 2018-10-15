package com.yeqiu.hydrautils.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yeqiu.hydrautils.HydraUtilsManager;
import com.yeqiu.hydrautils.R;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/18
 * @describe：
 * @fix：
 */
public class HaydraToast {


    private static Toast currentToast;

    private static int defDuration = Toast.LENGTH_SHORT;

    private static final String TOAST_TYPEFACE = "sans-serif-condensed";


    /**
     * 默认文字颜色，白色
     */
    @ColorInt
    private static final int DEFAULT_TEXT_COLOR = Color.parseColor("#FFFFFF");


    /**
     * 蓝色
     */
    @ColorInt
    private static final int BLUE_COLOR = Color.parseColor("#3F51B5");

    /**
     * 绿色
     */
    @ColorInt
    private static final int GREEN_COLOR = Color.parseColor("#388E3C");

    /**
     * 黄色
     */
    @ColorInt
    private static final int YELLOW_COLOR = Color.parseColor("#FFA900");

   /**
     * 黄色
     */
    @ColorInt
    private static final int GREY_COLOR = Color.parseColor("#77000000");


    /**
     * 显示蓝色
     *
     * @return
     */
    public static Toast showBlue(@NonNull String message) {

        return showBlue(message, null);

    }


    /**
     * 显示蓝色
     *
     * @return
     */
    public static Toast showBlue(@NonNull String message, Drawable icon) {

        Toast toast = make(message, icon, DEFAULT_TEXT_COLOR, BLUE_COLOR, defDuration);
        toast.show();
        return toast;
    }


    /**
     * 显示绿色
     *
     * @return
     */
    public static Toast showYellow(@NonNull String message) {

        return showYellow(message, null);

    }

    /**
     * 显示绿色
     *
     * @return
     */
    public static Toast showYellow(@NonNull String message, Drawable icon) {

        Toast toast = make(message, icon, DEFAULT_TEXT_COLOR, YELLOW_COLOR, defDuration);
        toast.show();
        return toast;
    }

    /**
     * 显示绿色
     *
     * @return
     */
    public static Toast showGreen(@NonNull String message) {

        return showGreen(message, null);

    }

    /**
     * 显示绿色
     *
     * @return
     */
    public static Toast showGreen(@NonNull String message, Drawable icon) {

        Toast toast = make(message, icon, DEFAULT_TEXT_COLOR, GREEN_COLOR, defDuration);
        toast.show();
        return toast;
    }



    public static Toast showCustomColor(@NonNull String message, Drawable icon,int color){

        Toast toast = make(message, icon, DEFAULT_TEXT_COLOR, color, defDuration);
        toast.show();
        return toast;
    }

     public static Toast showCustomColor(@NonNull String message, int color){

        Toast toast = make(message, null ,DEFAULT_TEXT_COLOR, color, defDuration);
        toast.show();
        return toast;
    }





    /**
     * 显示默认 灰色
     *
     * @return
     */
    public static Toast show(@NonNull String message) {

        return show(message, null);

    }

    /**
     * 显示默认 灰色
     *
     * @return
     */
    public static Toast show(@NonNull String message, Drawable icon) {

        Toast toast = make(message, icon, DEFAULT_TEXT_COLOR, -1, defDuration);
        toast.show();
        return toast;

    }


    /**
     * @param message         消息
     * @param icon            图标
     * @param textColor       文字颜色
     * @param backgroundColor 背景颜色
     * @param duration        事件
     * @return
     */
    @CheckResult
    public static Toast make(@NonNull String message, Drawable icon,
                             @ColorInt int textColor, int backgroundColor, int duration) {

        Context context = HydraUtilsManager.getInstance().getContext();

        if (currentToast == null) {
            currentToast = new Toast(context);
        }

        final View toastLayout = ((LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE)).inflate(R.layout.toast_layout, null);
        final ImageView toastIcon = toastLayout.findViewById(R.id.toast_icon);
        final TextView toastTextView = toastLayout.findViewById(R.id.toast_text);


        //设置背景
        if (backgroundColor != -1) {
            GradientDrawable bg = (GradientDrawable) toastLayout.getBackground();
            bg.setColor(backgroundColor);
        } else {
            //恢复默认
            GradientDrawable bg = (GradientDrawable) toastLayout.getBackground();
            bg.setColor(GREY_COLOR);
        }

        //图标
        if (icon != null) {
            setBackground(toastIcon, icon);
        } else {
            toastIcon.setVisibility(View.GONE);
        }

        toastTextView.setTextColor(textColor);
        toastTextView.setText(message);
        toastTextView.setTypeface(Typeface.create(TOAST_TYPEFACE, Typeface.NORMAL));

        currentToast.setView(toastLayout);
        currentToast.setDuration(duration);
        return currentToast;
    }


    /**
     * 设置背景
     *
     * @param view
     * @param drawable
     */
    public static final void setBackground(@NonNull View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }


    /**
     * 设置位置
     *
     * @param gravity
     * @param xOffset
     * @param yOffset
     * @return
     */
    public static Toast setGravityAndShow(int gravity, int xOffset, int yOffset) {

        if (currentToast == null) {
            throw new NullPointerException("Toast is null,Please check make() ");
        }

        currentToast.setGravity(gravity, xOffset, yOffset);

        return currentToast;

    }


}
