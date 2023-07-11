package com.yeqiu.fastmvvm.network

import cn.netdiscovery.http.interceptor.LoggingInterceptor
import com.safframework.http.interceptor.AndroidLoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * @project：FastMvvm
 * @author：小卷子
 * @date 2023/7/8
 * @describe：
 * @fix：
 */
abstract class BaseNetworkClient {


    private val okHttpClient by lazy {
        val builder = OkHttpClient.Builder()
            .addInterceptor(HeadInterceptor())
        if (NetConfig.enableLog) {
            builder.addInterceptor(getHttpLoggingInterceptor())
            builder.addInterceptor(getLoggingInterceptor())
        }
        setOkHttpClient(builder)
        builder.build()
    }

    private val retrofit: Retrofit by lazy {
        val build = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(getBaseUrl())
        setRetrofit(build)
        build.build()

    }


    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


    private fun getLoggingInterceptor(): LoggingInterceptor {
        val tag = "NetworkClient"
        val requestTag = "$tag ==========> 请求开始"
        val responseTag = "$tag <========== 请求结束"

        return AndroidLoggingInterceptor.build(
            isDebug = true,
            hideVerticalLine = true,
            requestTag = requestTag,
            responseTag = responseTag
        )
    }

    fun <T> getService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }


    abstract fun getBaseUrl(): String

    abstract fun setOkHttpClient(builder: OkHttpClient.Builder)

    abstract fun setRetrofit(build: Retrofit.Builder)
}



