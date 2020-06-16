package com.example.wanandroid_kt.ui.main.playground.systemlist

import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.base.IBaseView
import com.example.wanandroid_kt.entity.TabEntity

interface SystemListContract {
    interface View : IBaseView{
        fun showList(t: MutableList<TabEntity>)

    }

    interface Presenter : IBasePresenter<View>{
        fun loadSystemList()
    }
}