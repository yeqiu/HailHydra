package com.yeqiu.easyandroid

import android.util.Log

/**
 * @project：FastMvvm
 * @author：小卷子
 * @date 2023/7/8
 * @describe：
 * @fix：
 */
object Logger {
}

fun Any.log() {
    Log.i("fastmvvm", this.toString())
}

private fun String.log() {

}