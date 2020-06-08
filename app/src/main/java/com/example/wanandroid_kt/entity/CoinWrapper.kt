package com.example.wanandroid_kt.entity

data class CoinWrapper<T>(
    val offset: Int,
    val size: Int,
    val total: Int,
    val pageCount: Int,
    val curPage: Int,
    val over: Boolean,
    val datas: List<T>
)