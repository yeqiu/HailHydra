package com.yeqiu.easyandroid

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import java.io.Serializable

/**
 * @project：FastMvvm
 * @author：小卷子
 * @date 2023/7/12
 * @describe：
 * @fix：
 */
inline fun <reified T> Context.toActivity(vararg params: Pair<String, Any>) {

    val intent = Intent(this, T::class.java)
    putParams(intent, *params)
    if (this !is Activity){
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    startActivity(intent)
}

fun putParams(intent: Intent, vararg params: Pair<String, Any>) {


    params.forEach {
        when (val value = it.second) {
            is Int -> {intent.putExtra(it.first,value)}
            is Long -> {intent.putExtra(it.first,value)}
            is Double ->{intent.putExtra(it.first,value)}
            is Boolean ->{intent.putExtra(it.first,value)}
            is String -> {intent.putExtra(it.first,value)}
            is Serializable -> {intent.putExtra(it.first,value)}
            is Parcelable -> {intent.putExtra(it.first,value)}
        }
    }

}
