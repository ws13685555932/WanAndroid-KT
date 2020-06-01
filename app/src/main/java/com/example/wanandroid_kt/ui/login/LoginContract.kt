package com.example.wanandroid_kt.ui.login

import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.base.IBaseView

interface LoginContract {
    interface View : IBaseView{
        fun loginSuccess()
    }

    interface Presenter<V> : IBasePresenter<View>{
        fun login(username : String, password:String)
    }
}