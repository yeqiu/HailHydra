package com.fastmvvm.sample.network

import com.yeqiu.easyandroid.log
import com.yeqiu.fastmvvm.network.BaseNetworkClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * @project：FastMvvm
 * @author：小卷子
 * @date 2023/7/10
 * @describe：
 * @fix：
 */
object NetworkClient : BaseNetworkClient() {

    override fun getBaseUrl(): String {
//        return "https://api.github.com"
        return "https://www.wanandroid.com"
    }

    override fun setOkHttpClient(builder: OkHttpClient.Builder) {
    }

    override fun setRetrofit(build: Retrofit.Builder) {
    }

    //双重校验锁式-单例
    val api: Api by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        "api by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED)".log()
        getService(Api::class.java)
    }

}