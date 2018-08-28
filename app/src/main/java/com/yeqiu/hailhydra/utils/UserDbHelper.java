package com.yeqiu.hailhydra.utils;

import android.content.ContentValues;

import com.yeqiu.hailhydra.data.User;

import org.litepal.LitePal;

import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2018/8/28
 * @describe：
 * @fix：
 */
public class UserDbHelper {


    public static boolean addUserData(User user) {

        boolean success = user.save();

        return success;

    }


    public static void update(Class<?> modelClass, ContentValues values, long id) {

        LitePal.update(modelClass, values, id);

    }


    public static void delete(Class<?> modelClass, long id) {

        LitePal.delete(modelClass, id);

    }

    public static <T> T find(Class<T> modelClass, long id) {

        T t = LitePal.find(modelClass, id);

        return t;

    }

    public static <T> T findFirst(Class<T> modelClass) {


        T t = LitePal.findFirst(modelClass);
        return t;

    }

    public static <T> List<T> finAll(Class<T> modelClass) {

        List<T> tList = LitePal.findAll(modelClass);

        return tList;

    }


}
