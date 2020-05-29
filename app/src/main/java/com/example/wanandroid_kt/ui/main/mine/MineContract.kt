package com.example.wanandroid_kt.ui.main.mine

import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.base.IBaseView

interface MineContract{
    interface View:IBaseView{

    }

    interface Presenter<V> : IBasePresenter<View>{

    }
}