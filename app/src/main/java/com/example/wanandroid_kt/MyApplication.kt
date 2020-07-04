package com.example.wanandroid_kt

import android.app.Application
import android.content.Context
import com.example.wanandroid_kt.ext.getColor
import com.example.wanandroid_kt.view.LoadMoreFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout


class MyApplication : Application(){

    companion object{
        private val TAG = "MyApplication"

        lateinit var mContext : Context

        init {
            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ ->
                MaterialHeader(context).setColorSchemeColors(R.color.theme.getColor())
            }

            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
                LoadMoreFooter(context)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
    }
}