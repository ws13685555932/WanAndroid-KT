package com.example.wanandroid_kt.ui.main.project

import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.base.IBaseView

interface ProjectContract{
    interface View:IBaseView{

    }

    interface Presenter<V> : IBasePresenter<View>{

    }
}