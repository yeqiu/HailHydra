package com.fastmvvm.sample.network


import com.squareup.moshi.Json

data class Banner(
    @Json(name = "data")
    val `data`: List<Data?>?,
    @Json(name = "errorCode")
    val errorCode: Int?,
    @Json(name = "errorMsg")
    val errorMsg: String?
) {
    data class Data(
        @Json(name = "desc")
        val desc: String?,
        @Json(name = "id")
        val id: Int?,
        @Json(name = "imagePath")
        val imagePath: String?,
        @Json(name = "isVisible")
        val isVisible: Int?,
        @Json(name = "order")
        val order: Int?,
        @Json(name = "title")
        val title: String?,
        @Json(name = "type")
        val type: Int?,
        @Json(name = "url")
        val url: String?
    )
}