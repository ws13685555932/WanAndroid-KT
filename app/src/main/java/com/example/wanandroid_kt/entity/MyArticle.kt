package com.example.wanandroid_kt.entity

data class MyArticle(
    val coinInfo: CoinEntity,
    val shareArticles: Wrapper<Article>
)