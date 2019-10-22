package com.yeqiu.hydra.widget.headbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yeqiu.hydra.utils.ResourceUtil;
import com.yeqiu.hydrautils.R;


/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019-10-21
 * @describe：
 * @fix：
 */
public class HydraHeadBar extends FrameLayout implements View.OnClickListener {

    private Context context;
    private View headView;
    private ImageView ivHeadBack;
    private TextView tvHeadClose;
    private TextView tvHeadTitle;
    private TextView tvHeadRight;
    private ImageView ivHeadRight;
    private View headLine;
    private OnHeadBarClickListener onHeadBarClickListener;
    private LinearLayout llHeadRoot;


    public HydraHeadBar(@NonNull Context context) {
        this(context, null);
    }

    public HydraHeadBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public HydraHeadBar(@NonNull Context context, @Nullable AttributeSet attrs,
                        int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        init();
    }


    private void init() {

        headView = View.inflate(context, R.layout.header_bar_layout, this);
        ivHeadBack = (ImageView) findViewById(R.id.iv_head_back);
        tvHeadClose = (TextView) findViewById(R.id.tv_head_close);
        tvHeadTitle = (TextView) findViewById(R.id.tv_head_title);
        tvHeadRight = (TextView) findViewById(R.id.tv_head_right);
        ivHeadRight = (ImageView) findViewById(R.id.iv_head_right);
        llHeadRoot = (LinearLayout) findViewById(R.id.ll_head_root);
        headLine = findViewById(R.id.head_line);

        tvHeadRight.setVisibility(GONE);
        ivHeadRight.setVisibility(GONE);
        headLine.setVisibility(GONE);

        ivHeadBack.setOnClickListener(this);
        tvHeadClose.setOnClickListener(this);
        tvHeadRight.setOnClickListener(this);
        ivHeadRight.setOnClickListener(this);

    }

    /**
     * 设置背景色
     * 设置沉浸式占位
     *
     * @param color
     * @return
     */
    public HydraHeadBar setHeadBackgroundColor(@ColorRes int color) {

        headView.setBackgroundResource(color);
        return this;
    }



    /**
     * 设置返回图标
     *
     * @param imgId
     */
    public HydraHeadBar setHeadBackImg(@DrawableRes int imgId) {

        ivHeadBack.setImageResource(imgId);
        ivHeadBack.setVisibility(VISIBLE);
        tvHeadRight.setVisibility(GONE);
        return this;
    }

    /**
     * 显示返回图标
     *
     * @param isShow
     * @return
     */
    public HydraHeadBar showHeadBackImg(boolean isShow) {

        ivHeadBack.setVisibility(isShow ? VISIBLE : GONE);

        return this;
    }

    /**
     * 显示返回文字
     *
     * @param isShow
     * @return
     */
    public HydraHeadBar showHeadBackTextView(boolean isShow) {

        tvHeadClose.setVisibility(isShow ? VISIBLE : GONE);

        return this;
    }


    /**
     * 设置返回文字
     *
     * @param headBackText
     */
    public HydraHeadBar setHeadBackText(String headBackText) {

        tvHeadClose.setText(headBackText);
        ivHeadBack.setVisibility(GONE);
        tvHeadRight.setVisibility(VISIBLE);
        return this;
    }


    /**
     * 设置标题文字
     *
     * @param title
     * @return
     */
    public HydraHeadBar setHeadTitle(String title) {

        tvHeadTitle.setText(title);
        return this;

    }


    /**
     * 设置标题文字颜色
     *
     * @param color
     * @return
     */
    public HydraHeadBar setTitleColor(@ColorRes int color) {

        tvHeadTitle.setTextColor(ResourceUtil.getColor(color));
        return this;
    }


    /**
     * 设置标题文字大小
     *
     * @param size
     * @return
     */
    public HydraHeadBar setTitleSize(int size) {

        tvHeadTitle.setTextSize(size);
        return this;
    }


    /**
     * 设置右侧显示文字
     *
     * @param text
     * @return
     */
    public HydraHeadBar setTextViewOnBarRight(String text) {

        tvHeadRight.setText(text);

        ivHeadRight.setVisibility(GONE);
        tvHeadRight.setVisibility(VISIBLE);

        return this;
    }

    /**
     * 设置右侧显示文字
     *
     * @param size
     * @return
     */
    public HydraHeadBar setTextViewOnBarRightSize(int size) {

        tvHeadRight.setTextSize(size);


        return this;
    }

    /**
     * 设置右侧显示文字
     *
     * @param color
     * @return
     */
    public HydraHeadBar setTextViewOnBarRightColor(@ColorRes int color) {

        tvHeadRight.setTextColor(ResourceUtil.getColor(color));
        ivHeadRight.setVisibility(VISIBLE);
        tvHeadRight.setVisibility(GONE);
        return this;
    }


    public HydraHeadBar showTextViewOnBarRight(boolean isShow) {

        tvHeadRight.setVisibility(isShow ? VISIBLE : GONE);
        return this;
    }


    /**
     * 设置右侧显示图标
     *
     * @param imgId
     * @return
     */
    public HydraHeadBar setImageViewOnBarRight(@DrawableRes int imgId) {

        ivHeadRight.setImageResource(imgId);

        return this;
    }

    public HydraHeadBar showImageViewOnBarRight(boolean isShow) {

        ivHeadRight.setVisibility(isShow ? VISIBLE : GONE);
        return this;
    }

    /**
     * 显示标题栏横线
     *
     * @param color
     * @return
     */
    public HydraHeadBar showHeadLine(@ColorRes int color) {

        headLine.setVisibility(VISIBLE);
        headLine.setBackgroundResource(color);

        return this;
    }


    /**
     * 显示标题栏横线
     *
     * @return
     */
    public HydraHeadBar showHeadLine() {

        headLine.setVisibility(VISIBLE);

        return this;
    }


    public HydraHeadBar setOnHydraHeadBarClickListener(OnHeadBarClickListener onHeadBarClickListener) {

        this.onHeadBarClickListener = onHeadBarClickListener;
        return this;
    }


    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.iv_head_back && onHeadBarClickListener != null) {
            onHeadBarClickListener.onHeadBackClick(true);
            return;
        }
        if (id == R.id.tv_head_close && onHeadBarClickListener != null) {
            onHeadBarClickListener.onHeadBackClick(false);
            return;
        }
        if (id == R.id.tv_head_right && onHeadBarClickListener != null) {
            onHeadBarClickListener.onHeadRightClick(false);
            return;
        }
        if (id == R.id.iv_head_right && onHeadBarClickListener != null) {
            onHeadBarClickListener.onHeadRightClick(true);
            return;
        }


    }


}
