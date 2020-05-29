package com.example.wanandroid_kt.ui.main.home

import com.example.wanandroid_kt.base.BasePresenter
import com.example.wanandroid_kt.ui.main.discovery.DiscoveryContract

class HomePresenter (view : HomeContract.View) : BasePresenter<HomeContract.View>(view),
    HomeContract.Presenter<HomeContract.View>{

}