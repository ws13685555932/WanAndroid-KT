package com.example.wanandroid_kt.ui.coin

import com.example.wanandroid_kt.base.BasePresenter
import com.example.wanandroid_kt.entity.CoinRecord
import com.example.wanandroid_kt.entity.CoinWrapper
import com.example.wanandroid_kt.entity.CoinEntity
import com.example.wanandroid_kt.net.ApiCallBack
import com.example.wanandroid_kt.net.RetrofitManager
import com.example.wanandroid_kt.net.SchedulerUtil
import io.reactivex.disposables.Disposable

class CoinPresenter (view : CoinContract.View) : BasePresenter<CoinContract.View> ( view),
        CoinContract.Presenter{
        override fun getIntegralRecord(pageNum: Int) {
                RetrofitManager.service
                        .getIntegralRecord(pageNum)
                        .compose(SchedulerUtil.ioToMain())
                        .subscribe(object : ApiCallBack<CoinWrapper<CoinRecord>>(){
                                override fun disposible(d: Disposable) {
                                        addSubscrible(d)
                                }

                                override fun success(t: CoinWrapper<CoinRecord>) {
                                        view?.showCoinList(t)
                                }

                                override fun error(errorMsg: String) {
                                       view?.onError(errorMsg)
                                }

                        })
        }

        override fun getIntegral() {
                RetrofitManager.service
                        .getIntegral()
                        .compose(SchedulerUtil.ioToMain())
                        .subscribe(object  : ApiCallBack<CoinEntity>(){
                                override fun disposible(d: Disposable) {
                                        addSubscrible(d)
                                }

                                override fun success(t: CoinEntity) {
                                        view?.showCoin(t.coinCount)
                                }

                                override fun error(errorMsg: String) {
                                        view?.onError(errorMsg)
                                }

                        })
        }

}