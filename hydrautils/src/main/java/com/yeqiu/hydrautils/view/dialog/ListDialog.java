package com.yeqiu.hydrautils.view.dialog;

import android.app.Activity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yeqiu.hydrautils.R;
import com.yeqiu.hydrautils.utils.ImageUtils;
import com.yeqiu.hydrautils.view.dialog.base.BaseDialog;
import com.yeqiu.hydrautils.view.dialog.model.ListData;

import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/11/23
 * @describe：
 * @fix：
 */
public class ListDialog extends BaseDialog implements AdapterView.OnItemClickListener, View
        .OnClickListener {

    private ImageView ivBack;
    private TextView tvTitle;
    private ListView lvList;

    public ListDialog(Activity context) {
        super(context);
    }

    @Override
    protected Object getDiaologlayoutIdOrView() {
        return R.layout.layout_list_dialog;
    }

    @Override
    protected void initView(View view) {

        ivBack = (ImageView) view.findViewById(R.id.iv_list_back);
        tvTitle = (TextView) view.findViewById(R.id.tv_list_title);
        lvList = (ListView) view.findViewById(R.id.lv_list);

        initData();

    }

    private void initData() {

        int listFootViewId = dialogBuilder.getListFootViewId();
        if (listFootViewId != -1) {
            View view = inflateView(dialogBuilder.getListFootViewId());
            lvList.addFooterView(view);
        }

        ImageUtils.setSimpleImage(context,dialogBuilder.getBackImg(), ivBack);
        tvTitle.setText(dialogBuilder.getTitleText());
        ListAdapter listAdapter = new ListAdapter(dialogBuilder.getListDatas());
        lvList.setAdapter(listAdapter);

        ivBack.setOnClickListener(this);
        lvList.setOnItemClickListener(this);


        if (dialogBuilder.getListHeight() != -1) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup
                    .LayoutParams.MATCH_PARENT, dialogBuilder.getListHeight());
            lvList.setLayoutParams(layoutParams);
        }


        if (dialogBuilder.getListMaxHeightWhitItem() != -1) {
            setListViewHeight(dialogBuilder.getListMaxHeightWhitItem());
        }


    }

    /**
     * 根据最多显示item设置list的高度
     *
     * @param listMaxHeightWhitItem
     */
    private void setListViewHeight(int listMaxHeightWhitItem) {

        android.widget.ListAdapter listAdapter = lvList.getAdapter();
        if (listAdapter == null) {
            return;
        }
        //获取其中的一项
        View itemView = listAdapter.getView(0, null, lvList);
        //进行这一项的测量，为什么加这一步，具体分析可以参考 https://www.jianshu.com/p/dbd6afb2c890这篇文章
        itemView.measure(0, 0);
        int itemHeight = itemView.getMeasuredHeight();
        int itemCount = listAdapter.getCount();
        LinearLayout.LayoutParams layoutParams = null;
        if (itemCount <= listMaxHeightWhitItem) {
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    itemHeight * itemCount);
        } else if (itemCount > listMaxHeightWhitItem) {
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    itemHeight * listMaxHeightWhitItem);
        }
        lvList.setLayoutParams(layoutParams);

    }


    @Override
    protected void setWindow() {

        Window window = dialog.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.alpha = 0.98f;
        window.setAttributes(lp);
        window.setGravity(Gravity.BOTTOM);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        List<ListData> listDatas = dialogBuilder.getListDatas();


        if (position == listDatas.size() &&  dialogBuilder.getDialogListener() != null) {
            dialogBuilder.getDialogListener().onFootClick();
        }

        if (listDatas.size() > position && dialogBuilder.getDialogListener() != null) {
            String title = listDatas.get(position).getTitle();
            dialogBuilder.getDialogListener().onItemClick(position, title);
        }

        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

    }

    @Override
    public void onClick(View v) {

        dialog.dismiss();
    }


    class ListAdapter extends BaseAdapter {


        private List<ListData> listDatas;

        public ListAdapter(List<ListData> listDatas) {
            this.listDatas = listDatas;
        }

        @Override
        public int getCount() {
            return listDatas.size();
        }

        @Override
        public ListData getItem(int position) {
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
                convertView = View.inflate(context, R.layout.item_dialog_list, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            ListData item = getItem(position);

            if (TextUtils.isEmpty(item.getIcon())) {
                holder.ivIcon.setVisibility(View.GONE);
            } else {
                ImageUtils.setSimpleImage(context,item.getIcon(), holder.ivIcon);
            }

            holder.tvTitle.setText(item.getTitle());


            return convertView;
        }

        class ViewHolder {
            public ImageView ivIcon;
            public TextView tvTitle;

            public ViewHolder(View view) {

                ivIcon = (ImageView) view.findViewById(R.id.iv_list_icon);
                tvTitle = (TextView) view.findViewById(R.id.tv_list_title);
            }
        }
    }
}
