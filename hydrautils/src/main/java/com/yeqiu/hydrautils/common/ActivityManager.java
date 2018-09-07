package com.yeqiu.hydrautils.common;

import android.app.Activity;

import java.util.Iterator;
import java.util.Stack;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/7
 * @describe：
 * @fix：
 */
public class ActivityManager {


    private static ActivityManager instance;

    // Activity栈
    private Stack<Activity> activityStack = new Stack<>();

    private ActivityManager() {
    }


    public static ActivityManager getAppManager() {

        synchronized (ActivityManager.class) {
            if (instance == null) {
                instance = new ActivityManager();
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

            throw new IndexOutOfBoundsException("请检查传入的index是否合法,详细内容请打开log查看");
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

        throw new NoClassDefFoundError("请检查传入clazz，详细内容请打开log查看");

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
     * 关闭除了传入以外的所有activity
     */
    public void finishAllActivityButThis(Class clazz) {

        Iterator<Activity> iterator = activityStack.iterator();

        while (iterator.hasNext()) {

            Activity activity = iterator.next();
            if (activity.getClass().equals(clazz)) {
                continue;
            }
            activity.finish();
            iterator.remove();
        }

    }


}
