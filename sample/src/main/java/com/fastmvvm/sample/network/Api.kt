package com.fastmvvm.sample.network

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @project：FastMvvm
 * @author：小卷子
 * @date 2023/7/8
 * @describe：
 * @fix：
 */
interface Api {


    @GET("/users/{users}")
    suspend fun getUser(@Path("users") users:String): User

}