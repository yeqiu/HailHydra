package com.yeqiu.hydra.view.dialog;

import android.text.InputType;

import com.yeqiu.hydra.view.dialog.base.HydraBaseDialog;
import com.yeqiu.hydra.view.dialog.callback.BaseDialogListener;
import com.yeqiu.hydrautils.R;
import com.yeqiu.hydra.view.dialog.bean.ListData;

import java.util.ArrayList;
import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/7/11
 * @describe：
 * @fix：
 */
public class DialogBuilder {


    /**
     * 点击外部是否消失
     */
    private boolean canceledOnTouchOutside = false;
    /**
     * 点击返回是否消失
     */
    private boolean isBackCancel = true;
    /**
     * 设置仅仅只有确认键
     */
    private boolean justConfirm = false;
    /**
     * 标题文字
     */
    private String titleText = "";
    /**
     * 确认键文字
     */
    private String confirmText = "确定";
    /**
     * 取消键文字
     */
    private String cancelText = "取消";
    /**
     * 内容文字
     */
    private String descText = "";
    /**
     * 确认的颜色
     */
    private int confirmColor = R.color.color_808080;
    /**
     * 取消的颜色
     */
    private int cancelColor = R.color.color_808080;
    /**
     * 标题的颜色
     */
    private int titleColor = R.color.color_808080;
    /**
     * 内容的颜色
     */
    private int descColor = R.color.color_808080;
    /**
     * sheet item的颜色
     */
    private int itemColor = R.color.color_1a1a1a;
    /**
     * 标题的文字大小
     */
    private int titleSize = 15;
    /**
     * 内容的文字大小
     */
    private int descSize = 15;
    /**
     * 确认的文字大小
     */
    private int confirmSizer = 15;
    /**
     * 取消的文字大小
     */
    private int cancelSize = 15;
    /**
     * sheet item的文字大小
     */
    private int itemlSize = 15;
    /**
     * 输入框的输入类型
     */
    private int inputType = InputType.TYPE_CLASS_TEXT;
    /**
     * 输入框的提示文字
     */
    private String hintText = "hint提示";
    /**
     * 输入框的文字
     */
    private int inputSize = 15;
    /**
     * Sheet的Item数据
     */
    private List<String> sheetDatas;

    /**
     * 是否是加载框 加载框会执行旋转动画 仅对TipDialog有效
     */
    private boolean isLoading = false;
    /**
     * 图片id 仅对TipDialog有效
     */
    private int iconId = -999;
    /**
     * tip的提示文字 仅对TipDialog有效
     */
    private String tipText;

    /**
     * 加载框的方向 仅对TipDialog有效
     */
    private boolean orientationHorizontal = true;

    /**
     * 加载框的消失的时间，默认30000，
     */
    private int dismissTime = 3000;

    /**
     * 列表弹框的返回键
     */
    private int backImg = R.drawable.head_back_gray;

    /**
     * 列表弹框的列表数据
     */
    private List<ListData> listDatas;

    /**
     * 列表弹框的列表高度
     */
    private int listHeight = -1;
    /**
     * 列表弹框的列表最多显示几个item 此属性优先于listHeight
     */
    private int listMaxHeightWhitItem = -1;


    private int listFootViewId = -1;


    private HydraBaseDialog baseDialog;
    /**
     * dialog监听
     */
    private BaseDialogListener dialogListener;


    public DialogBuilder(HydraBaseDialog baseDialog) {

        this.baseDialog = baseDialog;
    }


    public HydraBaseDialog show() {
        baseDialog.show();
        return baseDialog;
    }

    public DialogBuilder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        this.canceledOnTouchOutside = canceledOnTouchOutside;
        return this;
    }

    public DialogBuilder setBackCancel(boolean backCancel) {
        isBackCancel = backCancel;
        return this;
    }

    public DialogBuilder setJustConfirm(boolean justConfirm) {
        this.justConfirm = justConfirm;
        return this;
    }

    public DialogBuilder setTitleText(String titleText) {
        this.titleText = titleText;
        return this;
    }

    public DialogBuilder setConfirmText(String confirmText) {
        this.confirmText = confirmText;
        return this;
    }

    public DialogBuilder setCancelText(String cancelText) {
        this.cancelText = cancelText;
        return this;
    }

    public DialogBuilder setDescText(String descText) {
        this.descText = descText;
        return this;
    }

    public DialogBuilder setConfirmColor(int confirmColor) {
        this.confirmColor = confirmColor;
        return this;
    }

    public DialogBuilder setCancelColor(int cancelColor) {
        this.cancelColor = cancelColor;
        return this;
    }

    public DialogBuilder setTitleSize(int titleSize) {
        this.titleSize = titleSize;
        return this;
    }

    public DialogBuilder setDescSize(int descSize) {
        this.descSize = descSize;
        return this;
    }

    public DialogBuilder setConfirmSizer(int confirmSizer) {
        this.confirmSizer = confirmSizer;
        return this;
    }

    public DialogBuilder setCancelSize(int cancelSize) {
        this.cancelSize = cancelSize;
        return this;
    }

    public DialogBuilder setInputType(int inputType) {
        this.inputType = inputType;
        return this;
    }

    public DialogBuilder setHintText(String hintText) {
        this.hintText = hintText;
        return this;
    }

    public DialogBuilder setInputSize(int inputSize) {
        this.inputSize = inputSize;
        return this;
    }

    public DialogBuilder setSheetDatas(List<String> sheetDatas) {
        this.sheetDatas = sheetDatas;
        return this;
    }


    public DialogBuilder setOnDialogListener(BaseDialogListener dialogListener) {
        this.dialogListener = dialogListener;
        return this;
    }

    public DialogBuilder setTitleColor(int titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    public DialogBuilder setDescColor(int descColor) {
        this.descColor = descColor;
        return this;
    }

    public DialogBuilder setItemColor(int itemColor) {
        this.itemColor = itemColor;
        return this;
    }

    public DialogBuilder setItemlSize(int itemlSize) {
        this.itemlSize = itemlSize;
        return this;
    }

    public DialogBuilder setIsLoading(boolean loading) {
        isLoading = loading;
        return this;
    }

    public DialogBuilder setIconId(int iconId) {
        this.iconId = iconId;
        return this;
    }


    /**
     * 建议设置少于4个字的提示，超过4个字会以跑马灯形式展示
     *
     * @param tipText
     * @return
     */
    public DialogBuilder setTipText(String tipText) {
        this.tipText = tipText;
        return this;
    }

    public DialogBuilder setOrientationHorizontal(boolean orientationHorizontal) {
        this.orientationHorizontal = orientationHorizontal;
        return this;
    }

    public DialogBuilder setDismissTime(int dismissTime) {
        this.dismissTime = dismissTime;
        return this;
    }


    public DialogBuilder setBackImg(int backImg) {
        this.backImg = backImg;
        return this;
    }


    public DialogBuilder setListDatas(List<ListData> listDatas) {
        this.listDatas = listDatas;
        return this;
    }


    public DialogBuilder setListHeight(int listHeight) {
        this.listHeight = listHeight;
        return this;
    }


    public DialogBuilder setListMaxHeightWhitItem(int listMaxHeightWhitItem) {
        this.listMaxHeightWhitItem = listMaxHeightWhitItem;
        return this;
    }


    public DialogBuilder setListFootViewId(int listFootViewId) {
        this.listFootViewId = listFootViewId;
        return this;
    }

    //----------get()-----------


    public int getListFootViewId() {
        return listFootViewId;
    }

    public int getListHeight() {
        return listHeight;
    }

    public int getListMaxHeightWhitItem() {
        return listMaxHeightWhitItem;
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

    public boolean getCanceledOnTouchOutside() {
        return canceledOnTouchOutside;
    }

    public boolean getIsBackCancel() {
        return isBackCancel;
    }

    public boolean getJustConfirm() {
        return justConfirm;
    }

    public String getTitleText() {
        return titleText == null ? "" : titleText;
    }

    public String getConfirmText() {
        return confirmText == null ? "" : confirmText;
    }

    public String getCancelText() {
        return cancelText == null ? "" : cancelText;
    }

    public String getDescText() {
        return descText == null ? "" : descText;
    }

    public int getConfirmColor() {
        return confirmColor;
    }

    public int getCancelColor() {
        return cancelColor;
    }

    public int getTitleSize() {
        return titleSize;
    }

    public int getDescSize() {
        return descSize;
    }

    public int getConfirmSizer() {
        return confirmSizer;
    }

    public int getCancelSize() {
        return cancelSize;
    }

    public int getInputType() {
        return inputType;
    }

    public String getHintText() {
        return hintText == null ? "" : hintText;
    }

    public int getInputSize() {
        return inputSize;
    }

    public BaseDialogListener getDialogListener() {
        return dialogListener;
    }

    public List<String> getSheetDatas() {
        if (sheetDatas == null) {
            return new ArrayList<>();
        }
        return sheetDatas;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public int getDescColor() {
        return descColor;
    }

    public int getItemColor() {
        return itemColor;
    }

    public int getItemlSize() {
        return itemlSize;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public int getIconId() {
        return iconId;
    }

    public String getTipText() {
        return tipText == null ? "" : tipText;
    }

    public boolean getOrientationHorizontal() {
        return orientationHorizontal;
    }

    public int getDismissTime() {
        return dismissTime;
    }
}
