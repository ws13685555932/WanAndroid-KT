package com.zs.wanandroid.ui.main.tab.list

import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.base.IBaseView
import com.example.wanandroid_kt.entity.Article


/**
 * @author zs
 * @date 2020-03-14
 */
interface TabListContract {
    interface View: IBaseView {
        fun showList(list:MutableList<Article>)
        fun collectSuccess()
        fun unCollectSuccess()
    }
    interface Presenter: IBasePresenter<View> {
        fun loadData(type:Int,id:Int,pageNum:Int)
        fun collect(id:Int)
        fun unCollect(id:Int)
    }
}