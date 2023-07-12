package com.fastmvvm.sample.network

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

/**
 * @project：FastMvvm
 * @author：小卷子
 * @date 2023/7/8
 * @describe：
 * @fix：
 */
interface Api {


    @GET
    suspend fun getUser(
        @Url url: String
    ): User


    @GET("/banner/json")
    suspend fun banner(): Banner

    @GET("/banner/json")
    suspend fun banner2(): ApiResponse<List<Banner.Data>>

    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") pwd: String
    ): ApiResponse<LoginInfo>

}