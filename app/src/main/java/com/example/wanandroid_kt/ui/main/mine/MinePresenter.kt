package com.example.wanandroid_kt.ui.main.mine

import com.example.wanandroid_kt.base.BasePresenter
import com.example.wanandroid_kt.const.SaveConstants
import com.example.wanandroid_kt.entity.IntegralEntity
import com.example.wanandroid_kt.ext.toast
import com.example.wanandroid_kt.net.ApiCallBack
import com.example.wanandroid_kt.net.RetrofitManager
import com.example.wanandroid_kt.net.SchedulerUtils
import com.example.wanandroid_kt.utils.SharedPrefUtil
import io.reactivex.disposables.Disposable

class MinePresenter (view :MineContract.View) : BasePresenter<MineContract.View>(view),
        MineContract.Presenter<MineContract.View>{

        override fun loadIntegral() {
                RetrofitManager.service
                        .getIntegral()
                        .compose(SchedulerUtils.ioToMain())
                        .subscribe(object : ApiCallBack<IntegralEntity>(){
                                override fun disposible(d: Disposable) {
                                        addSubscrible(d)
                                }

                                override fun success(t: IntegralEntity) {
                                        "success".toast()
                                        SharedPrefUtil.saveObject(SaveConstants.INTEGRAL_INFO, t)
                                        view?.showIntegral(t)
                                }

                                override fun error(errorMsg: String) {
                                        errorMsg.toast()
                                        view?.onError(errorMsg)
                                }

                        })

        }


}