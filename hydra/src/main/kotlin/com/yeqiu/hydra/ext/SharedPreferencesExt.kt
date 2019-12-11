package com.yeqiu.hydra.ext

import android.content.Context
import android.content.SharedPreferences
import com.yeqiu.hydra.HydraUtilsManager
import com.yeqiu.hydra.utils.APPInfoUtil
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @project：GithubApp
 * @author：小卷子
 * @date 2019-11-12
 * @describe：
 * @fix：
 */

val SP_NAME: String by lazy {
    APPInfoUtil.getAppName()
}

val context: Context by lazy {
    HydraUtilsManager.getInstance().context
}

val sp: SharedPreferences by lazy {
    context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
}


class SpProperty<T>(private val def: T, private var key: String = "") :
    ReadWriteProperty<Any, T> {


    override fun getValue(thisRef: Any, property: KProperty<*>): T {

        return getData(findProperKey(property))

    }


    private fun getData(key: String): T {

        return with(sp) {
            when (def) {
                is Long -> getLong(key, def)
                is Int -> getInt(key, def)
                is Float -> getFloat(key, def)
                is Boolean -> getBoolean(key, def)
                is String -> getString(key, def)
                else -> throw IllegalArgumentException("SharedPreferences 不支持的类型")
            } as T
        }

    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {

        putData(findProperKey(property), value)

    }


    private fun putData(key: String, value: T) {


        with(sp.edit()) {
            when (value) {
                is Long -> putLong(key, value)
                is Int -> putInt(key, value)
                is Float -> putFloat(key, value)
                is Boolean -> putBoolean(key, value)
                is String -> putString(key, value)
                else -> throw IllegalArgumentException("SharedPreferences 不支持的类型")
            }.apply()
        }
    }

    private fun findProperKey(property: KProperty<*>) = if (key.isEmpty()) property.name else key

}









