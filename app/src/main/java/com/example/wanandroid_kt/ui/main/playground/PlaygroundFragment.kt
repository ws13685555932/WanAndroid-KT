package com.example.wanandroid_kt.ui.main.playground

import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.base.AppLazyFragment
import com.example.wanandroid_kt.ui.main.discovery.DiscoveryContract

class PlaygroundFragment : AppLazyFragment<PlaygroundContract.Presenter<PlaygroundContract.View>>(), PlaygroundContract.View{
    override fun lazyInit() {
    }

    override fun createPresenter(): PlaygroundContract.Presenter<PlaygroundContract.View>? {
        return PlaygroundPresenter(this)
    }

    override fun layoutId(): Int {
        return R.layout.fragment_playground
    }

    override fun onError(error: String) {
    }

}