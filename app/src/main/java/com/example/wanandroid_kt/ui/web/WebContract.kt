package com.example.wanandroid_kt.ui.web

import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.base.IBaseView

interface WebContract {
    interface View:IBaseView{

    }

    interface Presenter:IBasePresenter<View>{

    }
}