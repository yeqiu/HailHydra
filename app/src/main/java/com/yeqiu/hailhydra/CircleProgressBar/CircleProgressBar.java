package com.yeqiu.hailhydra.CircleProgressBar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.yeqiu.hailhydra.R;


/**
 * @project：CarWash
 * @author：小卷子
 * @date 2019/3/29
 * @describe：
 * @fix：
 */
public class CircleProgressBar extends View {


    private Paint paint;
    //圆环的颜色
    private int roundProgressColor;
    //圆环的宽度
    private float roundWidth;
    private int max;
    //当前进度
    private int progress;


    public CircleProgressBar(Context context) {
        this(context, null);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        initAttrs(context, attrs);

        paint = new Paint();

    }

    private void initAttrs(Context context, AttributeSet attrs) {

        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable
                .RoundProgressBar);
        //获取自定义属性和默认值
        roundProgressColor = mTypedArray.getColor(R.styleable
                .RoundProgressBar_roundProgressColor, Color.GREEN);
        roundWidth = mTypedArray.getDimension(R.styleable.RoundProgressBar_roundWidth, 10);
        max = mTypedArray.getInteger(R.styleable.RoundProgressBar_max, 100);

        mTypedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画最外层的大圆环

        //获取圆心的x坐标
        int centre = getWidth() / 2;
        //圆环的半径
        int radius = (int) (centre - roundWidth / 2);
        //设置圆环的颜色
        paint.setColor(Color.parseColor("#00000000"));
        //设置空心
        paint.setStyle(Paint.Style.STROKE);
        //设置圆环的宽度
        paint.setStrokeWidth(roundWidth);
        //消除锯齿
        paint.setAntiAlias(false);
        //画出圆环
        canvas.drawCircle(centre, centre, radius, paint);

        //画圆弧 ，画圆环的进度

        //设置圆环的宽度
        paint.setStrokeWidth(roundWidth);
        //设置进度的颜色
        paint.setColor(roundProgressColor);


        /**
         * 可以绘制不同效果的圆弧
         * 用于定义的圆弧的形状和大小的界限
         */
        RectF oval = new RectF(centre - radius + roundWidth / 2, centre - radius + roundWidth /
                2, centre + radius - roundWidth / 2, centre + radius - roundWidth / 2);

        paint.setStyle(Paint.Style.STROKE);
        //根据进度画圆弧
        canvas.drawArc(oval, -90, 360 * progress / max, false, paint);

    }


    /**
     * 设置进度的最大值
     *
     * @param max
     */
    public synchronized void setMax(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.max = max;
    }


    /**
     * 设置进度，此为线程安全控件，由于考虑多线的问题，需要同步
     * 刷新界面调用postInvalidate()能在非UI线程刷新
     * @param progress
     */
    public synchronized void setProgress(int progress) {
        if(progress < 0){
            throw new IllegalArgumentException("progress not less than 0");
        }
        if(progress > max){
            progress = max;
        }
        if(progress <= max){
            this.progress = progress;
            postInvalidate();
        }

    }

}
