package com.example.wanandroid_kt.ui.main.playground.systemlist

import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.base.IBaseView
import com.example.wanandroid_kt.entity.SystemEntity

interface SystemListContract {
    interface View : IBaseView{
        fun showList(t: MutableList<SystemEntity>)

    }

    interface Presenter : IBasePresenter<View>{
        fun loadSystemList()
    }
}