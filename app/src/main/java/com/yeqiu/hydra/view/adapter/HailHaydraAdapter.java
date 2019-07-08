package com.yeqiu.hydra.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yeqiu.hydra.R;
import com.yeqiu.hydra.bean.Model.ModelHaydraItem;
import com.yeqiu.hydra.utils.image.ImageUtils;

import java.util.List;


/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/13
 * @describe：
 * @fix：
 */
public class HailHaydraAdapter extends RecyclerView.Adapter<HailHaydraAdapter.ViewHolder> {


    private Context context;
    private List<ModelHaydraItem> data;

    public HailHaydraAdapter(Context context, List<ModelHaydraItem> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .item_recyclerview_hail_haydra, parent, false);
        context = view.getContext();

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final ModelHaydraItem modelHaydraItem = data.get(position);


        ImageUtils.setImageWithCenerCrop(context,modelHaydraItem.getImage(), holder.ivItemHaydraIcon);

        holder.tvItemHaydraName.setText(modelHaydraItem.getName());

        holder.llItemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, modelHaydraItem.getActivity()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivItemHaydraIcon;
        TextView tvItemHaydraName;
        LinearLayout llItemRoot;

        public ViewHolder(View view) {
            super(view);
            ivItemHaydraIcon = (ImageView) view.findViewById(R.id.iv_item_haydra_icon);
            tvItemHaydraName = (TextView) view.findViewById(R.id.tv_item_haydra_name);
            llItemRoot = (LinearLayout) view.findViewById(R.id.ll_item_root);

        }
    }
}
