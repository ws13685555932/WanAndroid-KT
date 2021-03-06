package com.example.wanandroid_kt.ui.main.mine

import com.example.wanandroid_kt.base.BasePresenter
import com.example.wanandroid_kt.entity.CoinEntity
import com.example.wanandroid_kt.ext.toast
import com.example.wanandroid_kt.net.ApiCallBack
import com.example.wanandroid_kt.net.RetrofitManager
import com.example.wanandroid_kt.net.SchedulerUtil
import io.reactivex.disposables.Disposable

class MinePresenter (view :MineContract.View) : BasePresenter<MineContract.View>(view),
        MineContract.Presenter<MineContract.View>{

        override fun getCoin() {
                RetrofitManager.service
                        .getIntegral()
                        .compose(SchedulerUtil.ioToMain())
                        .subscribe(object : ApiCallBack<CoinEntity>(){
                                override fun disposible(d: Disposable) {
                                        addSubscrible(d)
                                }

                                override fun success(t: CoinEntity) {
                                        view?.showCoin(t)
                                }

                                override fun error(errorMsg: String) {
                                        errorMsg.toast()
                                        view?.onError(errorMsg)
                                }

                        })

        }


}