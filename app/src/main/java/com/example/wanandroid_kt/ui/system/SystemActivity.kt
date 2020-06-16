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
import kotlinx.android.synthetic.main.include_toolbar.*
import kotlinx.android.synthetic.main.item_project.*


class SystemActivity : AppBaseActivity<SystemContract.Presenter>(),
    SystemContract.View, LoadingTip.ReloadListener, OnRefreshListener, OnLoadMoreListener {

    private var cid: Int? = null
    private var title: String? = null
    private var currentPosition = 0
    private var pageNum = 0

    private lateinit var articleAdapter: ArticleAdapter
    private var articleList: MutableList<Article> = mutableListOf<Article>()



    override fun initView() {
        sysLoading.setReloadListener(this)

        ivBack.setOnClickListener {
            finish()
        }



        smartRefresh.setOnRefreshListener(this)
        smartRefresh.setOnLoadMoreListener(this)
        rvSystem.layoutManager = LinearLayoutManager(this)
        articleAdapter = ArticleAdapter()
        articleAdapter.addChildClickViewIds(R.id.ivCollect);
        articleAdapter.setOnItemChildClickListener(object : OnItemChildClickListener {
            override fun onItemChildClick(
                adapter: BaseQuickAdapter<*, *>,
                view: View,
                position: Int
            ) {
                if(view.id == R.id.ivCollect){
                    val item : Article = adapter.getItem(position) as Article
                    currentPosition = position
                    if(item.collect){
                        "uncollect".log()
                        presenter?.uncollect(articleList[position].id)
                    }else{
                        "collect".log()
                        presenter?.collect(articleList[position].id)
                    }

                }
            }

        })
        rvSystem.adapter = articleAdapter
    }

    override fun initData() {
        articleList.clear()
        articleAdapter.setList(articleList)
        val bundle = intent.extras
        cid = bundle?.getInt(Constants.SYSTEM_ID)
        title = bundle?.getString(Constants.SYSTEM_TITLE)
        tvTitleTool.text = title
        loadData()
    }

    private fun loadData() {
        sysLoading.loading()
        pageNum = 0
        cid?.let { presenter?.getArticleListOfTag(pageNum, it) }
    }

    override fun layoutId(): Int {
        return R.layout.activity_system
    }

    override fun createPresenter(): SystemContract.Presenter? {
        return SystemPresenter(this)
    }

    override fun showList(t: Wrapper<Article>) {
        dismissLoadMore()
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
            smartRefresh.finishLoadMore(false)
            smartRefresh.finishRefresh(false)
        }
    }

    private fun dismissLoadMore(){
        sysLoading.dismiss()
        if (smartRefresh.state.isOpening){
            "is openning".log()
            smartRefresh.finishLoadMore()
            smartRefresh.finishRefresh()
        }
    }

    override fun collectSucess() {
        "collect success".log()
        articleList[currentPosition].collect = true
        articleAdapter.notifyItemChanged(currentPosition)

    }

    override fun unCollecSuccess() {
        articleList[currentPosition].collect = false
        articleAdapter.setData(currentPosition, articleList[currentPosition])
    }

    override fun reload() {
        sysLoading.loading()
        loadData()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
//        loadData()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        pageNum++
        cid?.let { presenter?.getArticleListOfTag(pageNum, it) }
    }
}
