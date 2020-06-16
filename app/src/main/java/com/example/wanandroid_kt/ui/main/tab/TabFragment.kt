package com.zs.wanandroid.ui.main.tab

import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.adapter.FragmentListAdapter
import com.example.wanandroid_kt.adapter.TabAdapter
import com.example.wanandroid_kt.base.AppBaseFragment
import com.example.wanandroid_kt.entity.TabEntity
import com.example.wanandroid_kt.ext.toast
import com.zs.wanandroid.ui.main.tab.list.TabListFragment
import kotlinx.android.synthetic.main.fragment_project.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator

/**
 * 项目/公众号界面
 *
 * @author zs
 * @date 2020-03-14
 */
class TabFragment : AppBaseFragment<TabContract.Presenter>()
    ,TabContract.View , TabAdapter.OnTabClickListener {

    private var tabList = mutableListOf<TabEntity>()
    private var type:Int? = null

    override fun initView(){
        type = arguments?.getInt("type")
        Log.i(TAG,"type:$type")
        type?.let { presenter?.loadData(it) }
    }


    override fun showList(list: MutableList<TabEntity>) {
        tabList.clear()
        tabList.addAll(list)
        initFragment()
    }

    override fun onError(error: String) {
        error.toast()
    }

    private fun initFragment(){
        val fragmentList = mutableListOf<Fragment>()
        val list = mutableListOf<String>()
        tabList.forEach {
            val fragment = TabListFragment()
            val bundle = Bundle()
            type?.let { it1 -> bundle.putInt("type", it1) }
            bundle.putInt("id", it.id)
            bundle.putString("name", it.name)
            fragment.arguments = bundle
            fragmentList.add(fragment)
            it.name.let { it1 -> list.add(it1) }
        }
        val adapter = FragmentListAdapter(fragmentList,childFragmentManager)
        vpContent.offscreenPageLimit = 6
        vpContent.adapter = adapter
        val commonNavigator = CommonNavigator(context)
        val tabAdapter = TabAdapter(list)
        //tab点击事件
        tabAdapter.setOnTabClickListener(this)
        commonNavigator.adapter = tabAdapter
        miTab.navigator = commonNavigator
        //将miTab和voContent进行绑定
        ViewPagerHelper.bind(miTab,vpContent)
    }


    override fun createPresenter(): TabContract.Presenter? {
        return TabPresenter(this)
    }


    override fun onTabClick(view: View,index:Int) {
        vpContent.currentItem = index
    }

    override fun layoutId(): Int {
        return R.layout.fragment_project
    }
}
