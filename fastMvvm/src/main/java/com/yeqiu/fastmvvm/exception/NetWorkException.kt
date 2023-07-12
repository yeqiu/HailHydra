package com.yeqiu.fastmvvm.exception


/**
 * @project：FastMvvm
 * @author：小卷子
 * @date 2023/7/8
 * @describe：
 * @fix：
 */

const val jsonParseErrorCode = -1000
const val checkResponseErrorCode = -1001
const val NetworkErrorCode = -1002
const val unknownErrorCode = -2000


class NetworkException : RuntimeException {

    var errorMessage: String = ""
    var errorCode: Int = 0
    var throwable: Throwable? = null


    @JvmOverloads
    constructor(errorCode: Int = 0, errorMessage: String = "", throwable: Throwable? = null) {
        this.errorCode = errorCode
        this.errorMessage = errorMessage
        this.throwable = throwable
    }

}