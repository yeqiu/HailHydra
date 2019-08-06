package com.yeqiu.hydra.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;


/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019-08-05
 * @describe：自适应高度GirdView
 * @fix：
 */
public class FullGridView extends GridView {
    public FullGridView(Context context) {
        super(context);
    }

    public FullGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    /**+
     * 设置gridView 不滚动
     */
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }

}
