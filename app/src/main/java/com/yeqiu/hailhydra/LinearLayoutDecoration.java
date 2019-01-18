package com.yeqiu.hailhydra;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/1/14
 * @describe：线性分割线
 * @fix：
 */
public class LinearLayoutDecoration extends RecyclerView.ItemDecoration {

    public static final int HORIZONTAL = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL = LinearLayoutManager.VERTICAL;


    private int orientation;
    /**
     * 分割线的高度 （当为HORIZONTAL 是分割线的宽度）
     */
    private int dividerHeight = 1;
    private int decorationColor = Color.BLACK;
    private Paint paint;

    public LinearLayoutDecoration() {
        setPaint(decorationColor);
    }

    public LinearLayoutDecoration(int orientation) {
        setOrientation(orientation);
        setPaint(decorationColor);
    }

    public LinearLayoutDecoration(int orientation, int dividerHeight) {
        setOrientation(orientation);
        this.dividerHeight = dividerHeight;
        setPaint(decorationColor);

    }


    public LinearLayoutDecoration(int orientation, int dividerHeight, int decorationColor) {
        setOrientation(orientation);
        this.dividerHeight = dividerHeight;
        this.decorationColor = decorationColor;
        setPaint(decorationColor);
    }


    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalArgumentException("the orientation must be HORIZONTAL or " +
                    "VERTICAL");
        }
        this.orientation = orientation;
    }


    private void setPaint(int decorationColor) {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(decorationColor);

    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {

        super.getItemOffsets(outRect, view, parent, state);

        if (orientation == VERTICAL) {
            outRect.bottom = dividerHeight;
        } else {
            outRect.right = dividerHeight;
        }

    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //  super.onDraw(c, parent, state);

        if (orientation == VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    /**
     * 垂直分割线
     *
     * @param c
     * @param parent
     */
    private void drawVertical(Canvas c, RecyclerView parent) {

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View itemView = parent.getChildAt(i);
            int dividerTop = itemView.getBottom();
            int dividerLeft = parent.getPaddingLeft();
            int dividerBottom = itemView.getBottom() + dividerHeight;
            int dividerRight = parent.getWidth() - parent.getPaddingRight();
            c.drawRect(dividerLeft, dividerTop, dividerRight, dividerBottom, paint);
        }

    }

    /**
     * 水平分割线
     *
     * @param c
     * @param parent
     */
    private void drawHorizontal(Canvas c, RecyclerView parent) {

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View itemView = parent.getChildAt(i);
            int dividerTop = itemView.getTop();
            int dividerLeft = itemView.getRight();
            int dividerBottom = itemView.getBottom();
            int dividerRight = itemView.getRight() + dividerHeight;
            c.drawRect(dividerLeft, dividerTop, dividerRight, dividerBottom, paint);

        }

    }
}
