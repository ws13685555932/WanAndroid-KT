package com.zs.wanandroid.ui.collect

import android.content.Context
import android.os.Bundle
import android.util.TimeUtils
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.adapter.CollectAdapter
import com.example.wanandroid_kt.base.AppBaseActivity
import com.example.wanandroid_kt.const.Constants
import com.example.wanandroid_kt.entity.Article
import com.example.wanandroid_kt.ext.log
import com.example.wanandroid_kt.ext.toast
import com.example.wanandroid_kt.view.ViewCallback
import com.jakewharton.rxbinding4.view.clicks
import com.scwang.smart.refresh.layout.api.RefreshLayout
import io.reactivex.disposables.Disposable
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.functions.Consumer
import kotlinx.android.synthetic.main.activity_collect.*
import kotlinx.android.synthetic.main.activity_collect.smartRefresh
import kotlinx.android.synthetic.main.activity_system.*
import kotlinx.android.synthetic.main.item_project.*
import java.util.concurrent.TimeUnit

/**
 * 收藏
 * @author zs
 * @date 2020-03-13
 */
class CollectActivity : AppBaseActivity<CollectContract.Presenter>(),CollectContract.View{

    private lateinit var collectAdapter : CollectAdapter
    private var collectList = mutableListOf<Article>()
    private var currentPosition = 0
    private var pageNum = 0


    override fun initView() {
        ivBack.setOnClickListener {
            finish()
        }
        rvCollect.layoutManager = LinearLayoutManager(this)
        collectAdapter = CollectAdapter()
        collectAdapter.addChildClickViewIds(R.id.ivCollect)
        collectAdapter.setOnCollectedListener(object : CollectAdapter.OnCollectedListener{
            override fun onCollected(position: Int) {
                currentPosition = position
                presenter?.cancelCollect(collectList[position].originId)
            }

        })
        rvCollect.adapter = collectAdapter
    }

    override fun initData() {
        loadingTip.loading()
        loadData()
    }


    private fun loadData(){
        collectList.clear()
        collectAdapter.setList(collectList)
        pageNum = 0
        presenter?.loadData(pageNum)
    }

    override fun showList(list: MutableList<Article>) {
        loadingTip.dismiss()
        if (list.isNotEmpty()){
            collectList.addAll(list)
            collectAdapter.setList(collectList)
        }else {
            if (collectList.size==0)
                loadingTip.showEmpty()
            else
                "没有数据啦...".toast()
        }
    }

    override fun cancelCollectSuccess() {
        collectAdapter.removeAt(currentPosition)
    }


    override fun createPresenter(): CollectContract.Presenter? {
        return CollectPresenter(this)
    }

    override fun layoutId(): Int {
        return R.layout.activity_collect
    }

}
