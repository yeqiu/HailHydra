package com.yeqiu.hailhydra;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/1/14
 * @describe：
 * @fix：
 */
public class GridLayoutDecoration extends RecyclerView.ItemDecoration {

    private int dividerHeight = 10;
    private int decorationColor = Color.RED;
    private Paint paint;


    public GridLayoutDecoration() {
        setPaint(decorationColor);
    }


    public GridLayoutDecoration(int dividerHeight) {
        this.dividerHeight = dividerHeight;
        setPaint(decorationColor);
    }


    public GridLayoutDecoration(int dividerHeight, int decorationColor) {
        this.dividerHeight = dividerHeight;
        this.decorationColor = decorationColor;
        setPaint(decorationColor);
    }

    private void setPaint(int decorationColor) {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(decorationColor);

    }


    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        super.getItemOffsets(outRect, itemPosition, parent);

        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        if (isLastRaw(parent, itemPosition, spanCount, childCount)) {
            // 如果是最后一行，不需要绘制底部
            outRect.set(0, 0, dividerHeight, 0);
        } else if (isLastColum(parent, itemPosition, spanCount, childCount)) {
            // 如果是最后一列，则不需要绘制右边
            outRect.set(0, 0, 0, dividerHeight);
        } else {
            //正常绘制
            outRect.set(0, 0, dividerHeight, dividerHeight);
        }

    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }


    /**
     * 获取一共多少列
     *
     * @param parent
     * @return
     */
    private int getSpanCount(RecyclerView parent) {
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {

            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();

        } else if (layoutManager instanceof StaggeredGridLayoutManager) {

            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }

        return spanCount;
    }


    /**
     * 是否是最后一行
     *
     * @param parent
     * @param position
     * @param spanCount
     * @param childCount
     * @return
     */
    private boolean isLastRaw(RecyclerView parent, int position, int spanCount,
                              int childCount) {

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            childCount = childCount - childCount % spanCount;
            if (position >= childCount) {
                // 如果是最后一行，则不需要绘制底部
                return true;
            }

        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (position >= childCount)
                    return true;
            } else {
                // StaggeredGridLayoutManager 且横向滚动
                // 如果是最后一行，则不需要绘制底部
                if ((position + 1) % spanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 是否是最后一列
     *
     * @param parent
     * @param position
     * @param spanCount
     * @param childCount
     * @return
     */
    private boolean isLastColum(RecyclerView parent, int position, int spanCount,
                                int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((position + 1) % spanCount == 0) {
                // 如果是最后一列，则不需要绘制右边
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((position + 1) % spanCount == 0) {
                    // 如果是最后一列，则不需要绘制右边
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                if (position >= childCount) {
                    // 如果是最后一列，则不需要绘制右边
                    return true;
                }

            }
        }
        return false;
    }


    public void drawHorizontal(Canvas c, RecyclerView parent) {
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


    public void drawVertical(Canvas c, RecyclerView parent) {
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
