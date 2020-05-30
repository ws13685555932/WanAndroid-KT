package com.example.wanandroid_kt.ui.main.mine

import android.widget.Toast
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.base.AppLazyFragment
import com.example.wanandroid_kt.ext.toast
import com.example.wanandroid_kt.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment : AppLazyFragment<MineContract.Presenter<MineContract.View>>(), MineContract.View{
    override fun lazyInit() {
        initEvent()
    }

    private fun initEvent(){
        tvUserName.setOnClickListener {
//            Toast.makeText(activity, "hello",Toast.LENGTH_SHORT).show()
            goto(LoginActivity::class.java, true)
        }

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