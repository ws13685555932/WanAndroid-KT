package com.example.wanandroid_kt.entity

data class UserEntity(
    val admin: Boolean,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val publicName: String,
    val token: String,
    val type: Int,
    val username: String,
    val collectIds: MutableList<Int>,
    val chapterTops: List<Any>
)