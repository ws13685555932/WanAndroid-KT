package com.example.wanandroid_kt.ui.main.playground.navigation

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.adapter.NavigationAdapter
import com.example.wanandroid_kt.base.AppLazyFragment
import com.example.wanandroid_kt.entity.NaviEntity
import com.example.wanandroid_kt.ext.toast
import com.example.wanandroid_kt.view.OnSystemClickListener
import kotlinx.android.synthetic.main.fragment_navigation.*
import kotlinx.android.synthetic.main.fragment_systemlist.*

class NavigationFragment : AppLazyFragment<NavigationContract.Presenter>(),
        NavigationContract.View, OnSystemClickListener {

    private var navigationList = mutableListOf<NaviEntity>()
    private lateinit var navigationAdapter : NavigationAdapter

    override fun lazyInit() {
        rvNavigation.layoutManager = LinearLayoutManager(context)
        navigationAdapter = NavigationAdapter()
        navigationAdapter.setOnSystemClickListener(this)
        rvNavigation.adapter = navigationAdapter
        loadingTipNav.loading()
        presenter?.getNavigations()

    }

    override fun createPresenter(): NavigationContract.Presenter? {
        return NavigationPresenter(this)
    }

    override fun layoutId(): Int {
        return R.layout.fragment_navigation
    }

    override fun onCollectClick(helper: BaseViewHolder, i: Int, j: Int) {
        navigationList[i].articles[j].title?.toast()
    }

    override fun showList(t: MutableList<NaviEntity>) {
        loadingTipNav.dismiss()
        this.navigationList = t
        navigationAdapter.setList(t)
    }

    override fun onError(error: String) {
        super.onError(error)
        loadingTipNav.showInternetError()
    }

}