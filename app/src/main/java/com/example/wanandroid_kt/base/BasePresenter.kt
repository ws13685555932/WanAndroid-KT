package com.example.wanandroid_kt.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<V : IBaseView>(view: V) : IBasePresenter<V>{

    protected var view : V? = view
    private var compositeDisposable : CompositeDisposable? = null

    init {
        compositeDisposable = CompositeDisposable()
    }

    override fun onCreate() {
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onDestory() {
        compositeDisposable?.clear()
    }
    protected fun addSubscrible(disposable: Disposable){
        compositeDisposable?.add(disposable)
    }


}