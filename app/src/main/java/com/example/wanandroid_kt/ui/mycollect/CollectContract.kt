package com.zs.wanandroid.ui.collect

import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.base.IBaseView
import com.example.wanandroid_kt.entity.Article

/**
 * @author zs
 * @date 2020-03-13
 */
interface CollectContract {
    interface View: IBaseView {
        fun showList(list: MutableList<Article>)
        fun cancelCollectSuccess()
    }

    interface Presenter: IBasePresenter<View> {
        fun loadData(page:Int)
        fun cancelCollect(id:Int)
    }
}