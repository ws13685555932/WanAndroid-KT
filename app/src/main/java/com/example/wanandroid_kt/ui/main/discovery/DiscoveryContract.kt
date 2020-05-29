package com.example.wanandroid_kt.ui.main.discovery

import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.base.IBaseView

interface DiscoveryContract {
    interface View: IBaseView {

    }

    interface Presenter<V> : IBasePresenter<View> {

    }
}