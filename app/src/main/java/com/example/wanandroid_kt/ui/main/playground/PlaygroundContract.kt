package com.example.wanandroid_kt.ui.main.playground

import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.base.IBaseView

interface PlaygroundContract {
    interface View: IBaseView {

    }

    interface Presenter<V> : IBasePresenter<View> {

    }
}