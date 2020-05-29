package com.example.wanandroid_kt.ui.main.home

import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.base.IBaseView

interface HomeContract {
    interface View: IBaseView {

    }

    interface Presenter<V> : IBasePresenter<View> {

    }
}