package com.yeqiu.hydra.view.dialog.callback;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/7/11b
 * @describe：
 * @fix：
 */
public interface BaseDialogListener {

    /**
     * dialog消失的监听
     */
    void onDialogDismiss();

    /**
     * 确定监听
     */
    void onConfirmClick();

    /**
     * 取消监听
     */
    void onCanceclClick();


    /**
     * 确定监听 带输入框
     *
     * @param inputText
     */
    void onConfirmClick(String inputText);

    /**
     * 取消监听 带输入框
     *
     * @param inputText
     */
    void onCanceclClick(String inputText);


    /**
     * 条目的点击
     *
     * @param position
     */
    void onItemClick(int position, String text);

    /**
     * list foot点击
     */
    void onFootClick();

}
