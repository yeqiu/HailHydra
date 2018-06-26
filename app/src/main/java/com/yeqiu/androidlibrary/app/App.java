package com.yeqiu.androidlibrary.app;

import android.app.Application;

/**
 * @project：android_tools
 * @author：小卷子
 * @date 2018/4/14 下午4:33
 * @describe：
 * @fix：
 */
public class App extends Application {

    private static App app;


    public static synchronized App getInstance() {
        if (app == null) {
            app = new App();
        }
        return app;
    }

}
