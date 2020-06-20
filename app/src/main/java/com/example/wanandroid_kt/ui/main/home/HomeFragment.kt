package com.example.wanandroid_kt.ui.main.home

import android.graphics.Color
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.wanandroid_kt.MyApplication
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.adapter.ArticleAdapter
import com.example.wanandroid_kt.base.AppLazyFragment
import com.example.wanandroid_kt.entity.Article
import com.example.wanandroid_kt.entity.Banner
import com.example.wanandroid_kt.ext.log
import com.example.wanandroid_kt.utils.AppUtil
import com.to.aboomy.pager2banner.IndicatorView
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : AppLazyFragment<HomeContract.Presenter<HomeContract.View>>(), HomeContract.View{

    private lateinit var mAdapter : BaseQuickAdapter<Banner, BaseViewHolder>
    private lateinit var mBannerList :  MutableList<Banner>
    private lateinit var mArticleAdapter : ArticleAdapter
    private var mArticleList = mutableListOf<Article>()
    private var pageNum = 0

    override fun lazyInit() {
        presenter?.getBanner()
        presenter?.getArticles(pageNum)
    }

    override fun initView() {
        val indicator = IndicatorView(MyApplication.mContext)
            .setIndicatorColor(Color.DKGRAY)
            .setIndicatorSelectorColor(Color.WHITE)
        mAdapter = object : BaseQuickAdapter<Banner, BaseViewHolder>(R.layout.item_banner){
            override fun convert(holder: BaseViewHolder, item: Banner) {
                Glide.with(MyApplication.mContext)
                    .load(item.imagePath)
                    .into(holder.getView(R.id.ivBanner))
                item.imagePath.log()
            }
        }
        mBannerList = mutableListOf()
        mAdapter.setList(mBannerList)
        banner.setIndicator(indicator).adapter = mAdapter

        mArticleAdapter = ArticleAdapter()
        mArticleAdapter.setList(mArticleList)
        mArticleAdapter.loadMoreModule.setOnLoadMoreListener {
            loadMore()
        }
        rvArticle.adapter = mArticleAdapter
        rvArticle.layoutManager = LinearLayoutManager(context)

    }

    private fun loadMore(){
        pageNum += 1
        presenter?.getArticles(pageNum)
    }

    override fun createPresenter(): HomeContract.Presenter<HomeContract.View>? {
        return HomePresenter(this)
    }

    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    override fun showBanner(t: MutableList<Banner>) {
        mBannerList.addAll(t)
        mAdapter.setList(mBannerList)
    }

    override fun showList(t: MutableList<Article>) {
        mArticleList.addAll(t)
        mArticleAdapter.setList(mArticleList)
    }

    override fun onError(error: String) {
    }

}