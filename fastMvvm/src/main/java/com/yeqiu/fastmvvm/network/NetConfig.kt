package com.yeqiu.fastmvvm.network

import com.yeqiu.fastmvvm.BuildConfig


/**
 * @project：FastMvvm
 * @author：小卷子
 * @date 2023/7/8
 * @describe：
 * @fix：
 */
object NetConfig {

    var enableLog = BuildConfig.DEBUG
    val commonHead = HashMap<String,String>()


    fun addCommonHead(vararg head: Pair<String,String>){

        head.forEach {
            commonHead[it.first] = it.second
        }

    }

}