package com.example.wanandroid_kt.ui.register

import com.example.wanandroid_kt.base.BasePresenter
import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.base.IBaseView

interface RegisterContract{

    interface View :IBaseView{
        fun registerSuccess()
    }

    interface Presenter : IBasePresenter<View>{
        fun register(username:String, password : String, confirmPassword :String)
    }
}