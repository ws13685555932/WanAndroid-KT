package com.example.wanandroid_kt.entity

data class Wrapper<T>(
    val offset: Int,
    val size: Int,
    val total: Int,
    val pageCount: Int,
    val curPage: Int,
    val over: Boolean,
    val datas: MutableList<T>
)