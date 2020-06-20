package com.example.wanandroid_kt.ui.main.home

import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.base.IBaseView
import com.example.wanandroid_kt.entity.Article
import com.example.wanandroid_kt.entity.Banner

interface HomeContract {
    interface View: IBaseView {
        fun showBanner(t: MutableList<Banner>)
        fun showList(t: MutableList<Article>)

    }

    interface Presenter<V> : IBasePresenter<View> {
        fun getBanner()

        fun getArticles(pageNum : Int)

        fun getTopList(list : MutableList<Article>)
    }
}