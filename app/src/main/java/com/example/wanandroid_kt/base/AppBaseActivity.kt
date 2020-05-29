package com.example.wanandroid_kt.base

import android.content.Context
import android.os.Bundle
import com.example.wanandroid_kt.ext.toast

abstract class AppBaseActivity <P:IBasePresenter<*>> : BaseActivity<P>(), IBaseView{

    protected fun goto(clazz: Class<*>, isLogin:Boolean){
    }

    protected fun goto(clazz: Class<*>, isLogin: Boolean, bundle: Bundle){
    }

    override fun getContext(): Context? {
        return this
    }

    override fun onError(error: String) {
    }

}