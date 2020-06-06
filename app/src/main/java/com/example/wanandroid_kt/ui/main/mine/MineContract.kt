package com.example.wanandroid_kt.ui.main.mine

import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.base.IBaseView
import com.example.wanandroid_kt.entity.IntegralEntity

interface MineContract{
    interface View:IBaseView{
        fun showIntegral(t: IntegralEntity)
    }

    interface Presenter<V> : IBasePresenter<View>{
        fun loadIntegral()
    }
}