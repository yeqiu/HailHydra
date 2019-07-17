package com.yeqiu.hydra.utils.image;

import java.io.File;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/17
 * @describe：
 * @fix：
 */
public interface ImageSavaListener {

    void onSuccess(File file);

    void onFail(String msg);
}
