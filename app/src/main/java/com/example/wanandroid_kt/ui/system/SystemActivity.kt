package com.example.wanandroid_kt.ui.system

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.adapter.ArticleAdapter
import com.example.wanandroid_kt.base.AppBaseActivity
import com.example.wanandroid_kt.const.Constants
import com.example.wanandroid_kt.entity.Article
import com.example.wanandroid_kt.entity.Wrapper
import com.example.wanandroid_kt.ext.log
import com.example.wanandroid_kt.view.LoadingTip
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.activity_system.*
import kotlinx.android.synthetic.main.item_project.*


class SystemActivity : AppBaseActivity<SystemContract.Presenter>(),
    SystemContract.View, LoadingTip.ReloadListener, OnRefreshListener {

    private var cid: Int? = null
    private var title: String? = null
    private var currentPosition = 0
    private var pageNum = 0

    private lateinit var articleAdapter: ArticleAdapter
    private var articleList: MutableList<Article> = mutableListOf<Article>()

    private var collectClickListener: ArticleAdapter.OnCollectClickListener? = null


    override fun initView() {
        sysLoading.setReloadListener(this)

        smartRefresh.setOnRefreshListener(this)
        rvSystem.layoutManager = LinearLayoutManager(this)
        articleAdapter = ArticleAdapter()
//        articleAdapter.setOnItemChildClickListener(object : OnItemChildClickListener {
//            override fun onItemChildClick(
//                adapter: BaseQuickAdapter<*, *>,
//                view: View,
//                position: Int
//            ) {
//
//            }
//
//        })
        rvSystem.adapter = articleAdapter
    }

    override fun initData() {
        articleList.clear()
        articleAdapter.setList(articleList)
        val bundle = intent.extras
        cid = bundle?.getInt(Constants.SYSTEM_ID)
        title = bundle?.getString(Constants.SYSTEM_TITLE)
        loadData()
    }

    private fun loadData() {
        sysLoading.loading()
        pageNum = 0
        cid?.let { presenter?.getArticleListOfTag(pageNum, it) }
    }

    private fun loadMore() {
        pageNum += 1
        cid?.let { presenter?.getArticleListOfTag(pageNum, it) }
    }

    override fun layoutId(): Int {
        return R.layout.activity_system
    }

    override fun createPresenter(): SystemContract.Presenter? {
        return SystemPresenter(this)
    }

    override fun showList(t: Wrapper<Article>) {
        dismissRefresh()
        "dismiss".log()
        if (t.datas.isNotEmpty()) {
            articleList.addAll(t.datas)
            articleAdapter.setList(articleList)
        }
    }

    private fun dismissRefresh(){
        sysLoading.dismiss()
        if (smartRefresh.state.isOpening){
            "is openning".log()
            smartRefresh.finishLoadMore()
            smartRefresh.finishRefresh()
        }
    }

    override fun collectSucess() {

    }

    override fun unCollecSuccess() {
    }

    override fun reload() {
        loadData()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        "refresh".log()
        dismissRefresh()
        "dismiss".log()
    }
}
