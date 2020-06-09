package com.example.wanandroid_kt.ui.main.playground.navigation

import com.example.wanandroid_kt.base.BasePresenter
import com.example.wanandroid_kt.entity.NaviEntity
import com.example.wanandroid_kt.net.ApiCallBack
import com.example.wanandroid_kt.net.RetrofitManager
import com.example.wanandroid_kt.net.SchedulerUtil
import io.reactivex.disposables.Disposable

class NavigationPresenter(view : NavigationContract.View) : BasePresenter<NavigationContract.View>(view),
        NavigationContract.Presenter{
        override fun getNavigations() {
                RetrofitManager.service
                        .getNavigation()
                        .compose(SchedulerUtil.ioToMain())
                        .subscribe(object : ApiCallBack<MutableList<NaviEntity>>(){
                                override fun disposible(d: Disposable) {
                                        addSubscrible(d)
                                }

                                override fun success(t: MutableList<NaviEntity>) {
                                        view?.showList(t)
                                }

                                override fun error(errorMsg: String) {
                                        view?.onError(errorMsg)
                                }

                        })
        }

}