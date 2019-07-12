package com.yeqiu.hailhaydra.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.yeqiu.hailhaydra.BuildConfig;
import com.yeqiu.hydra.HydraUtilsManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/13
 * @describe：
 * @fix：
 */
public class HailHaydraApp extends Application {

    private static HailHaydraApp instance;
    private RefWatcher refWatcher;


    List<Activity> activities = new ArrayList<>();

    public HailHaydraApp() {
        instance = this;
    }

    public static synchronized HailHaydraApp getInstance() {
        if (instance == null) {
            instance = new HailHaydraApp();
        }
        return instance;
    }


    public List<Activity> getActivities() {
        if (activities == null) {
            return new ArrayList<>();
        }
        return activities;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        HydraUtilsManager.getInstance().init(this).setCurrentEnvironment(BuildConfig.DEBUG);

        refWatcher = LeakCanary.install(this);


        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                activities.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

    }


    public RefWatcher getRefWatcher() {
        return refWatcher;
    }





}
