package com.yeqiu.easyandroid

import android.util.Log
import com.yeqiu.easyandroid.Logger.flagE
import com.yeqiu.easyandroid.Logger.flagI
import com.yeqiu.easyandroid.Logger.logTag
import com.yeqiu.easyandroid.Logger.space
import org.json.JSONArray
import org.json.JSONObject


/**
 * @project：FastMvvm
 * @author：小卷子
 * @date 2023/7/8
 * @describe：
 * @fix：
 */
object Logger {

    const val space = "       "
    const val flagI = "i"
    const val flagE = "e"
    var enabledLog = BuildConfig.DEBUG
    var logTag = "easyandroid_log"


}

fun String.log() {

    if (!Logger.enabledLog) {
        return
    }
    runCatching {
        val taskName = getTaskName(4)
        printTopLine(flagI, taskName, logTag)
        Log.i(logTag, space + this)
        printBottomLine(flagI, logTag)
    }.onFailure {
        Log.e(logTag, "$space 日志输出错误")
    }

}


fun String.logE() {
    if (!Logger.enabledLog) {
        return
    }
    runCatching {
        val taskName = getTaskName(4)
        printTopLine(flagE, taskName, logTag)
        Log.e(logTag, space + this)
        printBottomLine(flagE, logTag)
    }.onFailure {
        Log.e(logTag, "$space 日志输出错误")
    }

}


fun printException(exception: Exception) {
    if (!Logger.enabledLog) {
        return
    }
    runCatching {
        Log.getStackTraceString(exception).logE()
    }.onFailure {
        Log.e(logTag, "$space 日志输出错误")
    }

}


fun String.logJson() {
    if (!Logger.enabledLog) {
        return
    }
    runCatching {
        val logString: String = this
        val json = if (logString.startsWith("{")) {
            val jsonObject = JSONObject(logString)
            //最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
            jsonObject.toString(4)
        } else if (logString.startsWith("[")) {
            val jsonArray = JSONArray(this)
            jsonArray.toString(4)
        } else {
            logString
        }
        json.log()
    }.onFailure {
        Log.e(logTag, "$space 日志输出错误")
    }


}


private fun getTaskName(index: Int): String {
    val stackTrace = Thread.currentThread().stackTrace
    //查看方法栈堆信息
//    for (i in stackTrace.indices) {
//        val stackTraceElement = stackTrace[i]
//        val className = stackTraceElement.className
//        Log.i("LogTest", "i = $i ,$className")
//    }
    var fileName: String? = "unknown"
    var lineNumber = 0
    if (stackTrace.size >= 5) {
        fileName = stackTrace[index].fileName
        lineNumber = stackTrace[index].lineNumber
    }
    val taskName = StringBuilder()
    taskName.append("(").append(fileName).append(":").append(lineNumber).append(")")
    return taskName.toString()
}


private fun printTopLine(flag: String, title: String, tag: String) {
    when (flag) {
        flagI -> {
            Log.i(tag, "                                                  ")
            Log.i(tag, "═════════$title═════════")
        }

        flagE -> {
            Log.i(tag, "═════════")
            Log.e(tag, "═════════$title═════════")
        }

        else -> {}
    }
}


private fun printBottomLine(flag: String, tag: String) {
    when (flag) {
        flagI -> Log.i(tag, "═════════ log end ═════════ ")
        flagE -> Log.e(tag, "═════════ log end ═════════ ")
        else -> {}
    }
}