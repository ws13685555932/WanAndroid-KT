package com.example.wanandroid_kt.ui.main.mine

import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.base.IBaseView
import com.example.wanandroid_kt.entity.CoinEntity

interface MineContract{
    interface View:IBaseView{
        fun showCoin(t: CoinEntity)
    }

    interface Presenter<V> : IBasePresenter<View>{
        fun getCoin()
    }
}