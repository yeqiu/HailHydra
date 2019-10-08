package com.yeqiu.hydra.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.yeqiu.hydra.utils.DensityUtils;
import com.yeqiu.hydra.utils.ResourceUtil;
import com.yeqiu.hydrautils.R;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019-09-29
 * @describe：
 * @fix：
 */
public class RecyclerViewDivider extends RecyclerView.ItemDecoration {
    
    private int space = DensityUtils.dp2px(1);
    public static int MODE_HORIZONTAL = 1;
    public static int MODE_VERTICAL = 2;
    public static int MODE_GRID = 3;
    private int mode = MODE_VERTICAL;
    private int dividerColor = ResourceUtil.getColor(R.color.color_808080);
    private Paint paint;

    public RecyclerViewDivider(int mode) {
        this.mode = mode;
    }


    public RecyclerViewDivider(int space, int mode) {
        this.space = space;
        this.mode = mode;
    }


    public RecyclerViewDivider setSpace(int space) {
        this.space = DensityUtils.dp2px(space);
        return this;
    }

    public RecyclerViewDivider setMode(int mode) {
        this.mode = mode;
        return this;
    }


    public RecyclerViewDivider setDividerColor(int dividerColor) {
        this.dividerColor = ResourceUtil.getColor(dividerColor);
        return this;
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        super.getItemOffsets(outRect, view, parent, state);
        initPaint();

        if (mode == MODE_VERTICAL) {
            //垂直
            outRect.set(0, 0, 0, space);
        } else if (mode == MODE_HORIZONTAL) {
            //水平
            outRect.set(0, 0, space, 0);
        } else if (mode == MODE_GRID) {
            //网格
            int itemPosition = parent.getChildAdapterPosition(view);
            getGridItemOffsets(outRect, itemPosition, parent);
        }

    }


    private void getGridItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {

        int spanCount = getSpanCount(parent);
        int left;
        int top ;
        int right ;
        int bottom ;

        if (isFirstRaw(itemPosition, spanCount)) {
            //第一行
            if (isLastColum(itemPosition, spanCount)) {
                //最后一列 四边全留空
                left = space;
                top = space;
                right = space;
                bottom = space;
            } else {
                //第一行 非第一列  左 顶 底 留空
                left = space;
                top = space;
                right = 0;
                bottom = space;

            }
        } else {
            //非第一行
            if (isLastColum(itemPosition, spanCount)) {
                //其他行的最后一列 左 右 底 留空
                left = space;
                top = 0;
                right = space;
                bottom = space;
            } else {
                //其他行 非最后一列 左 底 留空
                left = space;
                top = 0;
                right = 0;
                bottom = space;
            }

        }

        outRect.set(left, top, right, bottom);

    }


    /**
     * 是否是第一行
     *
     * @param itemPosition
     * @param spanCount    总列数
     * @return
     */
    private boolean isFirstRaw(int itemPosition, int spanCount) {

        return itemPosition < spanCount;

    }

    /**
     * 是否是第一列
     *
     * @return
     */
    private boolean isFirstColum(int itemPosition, RecyclerView parent) {

        if (itemPosition == 0) {
            return true;
        }

        int spanCount = getSpanCount(parent);

        if (itemPosition >= spanCount && itemPosition % spanCount == 0) {
            return true;
        }

        return false;

    }


    /**
     * 是否是最后一列
     *
     * @param itemPosition
     * @param spanCount
     * @return
     */
    private boolean isLastColum(int itemPosition, int spanCount) {


        return (itemPosition + 1) % spanCount == 0;
    }


    private void initPaint() {

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(dividerColor);
        paint.setStyle(Paint.Style.FILL);

    }

    /**
     * 总列数
     *
     * @param parent
     * @return
     */
    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }

        return spanCount;
    }


    /**
     * 总行数
     *
     * @param childCount
     * @param spanCount
     * @return
     */
    private int getRawCount(int childCount, int spanCount) {

        return childCount % spanCount == 0 ? 0 : 1 + childCount / spanCount;
    }


    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent,
                       @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        if (mode == MODE_VERTICAL) {
            drawVertical(c, parent);
        } else if (mode == MODE_HORIZONTAL) {
            drawHorizontal(c, parent);
        } else if (mode == MODE_GRID) {
            drawGrid(c, parent);
        }

    }


    public void drawVertical(Canvas canvas, RecyclerView parent) {

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + space;
            canvas.drawRect(left, top, right, bottom, paint);
        }

    }

    public void drawHorizontal(Canvas canvas, RecyclerView parent) {

        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            int left = child.getRight() + params.rightMargin;
            int right = left + space;
            canvas.drawRect(left, top, right, bottom, paint);
        }
    }

    private void drawGrid(Canvas canvas, RecyclerView parent) {

        int childCount = parent.getChildCount();
        int spanCount = getSpanCount(parent);

        for (int i = 0; i < childCount; i++) {

            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            //绘制底部横线
            int horizontalLeft = 0 - params.leftMargin;
            int horizontalRight = child.getRight() + params.rightMargin + space;
            int horizontalTop = child.getBottom() + params.bottomMargin;
            int horizontalBottom = horizontalTop + space;

            canvas.drawRect(horizontalLeft, horizontalTop, horizontalRight, horizontalBottom,
                    paint);

            boolean isFirstRaw = isFirstRaw(i, spanCount);
            //第一行绘制顶部
            if (isFirstRaw ) {
                horizontalTop = params.topMargin;
                horizontalBottom = space;
                canvas.drawRect(horizontalLeft, horizontalTop, horizontalRight, horizontalBottom,
                        paint);
            }

            //绘制右侧竖线 vertical
            int verticalTop = child.getTop() - params.topMargin;
            int verticalBottom = child.getBottom() + params.bottomMargin;
            int verticalLeft = child.getRight() + params.rightMargin;
            int verticalRight = verticalLeft + space;
            canvas.drawRect(verticalLeft, verticalTop, verticalRight, verticalBottom, paint);

            //第一列绘制左部
            boolean firstColum = isFirstColum(i, parent);
            if (firstColum ) {
                verticalLeft = params.leftMargin;
                verticalRight = space;
                canvas.drawRect(verticalLeft, verticalTop, verticalRight, verticalBottom, paint);
            }
        }

    }


}