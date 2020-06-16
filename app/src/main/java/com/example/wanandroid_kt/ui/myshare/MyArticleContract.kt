package com.zs.wanandroid.ui.my

import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.base.IBaseView
import com.example.wanandroid_kt.entity.MyArticle

/**
 * @author zs
 * @date 2020-03-17
 */
interface MyArticleContract {
    interface View: IBaseView {
        fun showList(t:MyArticle)
        fun deleteSuccess()
    }
    interface Presenter: IBasePresenter<View> {
        fun loadData(pageNum:Int)
        fun delete(id:Int)
    }
}