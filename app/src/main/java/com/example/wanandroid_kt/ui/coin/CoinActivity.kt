package com.example.wanandroid_kt.ui.coin

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.adapter.CoinAdapter
import com.example.wanandroid_kt.base.AppBaseActivity
import com.example.wanandroid_kt.entity.Bean
import com.example.wanandroid_kt.entity.CoinRecord
import com.example.wanandroid_kt.entity.Wrapper
import com.example.wanandroid_kt.ext.toast
import kotlinx.android.synthetic.main.activity_coin.*
import kotlinx.android.synthetic.main.header_my_coin.*


class CoinActivity : AppBaseActivity<CoinContract.Presenter>(), CoinContract.View {

    private lateinit var mAdapter : CoinAdapter
    private lateinit var mHeaderView : View

    override fun initView() {

        mAdapter = CoinAdapter()
        rvCoinList.layoutManager = LinearLayoutManager(this)
        val headView: View = layoutInflater.inflate(
            R.layout.header_my_coin,
            rvCoinList.parent as ViewGroup,
            false
        )
        mAdapter.setHeaderView(headView)
        rvCoinList.adapter = mAdapter

        val bean = Bean("s").proX()
        bean.toast()

        presenter?.getIntegralRecord(0)
        presenter?.getIntegral()

    }

//    fun animateTo(coinCount : Int){
//        val animator = ValueAnimator.ofInt(0,coinCount)
//        //播放时长
//        animator.duration = 1500
//        animator.interpolator = DecelerateInterpolator()
//        animator.addUpdateListener { animation ->
//            //获取改变后的值
//            val currentValue = animation.animatedValue as Int
//            svScore.setCoin(currentValue)
//        }
//        animator.start()
//    }

    override fun layoutId(): Int {
        return R.layout.activity_coin
    }

    override fun createPresenter(): CoinContract.Presenter? {
        return CoinPresenter(this)
    }

    override fun showCoinList(t: Wrapper<CoinRecord>) {
        mAdapter.setList(t.datas)

    }

    override fun showCoin(t: Int) {
        svScore.setMaxCoin(1000)
        svScore.setCoinWithAnim(t)
    }

}
