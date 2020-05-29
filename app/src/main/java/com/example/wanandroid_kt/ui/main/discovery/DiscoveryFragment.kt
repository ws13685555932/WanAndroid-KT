package com.example.wanandroid_kt.ui.main.discovery

import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.base.AppLazyFragment

class DiscoveryFragment : AppLazyFragment<DiscoveryContract.Presenter<DiscoveryContract.View>>(), DiscoveryContract.View{
    override fun lazyInit() {
    }

    override fun createPresenter(): DiscoveryContract.Presenter<DiscoveryContract.View>? {
        return DiscoveryPresenter(this)
    }

    override fun layoutId(): Int {
        return R.layout.fragment_discovery
    }

    override fun onError(error: String) {
    }

}