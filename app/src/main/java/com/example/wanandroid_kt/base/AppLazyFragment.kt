package com.example.wanandroid_kt.base

import android.os.Bundle

abstract class AppLazyFragment <P : IBasePresenter<*>> : LazyFragment<P>(){

    protected fun goto(clazz: Class<*>, isLogin : Boolean){

    }

    protected fun goto(clazz: Class<*>, isLogin: Boolean, bundle: Bundle){

    }
}