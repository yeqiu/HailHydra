package com.yeqiu.fastmvvm.exception


/**
 * @project：FastMvvm
 * @author：小卷子
 * @date 2023/7/8
 * @describe：
 * @fix：
 */
class GenerateViewDataBindingException : RuntimeException {
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
}