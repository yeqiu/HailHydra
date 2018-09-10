package com.yeqiu.hailhydra.view.dialog;

import android.text.InputType;

import com.yeqiu.hailhydra.R;
import com.yeqiu.hailhydra.view.dialog.dialoglistener.BaseDialogListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @project：AndroidLbrary
 * @author：小卷子
 * @date 2018/7/11
 * @describe：
 * @fix：
 */
public class DialogBuilder {


    /**
     *点击外部是否消失
     */
    private boolean canceledOnTouchOutside = false;
    /**
     *点击返回是否消失
     */
    private boolean isBackCancel = true;
    /**
     *设置仅仅只有确认键
     */
    private boolean justConfirm = false;
    /**
     *标题文字
     */
    private String titleText = "标题";
    /**
     *确认键文字
     */
    private String confirmText = "确定";
    /**
     *取消键文字
     */
    private String cancelText = "取消";
    /**
     *内容文字
     */
    private String descText = "内容";
    /**
     *确认的颜色
     */
    private int confirmColor = R.color.color_808080;
    /**
     *取消的颜色
     */
    private int cancelColor = R.color.color_808080;
    /**
     *标题的颜色
     */
    private int titleColor = R.color.color_808080;
    /**
     *内容的颜色
     */
    private int descColor = R.color.color_808080;
    /**
     *sheet item的颜色
     */
    private int itemColor = R.color.color_1a1a1a;
    /**
     *标题的文字大小
     */
    private int titleSize = 15;
    /**
     *内容的文字大小
     */
    private int descSize = 15;
    /**
     *确认的文字大小
     */
    private int confirmSizer = 15;
    /**
     *取消的文字大小
     */
    private int cancelSize = 15;
    /**
     *sheet item的文字大小
     */
    private int itemlSize = 15;
    /**
     *输入框的输入类型
     */
    private int inputType = InputType.TYPE_CLASS_TEXT;
    /**
     *输入框的提示文字
     */
    private String hintText = "hint提示";
    /**
     *输入框的文字
     */
    private int inputSize = 15;
    /**
     *Sheet的Item数据
     */
    private List<String> sheetDatas;



    private BaseDialog baseDialog;
    /**
     *dialog监听
     */
    private BaseDialogListener dialogListener;


    public DialogBuilder(BaseDialog baseDialog) {

        this.baseDialog = baseDialog;
    }


    public BaseDialog show() {
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

    //----------get()-----------

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
}
