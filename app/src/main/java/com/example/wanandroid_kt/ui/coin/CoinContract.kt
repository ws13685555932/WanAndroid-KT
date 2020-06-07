package com.example.wanandroid_kt.ui.coin

import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.base.IBaseView

interface CoinContract {
    interface View : IBaseView{

    }

    interface Presenter : IBasePresenter<View> {

    }
}