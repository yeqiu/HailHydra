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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yeqiu.hydra.utils.ViewUtils;
import com.yeqiu.hydra.utils.image.ImageUtils;
import com.yeqiu.hydra.view.dialog.base.HydraBaseDialog;
import com.yeqiu.hydra.view.dialog.bean.ListData;
import com.yeqiu.hydrautils.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/11/23
 * @describe：
 * @fix：
 */
public class ListDialog extends HydraBaseDialog<ListDialog> implements AdapterView
        .OnItemClickListener, View
        .OnClickListener {

    private ImageView ivBack;
    private TextView tvTitle;
    private ListView lvList;
    private int listHeight = -1;
    private int listMaxHeightWhitItem = -1;
    private int listFootViewId = -1;
    private int backImg = R.drawable.head_back_gray;
    private List<ListData> listDatas;


    public ListDialog(Activity context) {
        super(context);
    }

    @Override
    protected Object getDiaologlayoutIdOrView() {
        return R.layout.layout_list_dialog;
    }

    @Override
    protected void initDialog(View view) {

        ivBack = (ImageView) view.findViewById(R.id.iv_list_back);
        tvTitle = (TextView) view.findViewById(R.id.tv_list_title);
        lvList = (ListView) view.findViewById(R.id.lv_list);

        initData();

    }

    private void initData() {

        int listFootViewId = getListFootViewId();
        if (listFootViewId != -1) {
            View view = inflateView(getListFootViewId());
            lvList.addFooterView(view);
        }

        ivBack.setImageResource(getBackImg());
        tvTitle.setText(getTitleText());
        ListAdapter listAdapter = new ListAdapter(getListDatas());
        lvList.setAdapter(listAdapter);

        ivBack.setOnClickListener(this);
        lvList.setOnItemClickListener(this);


        if (getListHeight() != -1) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup
                    .LayoutParams.MATCH_PARENT, getListHeight());
            lvList.setLayoutParams(layoutParams);
        }


        if (getListMaxHeightWhitItem() != -1) {
            ViewUtils.setListViewHeightWhitItem(lvList, getListMaxHeightWhitItem());
        }


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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        List<ListData> listDatas = getListDatas();

        if (position == listDatas.size() && getDialogListener() != null) {
            getDialogListener().onFootClick();
        }

        if (listDatas.size() > position && getDialogListener() != null) {
            String title = listDatas.get(position).getTitle();
            getDialogListener().onItemClick(position, title);
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
                convertView = View.inflate(getContext(), R.layout.item_dialog_list, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            ListData item = getItem(position);

            if (TextUtils.isEmpty(item.getIcon())) {
                holder.ivIcon.setVisibility(View.GONE);
            } else {

                new ImageUtils().load(getContext(), item.getIcon(), holder.ivIcon);
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


    //==========设置数据==========

    /**
     * 列表弹框的列表高度
     */
    public ListDialog setListHeight(int listHeight) {
        this.listHeight = listHeight;
        return this;
    }

    /**
     * 列表弹框的列表最多显示几个item 此属性优先于listHeight
     */
    public ListDialog setListMaxHeightWhitItem(int listMaxHeightWhitItem) {
        this.listMaxHeightWhitItem = listMaxHeightWhitItem;
        return this;
    }

    /**
     * footView
     */
    public ListDialog setListFootViewId(int listFootViewId) {
        this.listFootViewId = listFootViewId;
        return this;
    }

    /**
     * 列表弹框的返回键
     */
    public ListDialog setBackImg(int backImg) {
        this.backImg = backImg;
        return this;
    }

    /**
     * 列表弹框的列表数据
     */
    public ListDialog setListDatas(List<ListData> listDatas) {
        this.listDatas = listDatas;
        return this;
    }

    //==========get()==========


    public int getListHeight() {
        return listHeight;
    }

    public int getListMaxHeightWhitItem() {
        return listMaxHeightWhitItem;
    }

    public int getListFootViewId() {
        return listFootViewId;
    }

    public int getBackImg() {
        return backImg;
    }

    public List<ListData> getListDatas() {
        if (listDatas == null) {
            return new ArrayList<>();
        }
        return listDatas;
    }
}
