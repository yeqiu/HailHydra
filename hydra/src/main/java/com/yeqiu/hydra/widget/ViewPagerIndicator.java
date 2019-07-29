package com.yeqiu.hydra.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yeqiu.hydra.utils.DensityUtils;
import com.yeqiu.hydrautils.R;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.ViewPager;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019-07-29
 * @describe：
 * @fix：
 */
public class ViewPagerIndicator extends FrameLayout {

    private int indicatorWidth;
    private int indicatorHeight;
    private int indicatorMargin;
    private int indicatorUnselectId;
    private int indicatorSelectId;
    private boolean indicatorAnimation = true;

    private List<ImageView> dots;
    private LinearLayout llDot;
    private ImageView ivSelectDot;


    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttribute(attrs);
        init();
    }

    private void initAttribute(AttributeSet attrs) {

        if (attrs == null) {
            return;
        }
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable
                .ViewPagerIndicator);

        indicatorHeight =
                typedArray.getDimensionPixelSize(R.styleable.ViewPagerIndicator_indicator_height,
                        DensityUtils.dp2px(10));
        indicatorWidth =
                typedArray.getDimensionPixelSize(R.styleable.ViewPagerIndicator_indicator_width,
                        DensityUtils.dp2px(10));
        indicatorMargin =
                typedArray.getDimensionPixelSize(R.styleable.ViewPagerIndicator_indicator_margin,
                        DensityUtils.dp2px(10));

        indicatorUnselectId =
                typedArray.getResourceId(R.styleable.ViewPagerIndicator_indicator_unselectId,
                        R.drawable.shape_dot_normal);
        indicatorSelectId =
                typedArray.getResourceId(R.styleable.ViewPagerIndicator_indicator_selectId,
                        R.drawable.shape_dot_selected);

        typedArray.recycle();

    }


    private void init() {

        llDot = new LinearLayout(getContext());
        llDot.setOrientation(LinearLayout.HORIZONTAL);
        addView(llDot);
        ivSelectDot = new ImageView(getContext());
        addView(ivSelectDot);

    }


    public ViewPagerIndicator setIndicatorWidth(int indicatorWidth) {
        this.indicatorWidth = DensityUtils.dp2px(indicatorWidth);
        return this;
    }

    public ViewPagerIndicator setIndicatorHeight(int indicatorHeight) {
        this.indicatorHeight = DensityUtils.dp2px(indicatorHeight);
        return this;
    }

    public ViewPagerIndicator setIndicatorMargin(int indicatorMargin) {
        this.indicatorMargin = DensityUtils.dp2px(indicatorMargin);
        return this;
    }


    public ViewPagerIndicator setIndicatorUnselectId(int indicatorUnselectId) {
        this.indicatorUnselectId = indicatorUnselectId;
        return this;
    }

    public ViewPagerIndicator setIndicatorSelectId(int indicatorSelectId) {
        this.indicatorSelectId = indicatorSelectId;
        return this;
    }

    public ViewPagerIndicator setIndicatorAnimation(boolean indicatorAnimation) {
        this.indicatorAnimation = indicatorAnimation;
        return this;
    }

    public void setViewPager(ViewPager viewPager) {

        int count = viewPager.getAdapter().getCount();

        dots = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            //设置圆点
            ImageView point = new ImageView(getContext());
            point.setBackgroundResource(indicatorUnselectId);
            //每个点设置参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(indicatorWidth,
                    indicatorHeight);
            //设置右边的Margin
            if (i != 0) {
                //除了0之外所有的点向左移动
                params.leftMargin = indicatorMargin;
            }
            point.setLayoutParams(params);
            //添加到点的线性布局中
            llDot.addView(point);
            dots.add(point);
        }

        ViewGroup.LayoutParams layoutParams = ivSelectDot.getLayoutParams();
        layoutParams.width = indicatorWidth;
        layoutParams.height = indicatorHeight;
        ivSelectDot.setLayoutParams(layoutParams);
        ivSelectDot.setBackgroundResource(indicatorSelectId);


        if (indicatorAnimation) {
            viewPager.addOnPageChangeListener(onPageChangeListener);
        }

    }


    private ViewPager.OnPageChangeListener onPageChangeListener =
            new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset,
                                           int positionOffsetPixels) {


                    int left = 0;

                    if (position == llDot.getChildCount() - 1) {
                        //最后一页
                        left =
                                llDot.getChildAt(position).getLeft() - llDot.getChildAt(position - 1).getLeft();
                    } else {
                        left = llDot.getChildAt(position + 1).getLeft() - llDot.getChildAt(position).getLeft();
                    }


                    int leftmargin = (int) (position * left + (positionOffset * left));

                    //params.leftMargin = 两点间滑动距离对应的坐标
                    LayoutParams params = (LayoutParams) ivSelectDot
                            .getLayoutParams();
                    params.leftMargin = leftmargin;
                    ivSelectDot.setLayoutParams(params);

                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            };

}
