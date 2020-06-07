package com.example.wanandroid_kt.ui.coin

import android.animation.ValueAnimator
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.base.AppBaseActivity
import com.example.wanandroid_kt.base.BaseActivity
import kotlinx.android.synthetic.main.activity_coin.*

class CoinActivity : AppBaseActivity<CoinContract.Presenter>(), CoinContract.View {

    override fun initView() {

    }

    fun animateTo(coinCount : Int){
        val animator = ValueAnimator.ofInt(0,coinCount)
        //播放时长
        animator.duration = 1500
        animator.interpolator = DecelerateInterpolator()
        animator.addUpdateListener { animation ->
            //获取改变后的值
            val currentValue = animation.animatedValue as Int
            svScore.setCoin(currentValue)
        }
        animator.start()
    }

    override fun layoutId(): Int {
        return R.layout.activity_coin
    }

    override fun createPresenter(): CoinContract.Presenter? {
        return CoinPresenter(this)
    }

}
