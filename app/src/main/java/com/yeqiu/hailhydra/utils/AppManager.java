package com.yeqiu.hailhydra.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.yeqiu.hailhydra.BuildConfig;
import com.yeqiu.hailhydra.activity.MainActivity;

import java.util.Iterator;
import java.util.Stack;

/**
 * @project：Xbzd
 * @author：小卷子
 * @date 2018/4/14 上午11:34
 * @describe：管理activity
 * @fix：
 */
public class AppManager {

    // 单例模式
    private static AppManager instance;

    // Activity栈
    private Stack<Activity> activityStack = new Stack<>();

    private AppManager() {
    }


    public static AppManager getAppManager() {

        synchronized (AppManager.class) {
            if (instance == null) {
                instance = new AppManager();
            }
        }

        return instance;
    }


    /**
     * 获取指定index的activity
     * 注：debug模式下如果index不合法会抛出异常
     * 在调用此方法时请务必做NullCheck
     *
     * @param index
     * @return
     */
    public Activity getActivityWithIndex(int index) {

        if (index < 0 || index >= activityStack.size()) {
            //抛出异常
            LogUtils.e("请检查传入的index是否合法");
            if (BuildConfig.DEBUG) {
                throw new IndexOutOfBoundsException("请检查传入的index是否合法,详细内容请打开log查看");
            } else {
                return null;
            }
        }

        Activity activity = activityStack.get(index);
        return activity;
    }


    /**
     * 获取栈
     *
     * @return
     */
    public Stack<Activity> getActivityStack() {
        if (activityStack == null) {
            return new Stack<>();
        }
        return activityStack;
    }

    /**
     * 根据类名获取指定的acitvity
     * * 注：debug模式下如果clazz不合法会抛出异常
     * 在调用此方法时请务必做NullCheck
     *
     * @param clazz
     * @return
     */
    public Activity getActivity(Class<?> clazz) {

        for (Activity activity : activityStack) {
            if (activity.getClass().equals(clazz)) {
                return activity;
            }
        }

        LogUtils.e("请检查传入clazz");
        if (BuildConfig.DEBUG) {
            throw new NoClassDefFoundError("请检查传入clazz，详细内容请打开log查看");
        } else {
            return null;
        }

    }


    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.push(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity getCurrentActivity() {

        return activityStack.lastElement();
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {

        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {

        if (!activityStack.isEmpty()) {
            if (activity != null) {
                activity.finish();
                activityStack.remove(activity);
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {


        Iterator<Activity> iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            Activity next = iterator.next();
            if (next.getClass().equals(cls)) {
                iterator.remove();
                next.finish();
            }
        }


    }

    /**
     * 结束所有Activity
     */
    private void finishAllActivity() {

        Iterator<Activity> iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            Activity next = iterator.next();
            next.finish();
            iterator.remove();
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void exitAPP(Context context) {
        try {
            finishAllActivity();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭除了MainActivity以外的所有activity
     */
    public void finishAllActivityButMain() {

        Iterator<Activity> iterator = activityStack.iterator();

        while (iterator.hasNext()) {

            Activity activity = iterator.next();
            if (activity.getClass().equals(MainActivity.class)) {
                continue;
            }
            activity.finish();
            iterator.remove();
        }

    }


    public void printActivityStack() {

        Log.i("test", "AppManager:printActivityStack, " + activityStack.toString());
    }


}
