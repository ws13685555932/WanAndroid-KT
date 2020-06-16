package com.example.wanandroid_kt.ui.main.home

import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.base.AppLazyFragment

class HomeFragment : AppLazyFragment<HomeContract.Presenter<HomeContract.View>>(), HomeContract.View{
    override fun lazyInit() {
    }

    override fun createPresenter(): HomeContract.Presenter<HomeContract.View>? {
        return HomePresenter(this)
    }

    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun onError(error: String) {
    }

}