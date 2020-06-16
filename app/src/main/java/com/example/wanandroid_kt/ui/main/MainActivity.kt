package com.example.wanandroid_kt.ui.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.base.AppBaseActivity
import com.example.wanandroid_kt.base.IBasePresenter
import com.example.wanandroid_kt.base.IBaseView
import com.example.wanandroid_kt.const.Constants
import com.example.wanandroid_kt.net.RetrofitManager
import com.example.wanandroid_kt.ui.main.home.HomeFragment
import com.example.wanandroid_kt.ui.main.mine.MineFragment
import com.example.wanandroid_kt.ui.main.playground.PlaygroundFragment
import com.zs.wanandroid.ui.main.tab.TabFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppBaseActivity<IBasePresenter<*>>() {

    private var lastIndex = 0
    private var fragments: MutableList<Fragment> = mutableListOf()

    override fun initView() {
        super.initView()
        initFragment()
        initBottomTab()
    }

    private fun initFragment() {
        fragments.add(HomeFragment())
        val project = TabFragment()
        val proBundle = Bundle()
        proBundle.putInt("type", Constants.PROJECT_TYPE)
        project.arguments = proBundle
        fragments.add(project)
        //体系
        fragments.add(PlaygroundFragment())
        //公众号
        val account = TabFragment()
        val accountBundle = Bundle()
        accountBundle.putInt("type", Constants.ACCOUNT_TYPE)
        account.arguments = accountBundle
        fragments.add(account)
        fragments.add(MineFragment())
        switchTo(lastIndex)
    }

    private fun initBottomTab() {
        bnvTab.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> switchTo(0)
                R.id.menu_project -> switchTo(1)
                R.id.menu_playground -> switchTo(2)
                R.id.menu_discovery -> switchTo(3)
                R.id.menu_mine -> switchTo(4)
            }
            true
        }
    }

    private fun switchTo(position: Int) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        val targetFragment: Fragment = fragments[position]
        val lastFragment: Fragment = fragments[lastIndex]
        lastIndex = position
        ft.hide(lastFragment)
        if (!targetFragment.isAdded) {
            supportFragmentManager.beginTransaction().remove(targetFragment).commit()
            ft.add(R.id.flContent, targetFragment)
            ft.setMaxLifecycle(targetFragment, Lifecycle.State.RESUMED)
        }
        ft.show(targetFragment)
        ft.commit()

    }


    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun createPresenter(): IBasePresenter<*>? {
        return null
    }
}


