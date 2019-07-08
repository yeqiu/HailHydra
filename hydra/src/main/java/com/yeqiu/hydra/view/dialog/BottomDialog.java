package com.yeqiu.hydra.view.dialog;

import android.app.Activity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yeqiu.hydra.view.dialog.base.BaseDialog;
import com.yeqiu.hydrautils.R;

import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/8
 * @describe：
 * @fix：
 */
public class BottomDialog extends BaseDialog implements View.OnClickListener, AdapterView
        .OnItemClickListener {

    private ListView lvBottomDialog;
    private boolean hasHead;

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

        List<String> datas = dialogBuilder.getSheetDatas();

        if (datas == null || datas.size() == 0) {
            return;
        }


        if (!TextUtils.isEmpty(dialogBuilder.getTitleText())) {
            //显示标题
            View headView = View.inflate(getContext(), R.layout.head_bottom_dialog,
                    null);
            TextView title = (TextView) headView.findViewById(R.id.tv_head_bottom_dialog);
            title.setText(dialogBuilder.getTitleText());
            lvBottomDialog.addHeaderView(headView);
            hasHead = true;
        }

        //取消按钮
        View footer = View.inflate(getContext(), R.layout.footer_bottom_dialog, null);
        TextView cancel = (TextView) footer.findViewById(R.id.tv_footer_bottom_dialog);
        cancel.setText(dialogBuilder.getCancelText());
        lvBottomDialog.addFooterView(footer);
        footer.setOnClickListener(this);

        //设置列表数据

        ListAdapter listAdapter = new ListAdapter(datas);
        lvBottomDialog.setAdapter(listAdapter);
        setListViewHeight(5);

        lvBottomDialog.setOnItemClickListener(this);

    }

    /**
     * 根据最多显示item设置list的高度
     *
     * @param listMaxHeightWhitItem
     */
    private void setListViewHeight(int listMaxHeightWhitItem) {

        android.widget.ListAdapter listAdapter = lvBottomDialog.getAdapter();
        if (listAdapter == null) {
            return;
        }
        //获取其中的一项
        View itemView = listAdapter.getView(0, null, lvBottomDialog);
        //进行这一项的测量，为什么加这一步，具体分析可以参考 https://www.jianshu.com/p/dbd6afb2c890这篇文章
        itemView.measure(0, 0);
        int itemHeight = itemView.getMeasuredHeight();
        int itemCount = listAdapter.getCount();
        LinearLayout.LayoutParams layoutParams = null;
        if (itemCount <= listMaxHeightWhitItem) {
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    itemHeight * itemCount + 10);
        } else if (itemCount > listMaxHeightWhitItem) {
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    itemHeight * listMaxHeightWhitItem + 10);
        }
        lvBottomDialog.setLayoutParams(layoutParams);

    }


    @Override
    public void onClick(View v) {

        if (dialogBuilder.getDialogListener() != null) {
            dialogBuilder.getDialogListener().onCanceclClick();
        }
        dismissDialog();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (dialogBuilder.getDialogListener() != null) {

            if (position == 0) {
                return;
            }

            if (hasHead) {
                position = position - 1;
            }

            if (dialogBuilder.getDialogListener() != null) {
                dialogBuilder.getDialogListener().onItemClick(position, dialogBuilder
                        .getSheetDatas().get(position));
            }

            dismissDialog();

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


}
