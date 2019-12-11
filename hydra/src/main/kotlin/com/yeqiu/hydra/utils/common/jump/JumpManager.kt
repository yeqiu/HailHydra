package com.yeqiu.hydra.utils.common.jump

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.provider.Settings
import androidx.fragment.app.FragmentActivity
import com.yeqiu.hydra.HydraUtilsManager
import com.yeqiu.hydra.utils.JumpUtil.jumpTo
import java.io.Serializable


/**
 * @project：GithubApp
 * @author：小卷子
 * @date 2019-11-12
 * @describe：
 * @fix：
 */

/**
 * Intent添加携带的数据
 * @param
 */
fun Intent.putExtras(vararg params: Pair<String, Any>): Intent {
    if (params.isEmpty()) return this
    params.forEach { (key, value) ->
        when (value) {
            is Int -> putExtra(key, value)
            is Byte -> putExtra(key, value)
            is Char -> putExtra(key, value)
            is Long -> putExtra(key, value)
            is Float -> putExtra(key, value)
            is Short -> putExtra(key, value)
            is Double -> putExtra(key, value)
            is Boolean -> putExtra(key, value)
            is Bundle -> putExtra(key, value)
            is String -> putExtra(key, value)
            is IntArray -> putExtra(key, value)
            is ByteArray -> putExtra(key, value)
            is CharArray -> putExtra(key, value)
            is LongArray -> putExtra(key, value)
            is FloatArray -> putExtra(key, value)
            is Parcelable -> putExtra(key, value)
            is ShortArray -> putExtra(key, value)
            is DoubleArray -> putExtra(key, value)
            is BooleanArray -> putExtra(key, value)
            is CharSequence -> putExtra(key, value)
            is Array<*> -> {
                when {
                    value.isArrayOf<String>() ->
                        putExtra(key, value as Array<String?>)
                    value.isArrayOf<Parcelable>() ->
                        putExtra(key, value as Array<Parcelable?>)
                    value.isArrayOf<CharSequence>() ->
                        putExtra(key, value as Array<CharSequence?>)
                    else -> putExtra(key, value)
                }
            }
            is Serializable -> putExtra(key, value)
        }
    }
    return this
}


fun Activity.finishWhitResult(resultCode: Int, vararg params: Pair<String, Any>) {

    this.setResult(resultCode, Intent().putExtras(*params))
    this.finish()
}


object JumpManager {


    const val REQUEST_CODE = 100

    val context: Context by lazy {
        HydraUtilsManager.getInstance().context
    }


    inline fun <reified destination : Activity> startActivity(vararg params: Pair<String, Any>) {

        val destination = destination::class.java
        val intent = Intent(context, destination)
        intent.putExtras(*params)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)

    }


    fun startActivity(intent: Intent) {

        context.startActivity(intent)

    }


    /**
     * 打开手机默认浏览器
     */
    fun jumpToBrowserActivity(url: String) {

        if (url.isEmpty()) {
            return
        }

        try {
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            jumpTo(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * 跳转拨号页面
     *
     * @param number
     */
    fun jumpToDialActivity(number: String) {

        if (number.isEmpty()) {
            return
        }
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
        jumpTo(intent)
    }


    /**
     * 跳转到app的设置页面(包括通知管理、应用权限等)
     */
    fun goSetting() {

        val settingsAction = "android.settings.APPLICATION_DETAILS_SETTINGS"
        val context = HydraUtilsManager.getInstance().context
        val intent = Intent()
            .setAction(settingsAction)
            .setData(
                Uri.fromParts(
                    "package",
                    context.applicationContext.packageName, null
                )
            )
        jumpTo(intent)
    }

    /**
     * 跳转系统的蓝牙设置界面
     */
    fun goBluetoothSetting() {
        val intent = Intent(Settings.ACTION_BLUETOOTH_SETTINGS)
        jumpTo(intent)
    }

    /**
     * 跳转到无限网络界面
     */
    fun goWifiSetting() {
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
        jumpTo(intent)
    }

    /**
     * 跳转到移动网络设置界面
     */
    fun goNetworkSetting() {
        val intent = Intent(Settings.ACTION_DATA_ROAMING_SETTINGS)
        jumpTo(intent)
    }

    /**
     * 跳转位置服务界面【管理已安装的应用程序】
     */
    fun goLocationSettings() {
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        jumpTo(intent)
    }


//----------------------------------------


    inline fun <reified destination : Activity> startActivityForResult(
        origin: FragmentActivity,
        vararg params: Pair<String, Any>,
        crossinline callback: ((result: Intent?) -> Unit)
    ) {

        val destination = destination::class.java
        val intent = Intent(context, destination)
        intent.putExtras(*params)

        val fragmentManager = origin.supportFragmentManager
        val fragment = GhostFragment()
        fragment.init(REQUEST_CODE, intent) { result ->
            callback(result)
            //移除fragment
            fragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss()
        }
        //添加一个空白的fragment
        fragmentManager.beginTransaction().add(fragment, GhostFragment::class.java.simpleName)
            .commitAllowingStateLoss()

    }


}
