package com.example.wanandroid_kt.ui.main.mine

import android.os.Bundle
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.base.AppLazyFragment
import com.example.wanandroid_kt.const.SaveConstants
import com.example.wanandroid_kt.entity.IntegralEntity
import com.example.wanandroid_kt.entity.LoginEvent
import com.example.wanandroid_kt.ui.coin.CoinActivity
import com.example.wanandroid_kt.ui.login.LoginActivity
import com.example.wanandroid_kt.utils.AppUtil
import com.example.wanandroid_kt.utils.SharedPrefUtil
import kotlinx.android.synthetic.main.fragment_mine.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MineFragment : AppLazyFragment<MineContract.Presenter<MineContract.View>>(),
    MineContract.View {

    private var integralEntity: IntegralEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun lazyInit() {
        initEvent()
        initData()
    }

    private fun initData() {
        //先判断数据是否为空，然后再强转，否则会出异常
        SharedPrefUtil.getObject(SaveConstants.INTEGRAL_INFO)?.let {
            integralEntity = it as IntegralEntity
        }
        if (integralEntity == null) {
            if (AppUtil.isLogin()){
                presenter?.loadIntegral()
            }
        }else{
            setIntegral()
        }
    }

    private fun setIntegral() {
        integralEntity?.apply {
            tvUserName.text = username
            tvId.text = String.format("%s","id:$userId")
            tvRank.text = rank.toString()
            tvCoin.text = coinCount.toString()
        }

    }

    private fun initEvent() {
        tvUserName.setOnClickListener {
            goto(LoginActivity::class.java, true)
        }

        llCoin.setOnClickListener {
            goto(CoinActivity::class.java, false)
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
        this.integralEntity = t
        setIntegral()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        SharedPrefUtil.removeKey(SaveConstants.INTEGRAL_INFO)
        EventBus.getDefault().unregister(this)
    }


}