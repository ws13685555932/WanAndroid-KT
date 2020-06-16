package com.example.wanandroid_kt.ui.main.mine

import android.os.Bundle
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.base.AppLazyFragment
import com.example.wanandroid_kt.const.Constants
import com.example.wanandroid_kt.entity.CoinEntity
import com.example.wanandroid_kt.entity.LoginEvent
import com.example.wanandroid_kt.entity.UserEntity
import com.example.wanandroid_kt.ext.str
import com.example.wanandroid_kt.ui.coin.CoinActivity
import com.example.wanandroid_kt.ui.login.LoginActivity
import com.example.wanandroid_kt.utils.SharedPrefUtil
import com.zs.wanandroid.ui.collect.CollectActivity
import kotlinx.android.synthetic.main.fragment_mine.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

//todo : 可以先保存上一次的值，在上一次的值上进行变化

class MineFragment : AppLazyFragment<MineContract.Presenter<MineContract.View>>(),
    MineContract.View {

    private var coinEntity: CoinEntity? = null
    private var userEntity: UserEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun lazyInit() {
        initEvent()
        initData()
    }

    private fun initData() {
        // 从本地获取名字和id
        SharedPrefUtil.getObject(Constants.USER_INFO)?.let {
            userEntity = it as UserEntity
        }
        if (userEntity != null){
            setNameAndId()
            presenter?.getCoin()
        }else{
            resetUI()
        }

    }

    private fun setNameAndId(){
        userEntity?.apply {
            tvUserName.text = username
            tvId.text = String.format("%s", "id:$id")
        }
    }

    private fun resetUI(){
        tvUserName.text = R.string.please_login.str()
        tvCoin.text = "0"
        tvId.text = R.string.default_id.str()
    }

    override fun showCoin(t: CoinEntity) {
        this.coinEntity = t
        tvCoin.text = t.coinCount.toString()
        tvRank.text = t.rank.toString()
    }

    private fun initEvent() {
        tvUserName.setOnClickListener {
            goto(LoginActivity::class.java, true)
        }

        llCoin.setOnClickListener {
            goto(CoinActivity::class.java, false)
        }

        rlCollect.setOnClickListener {
            goto(CollectActivity::class.java, true)
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


    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }


}