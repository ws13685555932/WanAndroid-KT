package com.zs.wanandroid.ui.main.tab

import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.base.IBaseView
import com.example.wanandroid_kt.entity.TabEntity

/**
 * @author zs
 * @date 2020-03-14
 */
interface TabContract {
    interface View: IBaseView {
        fun showList(list:MutableList<TabEntity>)
    }

    interface Presenter: IBasePresenter<View> {
        fun loadData(type:Int)
    }
}