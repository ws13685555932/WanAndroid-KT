package com.example.wanandroid_kt.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.StringRes
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.ext.toast
import com.example.wanandroid_kt.ui.login.LoginActivity
import com.example.wanandroid_kt.utils.AppUtil
import com.example.wanandroid_kt.view.LoadingDialog

abstract class AppBaseActivity <P:IBasePresenter<*>> : BaseActivity<P>(), IBaseView{

    private lateinit var dialog : LoadingDialog

    protected fun goto(clazz: Class<*>, needLogin:Boolean){
        //需要登录&&未登录
        if (needLogin && !AppUtil.isLogin()) {
            startActivity(Intent(this, LoginActivity::class.java))
        }else{
            startActivity(Intent(this,clazz))
        }
    }

    protected fun goto(clazz: Class<*>, needLogin: Boolean, bundle: Bundle){
        if (needLogin && !AppUtil.isLogin()){
            startActivity(Intent(this, LoginActivity::class.java))
        }else{
            startActivity(Intent(this, clazz).apply {
                putExtras(bundle)
            })
        }
    }

    override fun getContext(): Context? {
        return this
    }

    override fun onError(error: String) {
        hideLoadingDialog()
        error.toast()
    }

    fun showLoadingDialog(@StringRes messageResId : Int = R.string.loading){
        if ( ! this::dialog.isInitialized){
            dialog = LoadingDialog.newInstance()
        }
        dialog.show(supportFragmentManager, messageResId, false)
    }

    fun hideLoadingDialog(){
        if (this::dialog.isInitialized && dialog.isVisible){
            dialog.dismiss()
        }
    }

}