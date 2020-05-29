package com.example.wanandroid_kt.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity <P : IBasePresenter<*>> : AppCompatActivity(){
    protected val TAG = javaClass.name
    protected var presenter : P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutId = layoutId()

        if (layoutId != 0) {
            setContentView(layoutId)
        }

        presenter = createPresenter()
        presenter?.let {
            lifecycle.addObserver(it)
        }

        initView()
        initData()
    }

    protected fun goto(clazz: Class<*>){
        startActivity(Intent(this, clazz))
    }

    protected fun goto(clazz: Class<*>, bundle: Bundle){
    }

    protected abstract fun layoutId() : Int
    protected abstract fun createPresenter() : P?
    open fun initView() {}
    open fun initData() {}

}