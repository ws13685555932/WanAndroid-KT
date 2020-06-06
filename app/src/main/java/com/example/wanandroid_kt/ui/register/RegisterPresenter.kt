package com.example.wanandroid_kt.ui.register

import com.example.wanandroid_kt.base.BasePresenter
import com.example.wanandroid_kt.net.ApiCallBack
import com.example.wanandroid_kt.net.RetrofitManager
import com.example.wanandroid_kt.net.SchedulerUtils
import io.reactivex.disposables.Disposable

class RegisterPresenter(view : RegisterContract.View) : BasePresenter<RegisterContract.View>(view),
        RegisterContract.Presenter{

    override fun register(username: String, password: String, confirmPassword: String) {
        RetrofitManager.service
            .register(username, password, confirmPassword)
            .compose(SchedulerUtils.ioToMain())
            .subscribe(object :ApiCallBack<Any>(){
                override fun disposible(d: Disposable) {
                    addSubscrible(d)
                }

                override fun success(t: Any) {
                    view?.registerSuccess()
                }

                override fun error(errorMsg: String) {
                    view?.onError(errorMsg)
                }

            })
    }

}