package com.yeqiu.hydra.view.adapter;

import androidx.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yeqiu.hydra.R;
import com.yeqiu.hydra.bean.Model.CheckboxData;
import com.yeqiu.hydra.callback.OnCheckedChangeListener;

import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/16
 * @describe：
 * @fix：
 */
public class CheckboxListAdapter extends BaseQuickAdapter<CheckboxData, BaseViewHolder> {


    private List<CheckboxData> datas;
    private OnCheckedChangeListener onCheckedChangeListener;
    private int position;

    public CheckboxListAdapter(int layoutResId, @Nullable List<CheckboxData> datas) {
        super(layoutResId, datas);
        this.datas = datas;
    }

    @Override
    protected void convert(BaseViewHolder helper, final CheckboxData item) {

        CheckBox cbItem = helper.getView(R.id.cb_item);
        TextView tvItemName = helper.getView(R.id.tv_item_name);
        TextView tvItemNumber = helper.getView(R.id.tv_item_number);

        cbItem.setChecked(item.isCheck());
        tvItemName.setText(item.getName());
        tvItemNumber.setText(String.valueOf(item.getMoney()));


        cbItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        this.position = position;
        super.onBindViewHolder(holder, position);

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

}
