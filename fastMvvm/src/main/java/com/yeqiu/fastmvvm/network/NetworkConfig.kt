package com.yeqiu.fastmvvm.network

import com.yeqiu.fastmvvm.BuildConfig
import com.yeqiu.fastmvvm.exception.NetworkException


/**
 * @project：FastMvvm
 * @author：小卷子
 * @date 2023/7/8
 * @describe：
 * @fix：
 */
object NetworkConfig {

    var enableLog = BuildConfig.DEBUG
    val commonHead = HashMap<String, String>()
    var onNetworkError: (NetworkException) -> Unit = {}

    fun addCommonHead(vararg head: Pair<String, String>) {
        head.forEach {
            commonHead[it.first] = it.second
        }

    }

}