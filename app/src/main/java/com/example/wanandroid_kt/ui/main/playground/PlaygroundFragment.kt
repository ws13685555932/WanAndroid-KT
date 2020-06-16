package com.example.wanandroid_kt.ui.main.playground

import android.os.Build
import android.view.View
import androidx.fragment.app.Fragment
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.adapter.FragmentListAdapter
import com.example.wanandroid_kt.adapter.TabAdapter
import com.example.wanandroid_kt.base.AppLazyFragment
import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.ext.str
import com.example.wanandroid_kt.ext.toast
import com.example.wanandroid_kt.ui.main.playground.navigation.NavigationFragment
import com.example.wanandroid_kt.ui.main.playground.systemlist.SystemListFragment
import kotlinx.android.synthetic.main.fragment_playground.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import java.time.Clock.system

class PlaygroundFragment : AppLazyFragment<IBasePresenter<*>>(),
    TabAdapter.OnTabClickListener {

    private var tabList = mutableListOf<String>()

    override fun lazyInit() {

        tabList.add(R.string.system_tab.str())
        tabList.add(R.string.navigation_tab.str())

        initFragment()
    }

    private fun initFragment() {
        val fragmentList = mutableListOf<Fragment>()
        fragmentList.add(SystemListFragment())
        fragmentList.add(NavigationFragment())
        val adapter = FragmentListAdapter(fragmentList, childFragmentManager)
        vpContent.offscreenPageLimit = 0
        vpContent.adapter = adapter

        val commonNavigator = CommonNavigator(context)
        val tabAdapter = TabAdapter(tabList)
        tabAdapter.setOnTabClickListener(this)
        commonNavigator.adapter = tabAdapter
        miTab.navigator = commonNavigator
        ViewPagerHelper.bind(miTab, vpContent)
    }


    override fun layoutId(): Int {
        return R.layout.fragment_playground
    }

    override fun onError(error: String) {
        error.toast()
    }

    override fun onTabClick(view: View, index: Int) {
        vpContent.currentItem = index
    }

    override fun createPresenter(): IBasePresenter<*>? {
        return null
    }

}