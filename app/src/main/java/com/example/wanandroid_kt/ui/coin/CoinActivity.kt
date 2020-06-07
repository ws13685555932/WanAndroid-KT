package com.example.wanandroid_kt.ui.coin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.base.AppBaseActivity
import com.example.wanandroid_kt.base.BaseActivity
import kotlinx.android.synthetic.main.activity_coin.*

class CoinActivity : AppBaseActivity<CoinContract.Presenter>(), CoinContract.View {

    override fun initView() {
        svScore.setProgress(75)
    }

    override fun layoutId(): Int {
        return R.layout.activity_coin
    }

    override fun createPresenter(): CoinContract.Presenter? {
        return CoinPresenter(this)
    }

}
