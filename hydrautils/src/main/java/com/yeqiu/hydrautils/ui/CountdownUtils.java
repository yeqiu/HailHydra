package com.yeqiu.hydrautils.ui;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.yeqiu.hydrautils.utils.ResourceUtil;


/**
 * @project：XMGJ
 * @author：小卷子
 * @date 2018/10/30
 * @describe：倒计时的textview
 * @fix：
 */
public class CountdownUtils {

    /**
     * 默认view的颜色
     */
    private int defColor = UiConfig.getInstance().getDefColor();
    /**
     * 达到指定条件后view的颜色
     */
    private int limitcolor = UiConfig.getInstance().getLimitcolor();
    /**
     * 未达到指定条件view是否可以点击
     */
    private boolean defClickable = UiConfig.getInstance().isDefClickable();
    /**
     * 倒计时的时间
     */
    private long alltime = 60;
    /**
     * 倒计时的间隔
     */
    private long timeInterval = 1;


    private String beforeCountDownText = "";
    private String afterCountDownText = "";


    public CountdownUtils setDefColor(int defColor) {
        this.defColor = defColor;
        return this;

    }

    public CountdownUtils setLimitcolor(int limitcolor) {
        this.limitcolor = limitcolor;
        return this;
    }

    public CountdownUtils setDefClickable(boolean defClickable) {
        this.defClickable = defClickable;
        return this;
    }

    public CountdownUtils setAlltime(long alltime) {
        this.alltime = alltime;
        return this;
    }

    public CountdownUtils setTimeInterval(long timeInterval) {
        this.timeInterval = timeInterval;
        return this;
    }

    public CountdownUtils setBeforeCountDownText(String beforeCountDownText) {
        this.beforeCountDownText = beforeCountDownText;
        return this;
    }

    public CountdownUtils setAfterCountDownText(String afterCountDownText) {
        this.afterCountDownText = afterCountDownText;
        return this;
    }

    public void countDown(final TextView tv, final String endText) {

        CountDownTimer countDownTimer = new CountDownTimer(alltime * 1000,
                timeInterval * 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv.setClickable(defClickable);
                String countDownText = beforeCountDownText + String.valueOf
                        (millisUntilFinished / 1000) + afterCountDownText;
                tv.setText(countDownText);
                tv.setTextColor(ResourceUtil.getColor(defColor));
            }

            @Override
            public void onFinish() {
                tv.setText(endText);
                tv.setTextColor(ResourceUtil.getColor(limitcolor));
                tv.setClickable(true);
            }
        };


        countDownTimer.start();
    }


}
