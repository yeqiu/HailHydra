package com.yeqiu.hydra.view.dialog.pagergrid;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yeqiu.hydra.utils.image.ImageUtils;
import com.yeqiu.hydra.view.dialog.bean.ListData;
import com.yeqiu.hydrautils.R;

import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/12
 * @describe：
 * @fix：
 */
public class GridPagerAdapter extends BaseAdapter {


    private List<ListData> datas;
    private int curIndex;
    private int pageSize;


    public GridPagerAdapter(List<ListData> datas, int curIndex, int pageSize) {
        this.datas = datas;
        this.curIndex = curIndex;
        this.pageSize = pageSize;
    }


    /**
     * 先判断数据集的大小是否足够显示满本页？datas.size() > (curIndex+1)*pageSize,
     * 如果够，则直接返回每一页显示的最大条目个数pageSize,
     * 如果不够，则有几项返回几,(datas.size() - curIndex * pageSize);(也就是最后一页的时候就显示剩余item)
     */
    @Override
    public int getCount() {


        int size = datas.size() > (curIndex + 1) * pageSize ? pageSize : (datas.size() - curIndex *
                pageSize);

        return size;

    }

    @Override
    public ListData getItem(int position) {

        return datas.get(position + curIndex * pageSize);

    }

    @Override
    public long getItemId(int position) {

        return position + curIndex * pageSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_grid_pager, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ListData item = getItem(position);

        if (TextUtils.isEmpty(item.getIcon())) {
            ImageUtils.setSimpleImage(parent.getContext(), item.getIconId(), holder.ivGridPager);
        } else {
            ImageUtils.setSimpleImage(parent.getContext(), item.getIcon(), holder.ivGridPager);
        }

        holder.tvGridPager.setText(item.getTitle());


        return convertView;
    }


    private class ViewHolder {

        ImageView ivGridPager;
        TextView tvGridPager;

        public ViewHolder(View view) {

            ivGridPager = (ImageView) view.findViewById(R.id.iv_grid_pager);
            tvGridPager = (TextView) view.findViewById(R.id.tv_grid_pager);
        }
    }

}
