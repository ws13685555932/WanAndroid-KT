package com.example.wanandroid_kt.ui.system

import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.base.IBaseView
import com.example.wanandroid_kt.entity.Article
import com.example.wanandroid_kt.entity.Wrapper

interface SystemContract{
    interface View : IBaseView{
        fun showList(t: Wrapper<Article>)
        fun collectSucess()
        fun unCollecSuccess()

    }

    interface Presenter : IBasePresenter<View>{
        fun getArticleListOfTag(pageNum : Int, cid : Int)
        fun uncollect(id : Int)
        fun collect(id : Int)
    }
}