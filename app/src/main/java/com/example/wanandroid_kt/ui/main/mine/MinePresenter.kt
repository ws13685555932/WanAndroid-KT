package com.example.wanandroid_kt.ui.main.mine

import com.example.wanandroid_kt.base.BasePresenter
import com.example.wanandroid_kt.const.Const
import com.example.wanandroid_kt.entity.IntegralEntity
import com.example.wanandroid_kt.ext.toast
import com.example.wanandroid_kt.net.ApiCallBack
import com.example.wanandroid_kt.net.RetrofitManager
import com.example.wanandroid_kt.net.SchedulerUtils
import com.example.wanandroid_kt.utils.PrefUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

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
                                        PrefUtil.saveObject(Const.INTEGRAL_INFO, t)
                                        view?.showIntegral(t)
                                }

                                override fun error(errorMsg: String) {
                                        errorMsg.toast()
                                        view?.onError(errorMsg)
                                }

                        })

        }


}