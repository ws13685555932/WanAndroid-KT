package com.zs.wanandroid.ui.my

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.base.AppBaseActivity
import com.example.wanandroid_kt.entity.MyArticle
import kotlinx.android.synthetic.main.activity_my_article.*
import kotlinx.android.synthetic.main.fragment_systemlist.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * 我的文章
 * @author zs
 * @date 2020-03-17
 */
class MyArticleActivity : AppBaseActivity<MyArticleContract.Presenter>()
    ,MyArticleContract.View {
    override fun layoutId(): Int {
        return R.layout.activity_my_article
    }

    override fun createPresenter(): MyArticleContract.Presenter? {
        return MyArticlePresenter(this)
    }

    override fun showList(t: MyArticle) {
    }

    override fun deleteSuccess() {
    }

//    private var currentPosition = 0
//    private var articleAdapter: MyArticleAdapter? = null
//    private var pageNum = 1
//    private var articleList = mutableListOf<ArticleEntity.DatasBean>()
//
//    override fun init(savedInstanceState: Bundle?) {
//        initView()
//        loadingTip.loading()
//        loadData()
//    }
//
//    private fun initView(){
//        EventBus.getDefault().register(this)
//        ivBack.setOnClickListener {
//            finish()
//        }
//        ivAdd.setOnClickListener {
//            intent(ShareArticleActivity::class.java,true)
//        }
//        loadingTip.setReloadListener(this)
//        smartRefresh?.setOnRefreshListener(this)
//        smartRefresh?.setOnLoadMoreListener(this)
//        rvMyArticle.layoutManager = LinearLayoutManager(this)
//        articleAdapter = MyArticleAdapter(R.layout.item_my_article)
//        articleAdapter?.onItemChildClickListener = this
//        rvMyArticle.adapter = articleAdapter
//    }
//
//    /**
//     * 加载数据
//     * 初始化，网络出错重新加载，刷新均可使用
//     */
//    private fun loadData(){
//        articleList.clear()
//        articleAdapter?.setNewData(articleList)
//        pageNum = 1
//        presenter?.loadData(pageNum)
//    }
//
//    override fun showList(t: MyArticleEntity) {
//        dismissRefresh()
//        loadingTip.dismiss()
//        if (t.shareArticles?.datas?.isNotEmpty()!!){
//            articleList.addAll(t.shareArticles?.datas!!)
//            articleAdapter?.setNewData(articleList)
//        }else {
//            if (articleList.size==0)loadingTip.showEmpty()
//            else ToastUtils.show("没有数据啦...")
//        }
//    }
//
//    override fun deleteSuccess() {
//        articleAdapter?.delete(currentPosition)
//    }
//
//    override fun onError(error: String) {
//        //请求失败将page -1
//        if (pageNum>0)pageNum--
//        dismissRefresh()
//        ToastUtils.show(error)
//    }
//
//    override fun onLoadMore(refreshLayout: RefreshLayout) {
//        pageNum++
//        presenter?.loadData(pageNum)
//    }
//
//    override fun onRefresh(refreshLayout: RefreshLayout) {
//        loadData()
//    }
//
//    override fun reload() {
//        loadingTip.loading()
//        loadData()
//    }
//
//    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
//        Log.i(TAG,"id:${view?.id}")
//        when(view?.id){
//            R.id.rlContent->{
//                intent(Bundle().apply {
//                    putString(Constants.WEB_URL,articleList[position].link)
//                    putString(Constants.WEB_TITLE,articleList[position].title)
//                }, WebActivity::class.java,false)
//            }
//            R.id.tvDelete->{
//                if (position<articleList.size){
//                    //记录当前点击的item
//                    currentPosition = position
//                    presenter?.delete(articleList[position].id)
//                }
//            }
//
//        }
//    }
//
//
//    override fun createPresenter(): MyArticleContract.Presenter<MyArticleContract.View>? {
//        return MyArticlePresenter(this)
//    }
//
//    override fun getLayoutId(): Int {
//        return R.layout.activity_my_article
//    }
//
//    override fun getContext(): Context? {
//        return this
//    }
//
//    /**
//     * 隐藏刷新加载
//     */
//    private fun dismissRefresh() {
//        loadingTip.dismiss()
//        if (smartRefresh.state.isOpening) {
//            smartRefresh.finishLoadMore()
//            smartRefresh.finishRefresh()
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        EventBus.getDefault().unregister(this)
//    }
//
//    /**
//     * 登陆消息
//     */
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public fun shareEvent(shareEvent: ShareEvent){
//        loadData()
//    }

}
