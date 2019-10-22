package com.yeqiu.hydra.widget.headbar;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019-10-21
 * @describe：
 * @fix：
 */
public interface OnHeadBarClickListener {


    /**
     * 标题栏返回点击
     * @param isImg
     */
    void onHeadBackClick(boolean isImg);

    /**
     * 标题栏右侧点击
     * @param isImg
     */
    void onHeadRightClick(boolean isImg);

}
