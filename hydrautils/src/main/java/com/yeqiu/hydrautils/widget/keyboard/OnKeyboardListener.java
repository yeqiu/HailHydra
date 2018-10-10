package com.yeqiu.hydrautils.widget.keyboard;

/**
 * @author ye
 * @date 2018/4/4
 * @desc 键盘的监听事件
 */
public interface OnKeyboardListener {
    /**
     * 点击数字按键。
     *
     * @param text 输入的数字
     */
    void onInsertKeyEvent(String text);

    /**
     * 点击了删除按键。
     */
    void onDeleteKeyEvent();
}
