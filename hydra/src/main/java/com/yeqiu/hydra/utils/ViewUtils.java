package com.yeqiu.hydra.utils;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/17
 * @describe：
 * @fix：
 */
public class ViewUtils {

    /**
     * 根据item的数据设置list的高度
     *
     * @param maxHeightWhitItem
     */
    public static void setListViewHeightWhitItem( ListView listView,int maxHeightWhitItem) {

        ListAdapter adapter = listView.getAdapter();

        if (adapter == null) {
            return;
        }
        int count = adapter.getCount();
        int height = 0;
        for (int i = 0; i < count; i++) {
            View itemView = adapter.getView(i, null, listView);
            //进行这一项的测量，为什么加这一步，具体分析可以参考 https://www.jianshu.com/p/dbd6afb2c890这篇文章
            itemView.measure(0, 0);
            int itemHeight = itemView.getMeasuredHeight();
            height = height + itemHeight;
            if (i == maxHeightWhitItem-1) {
                break;
            }
        }

        if (height != 0) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup
                    .LayoutParams.MATCH_PARENT, height);
            listView.setLayoutParams(layoutParams);
        }


    }


    /**
     * 根据item的数据设置RecyclerView的高度
     *
     * @param maxHeightWhitItem
     */
    public static void setRecyclerViewHeightWhitItem(RecyclerView recyclerView, int
            maxHeightWhitItem) {

        RecyclerView.Adapter adapter = recyclerView.getAdapter();

        if (adapter == null) {
            return;
        }

        int itemCount = adapter.getItemCount();
        int height = 0;
        for (int i = 0; i < itemCount; i++) {
            RecyclerView.ViewHolder viewHolder = adapter.createViewHolder(recyclerView, adapter
                    .getItemViewType(i));

            adapter.onBindViewHolder(viewHolder, i);
            viewHolder.itemView.measure(
                    View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), View.MeasureSpec
                            .EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            viewHolder.itemView.layout(0, 0, viewHolder.itemView.getMeasuredWidth(),
                    viewHolder.itemView.getMeasuredHeight());
            viewHolder.itemView.setDrawingCacheEnabled(true);
            viewHolder.itemView.buildDrawingCache();

            height = viewHolder.itemView.getMeasuredHeight() + height;
            if (i == maxHeightWhitItem-1) {
                break;
            }
        }

        if (height != 0) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup
                    .LayoutParams.MATCH_PARENT, height);
            recyclerView.setLayoutParams(layoutParams);
        }

    }


}
