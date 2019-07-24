package com.yeqiu.hydra.utils;

import android.content.Intent;

import com.yeqiu.hydra.app.HailHaydraApp;
import com.yeqiu.hydra.view.activity.MainActivity;


/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/06/26
 * @describe：activity 跳转
 * @fix：
 */
public class ActivityUtils extends JumpUtils {

    /**
     * 跳转主页
     */
    public static void toMainActivity() {

        Intent intent = new Intent(HailHaydraApp.getInstance(), MainActivity.class);
        jumpToActivityByIntent(intent);
    }


}
