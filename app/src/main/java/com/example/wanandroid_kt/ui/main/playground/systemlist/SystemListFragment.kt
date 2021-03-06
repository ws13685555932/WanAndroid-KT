package com.example.wanandroid_kt.ui.main.playground.systemlist

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.adapter.SystemAdapter
import com.example.wanandroid_kt.base.AppLazyFragment
import com.example.wanandroid_kt.const.Constants
import com.example.wanandroid_kt.entity.TabEntity
import com.example.wanandroid_kt.ui.system.SystemActivity
import com.example.wanandroid_kt.view.OnSystemClickListener
import kotlinx.android.synthetic.main.fragment_systemlist.*

class SystemListFragment :AppLazyFragment<SystemListContract.Presenter>(), SystemListContract.View,
    OnSystemClickListener {

    private var systemList : MutableList<TabEntity>? = null
    private lateinit var systemAdapter : SystemAdapter

    override fun lazyInit() {
        rvSystem.layoutManager = LinearLayoutManager(context)
        systemAdapter = SystemAdapter()
        systemAdapter.setOnSystemClickListener(this)
        rvSystem.adapter = systemAdapter
        loadingTip.loading()
        presenter?.loadSystemList()
    }

    override fun createPresenter(): SystemListContract.Presenter? {
        return SystemListPresenter(this)
    }

    override fun layoutId(): Int {
        return R.layout.fragment_systemlist
    }

    override fun showList(t: MutableList<TabEntity>) {
        loadingTip.dismiss()
        systemList = t
        systemAdapter.setList(systemList)
    }

    override fun onCollectClick(helper: BaseViewHolder, i: Int, j: Int) {
        val id = systemList?.get(i)?.children?.get(j)?.id
        val title = systemList?.get(i)?.children?.get(j)?.name

        goto(Bundle().apply {
            id?.let { putInt(Constants.SYSTEM_ID, it) }
            putString(Constants.SYSTEM_TITLE, title)
        }, SystemActivity::class.java,false)
    }

    override fun onError(error: String) {
        super.onError(error)
        loadingTip.showInternetError()
    }

}