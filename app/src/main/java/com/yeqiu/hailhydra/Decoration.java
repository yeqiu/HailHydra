package com.yeqiu.hailhydra;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/1/11
 * @describe：
 * @fix：
 */
public class Decoration extends RecyclerView.ItemDecoration {

    private int dividerHeight;

    private Paint paint;

    public Decoration(Context context) {

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);

        dividerHeight = 1;


    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.bottom = dividerHeight;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);


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
}
