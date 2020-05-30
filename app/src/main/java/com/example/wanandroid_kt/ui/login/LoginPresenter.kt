package com.example.wanandroid_kt.ui.login

import com.example.wanandroid_kt.base.BasePresenter
import com.example.wanandroid_kt.ui.main.mine.MineContract

class LoginPresenter(view:LoginContract.View) :BasePresenter<LoginContract.View>(view),
        LoginContract.Presenter<LoginContract.View>{

}