package com.yeqiu.fastmvvm.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @project：FastMvvm
 * @author：小卷子
 * @date 2023/7/10
 * @describe：
 * @fix：
 */
class HeadInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        NetworkConfig.commonHead.forEach{
            builder.addHeader(it.key,it.value)
        }
        return chain.proceed(builder.build())
    }
}