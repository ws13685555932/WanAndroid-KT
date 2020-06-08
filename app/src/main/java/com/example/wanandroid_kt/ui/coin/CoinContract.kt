package com.example.wanandroid_kt.ui.coin

import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.base.IBaseView
import com.example.wanandroid_kt.entity.CoinRecord
import com.example.wanandroid_kt.entity.CoinWrapper

interface CoinContract {
    interface View : IBaseView{
        fun showCoinList(t : CoinWrapper<CoinRecord>)

        fun showCoin(t : Int)
    }

    interface Presenter : IBasePresenter<View> {
        fun getIntegralRecord(pageNum : Int)

        fun getIntegral()
    }
}