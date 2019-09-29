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
import com.yeqiu.hydra.utils.LogUtils;
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


    private int dividerHeight = DensityUtils.dp2px(5);

    public static int MODE_HORIZONTAL = 1;
    public static int MODE_VERTICAL = 2;
    public static int MODE_GRID = 3;
    private int mode = MODE_VERTICAL;
    private int dividerColor = ResourceUtil.getColor(R.color.color_808080);
    private int horizonSpace;
    private int verticalSpace;
    private Paint paint;
    /**
     * 显示最后一个边界
     */
    private boolean showLastBorder = true;

    public RecyclerViewDivider(int mode) {
        this.mode = mode;
    }

    public RecyclerViewDivider setDividerHeight(int dividerHeight) {
        this.dividerHeight = dividerHeight;
        return this;
    }


    public RecyclerViewDivider setDividerColor(int dividerColor) {
        this.dividerColor = dividerColor;
        return this;

    }

    public RecyclerViewDivider setSpace(int horizonSpace, int verticalSpace) {

        this.horizonSpace = horizonSpace;
        this.verticalSpace = verticalSpace;
        return this;
    }


    private void init() {

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(dividerColor);
        paint.setStyle(Paint.Style.FILL);

        horizonSpace = horizonSpace == 0 ? dividerHeight : horizonSpace;
        verticalSpace = verticalSpace == 0 ? dividerHeight : verticalSpace;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        super.getItemOffsets(outRect, view, parent, state);
        init();


        if (mode == MODE_VERTICAL) {
            //垂直
            outRect.set(0, 0, 0, verticalSpace);
        } else if (mode == MODE_HORIZONTAL) {
            //水平
            outRect.set(0, 0, horizonSpace, 0);
        } else if (mode == MODE_GRID) {
            //网格
            getGridItemOffsets(outRect, parent, view);
        }


    }


    private void getGridItemOffsets(Rect outRect, RecyclerView parent, View view) {

        outRect.set(horizonSpace, verticalSpace, horizonSpace, verticalSpace);
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

        childCount = showLastBorder ? childCount : childCount - 1;

        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + verticalSpace;
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
            int right = left + verticalSpace;
            canvas.drawRect(left, top, right, bottom, paint);
        }
    }

    private void drawGrid(Canvas canvas, RecyclerView parent) {


//        outRect.set(horizonSpace, verticalSpace, horizonSpace, verticalSpace);

        int childCount = parent.getChildCount();

        int spanCount = getSpanCount(parent);
        int lineCount = getLineCount(parent);
        LogUtils.i("列数 =" + spanCount);
        LogUtils.i("行数 =" + lineCount);


    }


    /**
     * 获取列数
     *
     * @param parent
     * @return
     */
    private int getSpanCount(RecyclerView parent) {
        int spanCount = 1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }

        return spanCount;
    }


    private int getLineCount(RecyclerView parent) {

        int lineCount = 1;

        lineCount = parent.getChildCount() / getSpanCount(parent);

        if (parent.getChildCount() % getSpanCount(parent) != 0) {
            lineCount = lineCount + 1;
        }


        return lineCount;
    }


    private boolean isLastColum(RecyclerView parent, int pos, int spanCount,
                                int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            // 如果是最后一列，则不需要绘制右边
            if ((pos + 1) % spanCount == 0) {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                // 如果是最后一列，则不需要绘制右边
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一列，则不需要绘制右边
                if (pos >= childCount) {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,
                              int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount) {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount) {
                    return true;
                }
            } else
            // StaggeredGridLayoutManager 且横向滚动
            {
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }

}