package com.example.wanandroid_kt.ui.main.mine

import android.os.Bundle
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.base.AppLazyFragment
import com.example.wanandroid_kt.const.Const
import com.example.wanandroid_kt.entity.IntegralEntity
import com.example.wanandroid_kt.entity.LoginEvent
import com.example.wanandroid_kt.ui.login.LoginActivity
import com.example.wanandroid_kt.utils.AppUtil
import com.example.wanandroid_kt.utils.PrefUtil
import kotlinx.android.synthetic.main.fragment_mine.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MineFragment : AppLazyFragment<MineContract.Presenter<MineContract.View>>(),
    MineContract.View {

    private var coinRankEntity: IntegralEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override fun lazyInit() {
        initEvent()
        initData()
    }

    private fun initData() {
        //先判断数据是否为空，然后再强转，否则会出异常
        presenter?.loadIntegral()
    }

    private fun setIntegral() {

    }

    private fun initEvent() {
        tvUserName.setOnClickListener {
            goto(LoginActivity::class.java, true)
        }

    }

    override fun createPresenter(): MineContract.Presenter<MineContract.View>? {
        return MinePresenter(this)
    }

    override fun layoutId(): Int {
        return R.layout.fragment_mine
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun receiveLogin(loginEvent: LoginEvent) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun recieveLogout(loginEvent: LoginEvent) {

    }

    override fun showIntegral(t: IntegralEntity) {
        tvCoin.setText(t.coinCount.toString())
    }


}