package com.example.wanandroid_kt.base

import android.content.Intent
import android.os.Bundle
import com.example.wanandroid_kt.ui.login.LoginActivity
import com.example.wanandroid_kt.utils.AppUtil

abstract class AppBaseFragment<P : IBasePresenter<*>> : BaseFragment<P>() {

    protected fun goto(clazz: Class<*>, isLogin : Boolean){
        //需要登录&&未登录
        if (isLogin && !AppUtil.isLogin()) {
            startActivity(Intent(context, LoginActivity::class.java))
        }else{
            startActivity(Intent(context,clazz))
        }
    }

    protected fun goto(clazz: Class<*>, isLogin: Boolean, bundle: Bundle){
        //需要登录&&未登录
        if (isLogin && !AppUtil.isLogin()) {
            startActivity(Intent(context, LoginActivity::class.java))
        }else{
            startActivity(Intent(context, clazz).apply {
                putExtras(bundle)
            })
        }
    }

}