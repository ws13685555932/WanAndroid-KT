package com.example.wanandroid_kt.ui.main.playground.systemlist

import android.annotation.SuppressLint
import com.example.wanandroid_kt.base.BasePresenter
import com.example.wanandroid_kt.entity.SystemEntity
import com.example.wanandroid_kt.net.ApiCallBack
import com.example.wanandroid_kt.net.RetrofitManager
import com.example.wanandroid_kt.net.SchedulerUtil
import io.reactivex.disposables.Disposable
import java.nio.channels.MulticastChannel

class SystemListPresenter (view : SystemListContract.View) : BasePresenter<SystemListContract.View>(view),
        SystemListContract.Presenter{


    override fun loadSystemList() {
        RetrofitManager.service
            .getSystemList()
            .compose(SchedulerUtil.ioToMain())
            .subscribe(object : ApiCallBack<MutableList<SystemEntity>>(){
                override fun disposible(d: Disposable) {
                    addSubscrible(d)
                }

                override fun success(t: MutableList<SystemEntity>) {
                    view?.showList(t)
                }

                override fun error(errorMsg: String) {
                    view?.onError(errorMsg)
                }

            })
    }

}