package com.example.wanandroid_kt.base

abstract class LazyFragment <P : IBasePresenter<*>> : BaseFragment<P>(){

    private var isLoaded = false

    override fun onResume() {
        super.onResume()
        if(!isLoaded && !isHidden){
            lazyInit()
            isLoaded = true
        }
    }

    override fun initView() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    protected abstract fun lazyInit()

}