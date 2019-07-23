package com.yeqiu.hydra.utils;

import android.app.Activity;

import com.yeqiu.hydra.HydraUtilsManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/9/7
 * @describe：
 * @fix：
 */
public class ActivityManager {


    private static ActivityManager instance;

    private List<Activity> activityStack = new ArrayList<>();

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


    private void checkActivityManager() {

        if (getActivityStack().size() == 0) {

            throw new NullPointerException("activityStack的size为0,请检查在base中是否启用ActivityManager");
        }

    }


    /**
     * 获取指定index的activity
     * 注：debug模式下如果index不合法会抛出异常
     * @param index
     * @return
     */
    public Activity getActivityWithIndex(int index) {

        checkActivityManager();

        if (index < 0 || index >= getActivityStack().size()) {
            //抛出异常
            LogUtils.e("请检查传入的index是否合法");
            throw new IndexOutOfBoundsException("请检查传入的index是否合法,详细内容请打开log查看");
        }

        Activity activity = getActivityStack().get(index);
        return activity;
    }


    /**
     * 获取栈
     *
     * @return
     */
    public List<Activity> getActivityStack() {
        if (activityStack == null) {
            return new ArrayList<>();
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

        checkActivityManager();

        for (Activity activity : getActivityStack()) {
            if (activity.getClass().equals(clazz)) {
                return activity;
            }
        }

        LogUtils.e("请检查传入clazz");
        if (HydraUtilsManager.getInstance().isDebug()) {
            throw new NoClassDefFoundError("请检查传入clazz，详细内容请打开log查看");
        } else {
            return null;
        }

    }


    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {

        getActivityStack().add(activity);

    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity getCurrentActivity() {

        checkActivityManager();
        return activityStack.get(getActivityStack().size() - 1);
    }


    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {

        checkActivityManager();

        Activity activity = getActivityStack().get(getActivityStack().size() - 1);
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {

        checkActivityManager();

        activity.finish();
        if (!getActivityStack().isEmpty()) {
            if (activity != null) {
                getActivityStack().remove(activity);
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<? extends Activity> cls) {

        checkActivityManager();

        Iterator<Activity> iterator = getActivityStack().iterator();
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

        checkActivityManager();

        Iterator<Activity> iterator = getActivityStack().iterator();
        while (iterator.hasNext()) {
            Activity next = iterator.next();
            next.finish();
            iterator.remove();
        }
        getActivityStack().clear();
    }

    /**
     * 关闭除了传入以外的所有activity
     */
    public void finishAllActivityButThis(Class<? extends Activity> cls) {

        checkActivityManager();

        Iterator<Activity> iterator = getActivityStack().iterator();

        while (iterator.hasNext()) {

            Activity activity = iterator.next();
            if (activity.getClass().equals(cls)) {
                continue;
            }
            activity.finish();
            iterator.remove();
        }
    }


    /**
     * 回到指定页面，关闭指定页面之后的所有页面
     */
    public void popToAtivity(Class<? extends Activity> activity) {

        checkActivityManager();

        ListIterator<Activity> listIterator;

        for (listIterator = getActivityStack().listIterator(); listIterator.hasNext(); ) {
            // 将游标定位到列表结尾
            listIterator.next();
        }

        while (listIterator.hasPrevious()) {
            Activity previous = listIterator.previous();
            if (previous.getClass().equals(activity)) {
                break;
            }
            previous.finish();
            listIterator.remove();
        }

    }

}
