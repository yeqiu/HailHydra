package com.fastmvvm.sample.network

import com.yeqiu.fastmvvm.network.NetworkResponse

/**
 * @project：FastMvvm
 * @author：小卷子
 * @date 2023/7/11
 * @describe：
 * @fix：
 */
data class ApiResponse<T>(val errorCode:Int, val errorMsg:String, val data:T?):NetworkResponse<T>(){

    override fun isSuccess(): Boolean {
        return errorCode == 0
    }

    override fun getCode(): Int {
        return errorCode
    }

    override fun getResponse(): T? {
        return data
    }

    override fun getErrorMessage(): String {
        return errorMsg
    }


}