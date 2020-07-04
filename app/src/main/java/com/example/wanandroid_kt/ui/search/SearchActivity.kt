package com.example.wanandroid_kt.ui.search

import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.adapter.ArticleAdapter
import com.example.wanandroid_kt.base.AppBaseActivity
import com.example.wanandroid_kt.const.Constants
import com.example.wanandroid_kt.entity.Article
import com.example.wanandroid_kt.ext.toast
import com.example.wanandroid_kt.utils.SharedPrefUtil
import com.jakewharton.rxbinding4.widget.editorActions
import com.jakewharton.rxbinding4.widget.textChanges
import com.zs.wanandroid.ui.search.SearchContract
import com.zs.wanandroid.ui.search.SearchPresenter
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_system.smartRefresh

class SearchActivity : AppBaseActivity<SearchContract.Presenter>(),
    SearchContract.View {

    private var keyWord: String? = null
    private var rlInitWidth: Int = 0
    private var historyList: MutableList<String>? = null

    private var pageNum: Int = 0
    private var articleList = mutableListOf<Article>()
    private lateinit var articleAdapter: ArticleAdapter
    private var currentPosition = 0


    override fun initData() {
        initHistory()
    }

    private fun initHistory() {

        historyList = SharedPrefUtil.getList(Constants.HISTORY_SEARCH)

        labelsView.setLabels(historyList)
        labelsView.setOnLabelClickListener { _, data, _ ->

        }
    }

    override fun initView() {
        tvCancel.setOnClickListener { finish() }
        ivClear.setOnClickListener { etSearch.setText("") }

        etSearch.textChanges()
            .subscribe {
                keyWord = it.toString()
                if (it.isEmpty()) {
                    ivClear.visibility = View.GONE
                } else {
                    ivClear.visibility = View.VISIBLE
                }
            }

        etSearch.editorActions()
            .subscribe {
                if (!TextUtils.isEmpty(keyWord)) {
                    //将已存在的key移除，避免存在重复数据
                    for (index in 0 until historyList?.size!!){
                        if (historyList!![index]==keyWord) {
                            historyList!!.removeAt(index)
                            break
                        }
                    }
                    historyList?.add(keyWord!!)
                    searchKeyword()
                }else {
                    "请输入关键字".toast()
                }
            }

        articleAdapter = ArticleAdapter()
        articleAdapter.setList(articleList)
        articleAdapter.setOnCollectedListener(object :ArticleAdapter.OnCollectedListener{
            override fun onCollected(collected: Boolean, position: Int) {
                currentPosition = position
                if (collected) {
                    presenter?.collect(articleList[position].originId)
                }else{
                    presenter?.unCollect(articleList[position].originId)
                }
            }


        })
        rvSearch.layoutManager = LinearLayoutManager(this)
        rvSearch.adapter = articleAdapter


        smartRefresh.setOnLoadMoreListener {
            pageNum += 1
            presenter?.search(pageNum, keyWord!!)
        }

    }

    private fun searchKeyword() {
        pageNum = 0
        keyWord = etSearch.text!!.toString()
        if (keyWord.toString().isEmpty()){
            "请输入关键字".toast()
        }else{
            etSearch.setText(keyWord)
            smartRefresh.visibility = View.VISIBLE
            clRecord.visibility =View.GONE
            loadingTip.loading()
            presenter?.search(pageNum, keyWord!!)
        }
    }


    override fun layoutId(): Int {
        return R.layout.activity_search
    }

    override fun createPresenter(): SearchContract.Presenter? {
        return SearchPresenter(this)
    }

    override fun showList(list: MutableList<Article>) {

        if (list.isNotEmpty()) {
            dismiss()
            articleList.addAll(list)
            articleAdapter.setList(articleList)
        } else {
            dismissNoData()
            "没有更多数据了".toast()
        }
    }

    private fun dismissNoData() {
        loadingTip.dismiss()
        if (smartRefresh.state.isOpening) {
            smartRefresh.finishLoadMoreWithNoMoreData()
            smartRefresh.setNoMoreData(true)
        }
    }

    fun dismiss(){
        loadingTip.dismiss()
        if (smartRefresh.state.isOpening) {
            smartRefresh.finishLoadMore()
            smartRefresh.finishRefresh()
        }
    }

    override fun collectSuccess() {
        articleList[currentPosition].collect = true
        articleAdapter.setData(currentPosition, articleList[currentPosition])
    }

    override fun unCollectSuccess() {
        articleList[currentPosition].collect = false
        articleAdapter.setData(currentPosition, articleList[currentPosition])
    }


}
