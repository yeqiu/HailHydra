package com.yeqiu.hydra.ext

/**
 * @project：GithubApp
 * @author：小卷子
 * @date 2019-11-11
 * @describe：Boolean的扩展
 * @fix：
 */


sealed class BooleanExt<out T>

object Otherwise : BooleanExt<Nothing>()
class WithData<T>(val data: T) : BooleanExt<T>()

inline fun <T> Boolean.yes(block: () -> T) =
    when {
        this -> {
            WithData(block())
        }
        else -> {
            Otherwise
        }
    }

inline fun <T> Boolean.no(block: () -> T) =
    when {
        this -> Otherwise
        else -> {
            WithData(block())
        }
    }

inline fun <T> BooleanExt<T>.otherwise(block: () -> T): T =
    when (this) {
        is Otherwise -> block()
        is WithData -> this.data
    }


