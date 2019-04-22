package com.yeqiu.hailhaydra.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hydra.utils.ScreenUtils;

import java.util.List;

/**
 * @project：AndroidLbrary
 * @author：小卷子
 * @date 2018/6/26
 * @describe：
 * @fix：
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private List<String> datas;
    private int type = 1;

    public RecyclerViewAdapter( List<String> datas) {


        this.datas = datas;
    }

    /**
     * @param parent
     * @param viewType
     * @return 创建 ViewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview,
                parent, false);


        ViewHolder viewHolder = new ViewHolder(view);

        RecyclerView.LayoutParams layoutParams = (RecyclerView
                .LayoutParams) view.getLayoutParams();

        int screenWidth = ScreenUtils.getScreenWidth();
        layoutParams.width = screenWidth / 4;


        return viewHolder;
    }

    /**
     * @param holder
     * @param position 设置数据
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.tv.setText(datas.get(position));


    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public void setType(int type) {

        this.type = type;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);

            tv = (TextView) itemView.findViewById(R.id.tv_item_recyclerview);
        }
    }


}




