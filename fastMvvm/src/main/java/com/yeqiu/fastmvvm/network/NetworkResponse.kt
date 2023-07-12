package com.yeqiu.fastmvvm.network

/**
 * @project：FastMvvm
 * @author：小卷子
 * @date 2023/7/11
 * @describe：
 * @fix：
 */
abstract class NetworkResponse<T> {

    abstract fun isSuccess():Boolean
    abstract fun getCode():Int
    abstract fun getResponse():T?
    abstract fun getErrorMessage():String

}