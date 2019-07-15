package com.yeqiu.hydra.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/15
 * @describe：
 * @fix：
 */
public class CommonUtils {


    /**
     * 按指定大小，分隔集合，将集合按规定个数分为n个部分
     *
     * @param <T>
     * @param list
     * @param len
     * @return
     */
    public static <T> List<List<T>> splitList(List<T> list, int len) {

        if (list == null || list.isEmpty() || len < 1) {
            return Collections.emptyList();
        }

        List<List<T>> result = new ArrayList<>();

        int size = list.size();
        int count = (size + len - 1) / len;

        for (int i = 0; i < count; i++) {
            List<T> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }

        return result;

    }

}
