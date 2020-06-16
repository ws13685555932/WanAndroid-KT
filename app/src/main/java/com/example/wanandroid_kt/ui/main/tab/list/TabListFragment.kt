package com.zs.wanandroid.ui.main.tab.list


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.adapter.ArticleAdapter
import com.example.wanandroid_kt.base.AppLazyFragment
import com.example.wanandroid_kt.entity.Article
import com.example.wanandroid_kt.ext.log
import com.example.wanandroid_kt.ext.toast
import com.example.wanandroid_kt.utils.AppUtil
import kotlinx.android.synthetic.main.activity_system.*
import kotlinx.android.synthetic.main.fragment_article_list.*
import kotlinx.android.synthetic.main.fragment_systemlist.*

/**
 * des 项目/公众号列表
 *
 * @author zs
 * @date 2020-03-14
 */
class TabListFragment : AppLazyFragment<TabListContract.Presenter>()
    , TabListContract.View {

    private var projectList = mutableListOf<Article>()
    private var pageNum = 1
    private var projectId: Int = 0
    private var name: String? = null
    private lateinit var projectAdapter: ArticleAdapter
    private var currentPosition = 0
    private var type: Int? = null
    override fun lazyInit() {
        type = arguments?.getInt("type")
        projectId = arguments?.getInt("id") ?: 0
        name = arguments?.getString("name") ?: ""
        initView()
        loadData()
    }

    override fun initView() {
        projectAdapter = ArticleAdapter()
        projectAdapter.setList(projectList)
        projectAdapter.addChildClickViewIds(R.id.ivCollect);
        projectAdapter.setOnItemChildClickListener(object : OnItemChildClickListener {
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
                        presenter?.unCollect(projectList[position].id)
                    }else{
                        "collect".log()
                        presenter?.collect(projectList[position].id)
                    }

                }
            }

        })
        rvProject.layoutManager = LinearLayoutManager(context)
        rvProject.adapter = projectAdapter
    }

    private fun loadData() {
        loadTab.loading()
        projectList.clear()
        projectAdapter.setList(projectList)
        pageNum = 1
        type?.let { presenter?.loadData(it, projectId, pageNum) }
    }

    override fun showList(list: MutableList<Article>) {
//        dismissRefresh()
        loadTab.dismiss()
        if (list.isNotEmpty()) {
            projectList.addAll(list)
            projectAdapter.setList(projectList)
        } else {
            if (projectList.size == 0) loadTab.showEmpty()
            else "没有数据啦...".toast()
        }
    }

    /**
     * 收藏成功
     */
    override fun collectSuccess() {
        if (currentPosition < projectList.size) {
            projectList[currentPosition].collect = true
            projectAdapter.notifyItemChanged(currentPosition)
        }
    }

    /**
     * 取消收藏成功
     */
    override fun unCollectSuccess() {
        if (currentPosition < projectList.size) {
            projectList[currentPosition].collect = false
            projectAdapter.notifyItemChanged(currentPosition)
        }
    }

    override fun onError(error: String) {
        //请求失败将page -1
        if (pageNum > 1) pageNum--
        loadTab.dismiss()
    }


    override fun createPresenter(): TabListContract.Presenter? {
        return TabListPresenter(this)
    }


    override fun layoutId(): Int {
        return R.layout.fragment_article_list
    }


}
