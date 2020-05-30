package com.example.wanandroid_kt.ui.login

import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.base.IBaseView

interface LoginContract {
    interface View : IBaseView{

    }

    interface Presenter<V> : IBasePresenter<View>{

    }
}