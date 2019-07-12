package com.yeqiu.hailhaydra.test;

/**
 * @project：HailHydra
 * @author：小卷子
 * @date 2019/7/12
 * @describe：
 * @fix：
 */
public class A<T extends A> {

    public T funA() {
        return (T)this;
    }


}
