package com.example.wanandroid_kt.ui.login

import com.example.wanandroid_kt.base.BasePresenter
import com.example.wanandroid_kt.entity.UserEntity
import com.example.wanandroid_kt.net.ApiCallBack
import com.example.wanandroid_kt.net.RetrofitManager
import com.example.wanandroid_kt.net.SchedulerUtils
import com.example.wanandroid_kt.ui.main.mine.MineContract
import io.reactivex.disposables.Disposable

class LoginPresenter(view:LoginContract.View) :BasePresenter<LoginContract.View>(view),
        LoginContract.Presenter<LoginContract.View>{

        override fun login(username: String, password: String) {
                RetrofitManager.service
                        .login(username, password)
                        .compose(SchedulerUtils.ioToMain())
                        .subscribe(object : ApiCallBack<UserEntity>() {
                                override fun disposible(d: Disposable) {
                                        addSubscrible(d)
                                }

                                override fun success(t: UserEntity) {
                                        view?.loginSuccess()
                                }

                                override fun error(errorMsg: String) {
                                        view?.onError(errorMsg)
                                }


                        })
        }

}