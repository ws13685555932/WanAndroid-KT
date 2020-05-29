package com.example.wanandroid_kt.ui.main.mine

import android.widget.Toast
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.base.AppLazyFragment
import com.example.wanandroid_kt.ext.toast

class MineFragment : AppLazyFragment<MineContract.Presenter<MineContract.View>>(), MineContract.View{
    override fun lazyInit() {

    }

    override fun createPresenter(): MineContract.Presenter<MineContract.View>? {
        return MinePresenter(this)
    }

    override fun layoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun onError(error: String) {

    }

}