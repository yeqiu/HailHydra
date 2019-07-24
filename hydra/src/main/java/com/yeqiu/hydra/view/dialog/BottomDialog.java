package com.yeqiu.hydra.view.dialog;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yeqiu.hydra.view.dialog.base.HydraBaseDialog;
import com.yeqiu.hydrautils.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/8
 * @describe：
 * @fix：
 */
public class BottomDialog extends HydraBaseDialog<BottomDialog> implements View.OnClickListener,
        AdapterView.OnItemClickListener {

    private ListView lvBottomDialog;
    private List<String> listDatas;


    public BottomDialog(Activity context) {
        super(context);
    }

    @Override
    protected void setWindow() {

        Window window = dialog.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        window.setGravity(Gravity.BOTTOM);

    }

    @Override
    protected int getstyle() {
        return R.style.sheet_dialog;

    }

    @Override
    protected Object getDiaologlayoutIdOrView() {
        return R.layout.layout_bottom_dialog;
    }

    @Override
    protected void initDialog(View view) {

        lvBottomDialog = (ListView) view.findViewById(R.id.lv_bottom_dialog);

        List<String> datas = getListDatas();

        if (datas == null || datas.size() == 0) {
            return;
        }


        //取消按钮
        View footer = View.inflate(getContext(), R.layout.footer_bottom_dialog, null);
        TextView cancel = (TextView) footer.findViewById(R.id.tv_footer_bottom_dialog);
        cancel.setText(getCancelText());
        lvBottomDialog.addFooterView(footer);
        footer.setOnClickListener(this);

        //设置列表数据
        ListAdapter listAdapter = new ListAdapter(datas);
        lvBottomDialog.setAdapter(listAdapter);
        lvBottomDialog.setOnItemClickListener(this);

    }




    @Override
    public void onClick(View v) {

        if (getDialogListener() != null) {
            getDialogListener().onCanceclClick();
        }
        dismissDialogWhitDelayer();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (getDialogListener() != null) {

            if (getDialogListener() != null) {
                getDialogListener().onItemClick(position, getListDatas().get(position));
            }

            dismissDialogWhitDelayer();

        }
    }


    class ListAdapter extends BaseAdapter {

        private List<String> listDatas;

        public ListAdapter(List<String> listDatas) {
            this.listDatas = listDatas;
        }

        @Override
        public int getCount() {
            return listDatas.size();
        }

        @Override
        public String getItem(int position) {
            return listDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(getContext(), R.layout.item_bottom_dialog, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            String item = getItem(position);
            holder.tvItemBottomDialog.setText(item);


            return convertView;
        }


        class ViewHolder {

            TextView tvItemBottomDialog;


            public ViewHolder(View view) {

                tvItemBottomDialog = (TextView) view.findViewById(R.id.tv_item_bottom_dialog);

            }
        }
    }


    //========设置数据========

    public BottomDialog setListDatas(List<String> listDatas) {
        this.listDatas = listDatas;
        return this;
    }


    //========get()========

    protected List<String> getListDatas() {
        if (listDatas == null) {
            return new ArrayList<>();
        }
        return listDatas;
    }


}
