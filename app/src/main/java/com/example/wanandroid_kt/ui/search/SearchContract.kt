package com.zs.wanandroid.ui.search

import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.base.IBaseView
import com.example.wanandroid_kt.entity.Article


/**
 * @author zs
 * @date 2020-03-15
 */
interface SearchContract {
    interface View: IBaseView {
        fun showList(list: MutableList<Article>)
        fun collectSuccess()
        fun unCollectSuccess()
    }

    interface Presenter : IBasePresenter<View> {
        fun search(pageNum:Int,key:String)
        fun collect(id:Int)
        fun unCollect(id:Int)
    }
}