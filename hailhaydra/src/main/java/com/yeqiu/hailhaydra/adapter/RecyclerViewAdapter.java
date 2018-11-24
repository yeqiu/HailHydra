package com.yeqiu.hailhaydra.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hydrautils.utils.ImageUtils;

import java.util.ArrayList;

/**
 * @project：AndroidLbrary
 * @author：小卷子
 * @date 2018/6/26
 * @describe：
 * @fix：
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private Context context;
    private ArrayList<Integer> datas;
    private int type = 1;

    public RecyclerViewAdapter(Context context, ArrayList<Integer> datas) {

        this.context = context;
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
        View view = View.inflate(context, R.layout.item_recyclerview, null);

        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    /**
     * @param holder
     * @param position 设置数据
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int imgId = datas.get(position);


        ImageUtils.setImageWithfitCenter(imgId, holder.iv);

        holder.tv.setText("position " + position);

        if (type == 4) {
            holder.tv.setVisibility(View.GONE);

        }


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


        private ImageView iv;
        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);

            iv = (ImageView) itemView.findViewById(R.id.iv_item_recyclerview);
            tv = (TextView) itemView.findViewById(R.id.tv_item_recyclerview);
        }
    }


}




