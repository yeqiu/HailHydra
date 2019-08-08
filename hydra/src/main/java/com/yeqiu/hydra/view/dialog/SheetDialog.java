package com.yeqiu.hydra.view.dialog;

import android.app.Activity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yeqiu.hydra.utils.DensityUtils;
import com.yeqiu.hydra.utils.ResourceUtil;
import com.yeqiu.hydra.utils.ViewUtils;
import com.yeqiu.hydra.view.dialog.base.HydraBaseDialog;
import com.yeqiu.hydrautils.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.ColorRes;


/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/4/17
 * @describe：仿ios底部弹出dialog
 * @fix：
 */
public class SheetDialog extends HydraBaseDialog<SheetDialog> {


    private int itemColor = R.color.color_1a1a1a;
    private int itemlTextSize = 15;
    private List<String> sheetDatas;



    public SheetDialog(Activity context) {
        super(context);
    }

    @Override
    protected int getstyle() {
        return R.style.sheet_dialog;

    }

    @Override
    protected void setWindow() {
        Window window = dialog.getWindow();
        window.getDecorView().setPadding(DensityUtils.dp2px(10), 0, DensityUtils.dp2px
                (10), DensityUtils.dp2px(10));
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        window.setGravity(Gravity.BOTTOM);

    }

    @Override
    protected Object getDiaologlayoutIdOrView() {
        return R.layout.layout_sheet_dialog;
    }

    @Override
    protected void initDialog(View view) {

        TextView title = (TextView) view.findViewById(R.id.tv_sheet_dialog_title);
        LinearLayout list = (LinearLayout) view.findViewById(R.id.ll_sheet_dialog_list);
        TextView cancel = (TextView) view.findViewById(R.id.tv_sheet_dialog_cancel);

        List<String> datas = getSheetDatas();

        if (datas == null || datas.size() == 0) {
            return;
        }

        if (TextUtils.isEmpty(getTitleText())) {
            //不显示标题
            title.setVisibility(View.GONE);
        } else {
            title.setText(getTitleText());
            title.setTextColor(ResourceUtil.getColor(getTitleColor()));
            title.setTextSize(getTitleSize());
        }

        cancel.setTextColor( ResourceUtil.getColor(getCancelColor()));
        setItem(datas, list, cancel);


    }

    private void setItem(final List<String> datas, LinearLayout list, TextView cancel) {

        for (int i = 0; i < datas.size(); i++) {
            final int position = i;
            final String text = datas.get(i);
            final TextView tv = new TextView(getContext());
            tv.setTag(i);
            int padding = DensityUtils.dp2px(10);
            tv.setPadding(padding, padding, padding, padding);
            tv.setGravity(Gravity.CENTER);
            //设置文字
            ViewUtils.setTextView(tv, text, getItemlTextSize(), getItemColor());

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getDialogListener() != null) {
                        getDialogListener().onItemClick(position, text);
                    }
                    //关闭弹窗
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            });

            list.addView(tv);
            if (i != datas.size() - 1) {
                View divider = new View(getContext());
                divider.setBackgroundResource(R.color.color_e1e1e1);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup
                        .LayoutParams.MATCH_PARENT, DensityUtils.dp2px(1));
                list.addView(divider, params);
            }
        }


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getDialogListener() != null) {
                    getDialogListener().onCanceclClick();
                }
                //关闭弹窗
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
    }


    //==========设置数据==========

    /**
     * sheet item的颜色
     */
    public SheetDialog setItemColor(@ColorRes int itemColor) {
        this.itemColor = itemColor;
        return this;
    }

    /**
     * sheet item的文字大小
     */
    public SheetDialog setItemlTextSize(int itemlTextSize) {
        this.itemlTextSize = itemlTextSize;
        return this;
    }

    /**
     * Sheet的Item数据
     */
    public SheetDialog setSheetDatas(List<String> sheetDatas) {
        this.sheetDatas = sheetDatas;
        return this;
    }

    //==========get()==========


    protected int getItemColor() {
        return itemColor;
    }

    protected int getItemlTextSize() {
        return itemlTextSize;
    }

    protected List<String> getSheetDatas() {
        if (sheetDatas == null) {
            return new ArrayList<>();
        }
        return sheetDatas;
    }
}
