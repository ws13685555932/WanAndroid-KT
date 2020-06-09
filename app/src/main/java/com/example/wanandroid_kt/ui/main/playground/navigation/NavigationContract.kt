package com.example.wanandroid_kt.ui.main.playground.navigation

import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.base.IBaseView
import com.example.wanandroid_kt.entity.NaviEntity

interface NavigationContract {
    interface View : IBaseView{
        fun showList(t: MutableList<NaviEntity>)

    }

    interface Presenter : IBasePresenter<View>{
        fun getNavigations()

    }
}