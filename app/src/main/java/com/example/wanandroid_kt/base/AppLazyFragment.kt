package com.example.wanandroid_kt.base

import android.content.Intent
import android.os.Bundle
import com.example.wanandroid_kt.ext.toast
import com.example.wanandroid_kt.ui.login.LoginActivity
import com.example.wanandroid_kt.utils.AppUtil

abstract class AppLazyFragment<P : IBasePresenter<*>> : LazyFragment<P>(), IBaseView {

    protected fun goto(clazz:Class<*>, isLogin:Boolean){
        //需要登录&&未登录
        if (isLogin && !AppUtil.isLogin()) {
            startActivity(Intent(context, LoginActivity::class.java))
        }else{
            startActivity(Intent(context,clazz))
        }
    }

    /**
     * 携带bundle跳转
     * @param isLogin 启动界面是否需要登录
     */
    protected fun goto(bundle: Bundle, clazz:Class<*>, isLogin:Boolean){
        //需要登录&&未登录
        if (isLogin && !AppUtil.isLogin()) {
            startActivity(Intent(context, LoginActivity::class.java))
        }else{
            startActivity(Intent(context, clazz).apply {
                putExtras(bundle)
            })
        }
    }

    override fun onError(error: String) {
        error.toast()
    }


}