package com.example.wanandroid_kt.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import com.example.wanandroid_kt.ext.toast
import com.example.wanandroid_kt.view.LoadingDialog

abstract class AppBaseActivity <P:IBasePresenter<*>> : BaseActivity<P>(), IBaseView{

    private lateinit var dialog : LoadingDialog

    protected fun goto(clazz: Class<*>, isLogin:Boolean){
    }

    protected fun goto(clazz: Class<*>, isLogin: Boolean, bundle: Bundle){
    }

    override fun getContext(): Context? {
        return this
    }

    override fun onError(error: String) {
        hideLoadingDialog()
        error.toast()
    }

    fun showLoadingDialog(@StringRes messageResId: Int){
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