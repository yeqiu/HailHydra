package com.yeqiu.hydra.utils.image.progress;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/23
 * @describe：
 * @fix：
 */
public interface ProgressListener {

    /**
     * 图片加载进度回调
     *
     * @param isDone
     * @param progress
     */
    void onLoadProgress(boolean isDone, int progress);

    /**
     * 加载失败
     */
    void onLoadFailed();
}
