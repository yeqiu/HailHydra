package com.yeqiu.hailhaydra.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.yeqiu.hailhaydra.R;
import com.yeqiu.hailhaydra.bean.Model.CheckboxData;
import com.yeqiu.hailhaydra.callback.OnCheckedChangeListener;

import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/11/15
 * @describe：
 * @fix：
 */
public class CheckboxListAdapter extends BaseAdapter {

    private List<CheckboxData> datas;
    private OnCheckedChangeListener onCheckedChangeListener;

    public CheckboxListAdapter(List<CheckboxData> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public CheckboxData getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_checkbox_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final CheckboxData item = getItem(position);

        holder.cbItem.setChecked(item.isCheck());
        holder.tvItemName.setText(item.getName());
        holder.tvItemNumber.setText(String.valueOf(item.getMoney()));


        holder.cbItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //这个可以区分CheckBox的状态是用户触发还是代码修改状态 如果是代码修改直接无视
                if (!buttonView.isPressed()) {
                    return;
                }

               item.setCheck(isChecked);

                if (onCheckedChangeListener != null) {
                    onCheckedChangeListener.onCheckedChange(position, isChecked, item);
                }

            }
        });

        return convertView;
    }


    public void setAllCheck(boolean allCheck) {

        for (int i = 0; i < datas.size(); i++) {
            datas.get(i).setCheck(allCheck);
        }
        notifyDataSetChanged();
    }


    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {

        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    public class ViewHolder {
        CheckBox cbItem;
        TextView tvItemName;
        TextView tvItemNumber;

        public ViewHolder(View view) {
            cbItem = (CheckBox) view.findViewById(R.id.cb_item);
            tvItemName = (TextView) view.findViewById(R.id.tv_item_name);
            tvItemNumber = (TextView) view.findViewById(R.id.tv_item_number);

        }
    }
}
