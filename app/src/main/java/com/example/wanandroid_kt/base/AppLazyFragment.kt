package com.example.wanandroid_kt.base

import android.content.Intent
import android.os.Bundle

abstract class AppLazyFragment <P : IBasePresenter<*>> : LazyFragment<P>(){

    protected fun goto(clazz: Class<*>, isLogin : Boolean){
        if (isLogin){
            startActivity(Intent(context, clazz))
        }
    }

    protected fun goto(clazz: Class<*>, isLogin: Boolean, bundle: Bundle){

    }
}