package com.yeqiu.hailhaydra.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hailhaydra.bean.Model.ModelHaydraItem;
import com.yeqiu.hydra.utils.JumpUtils;

import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/10/9
 * @describe：
 * @fix：
 */
public class OtherListAdapter extends RecyclerView.Adapter<OtherListAdapter.ViewHolder> {


    private List<ModelHaydraItem> data;

    public OtherListAdapter(List<ModelHaydraItem> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_other_list, null);

        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final ModelHaydraItem modelHaydraItem = data.get(position);
        holder.bt.setText(modelHaydraItem.getName());

        holder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class activity = modelHaydraItem.getActivity();
                JumpUtils.jumpToActivityByClass(activity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private Button bt;

        public ViewHolder(View itemView) {
            super(itemView);
            bt = itemView.findViewById(R.id.bt_item_other);

        }
    }


}
