package com.yeqiu.hydra.utils.image.progress;

/**
 * @project：Test
 * @author：小卷子
 * @date 2019/7/23
 * @describe：
 * @fix：
 */
public interface ProgressListener {

    /**
     * 图片加载进度回调
     * @param progress
     */
    void onLoadProgress(boolean isDone, int progress);

    void onLoadFailed();
}
