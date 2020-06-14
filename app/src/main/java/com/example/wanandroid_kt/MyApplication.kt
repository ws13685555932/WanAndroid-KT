package com.example.wanandroid_kt

import android.app.Application
import android.content.Context
import com.example.wanandroid_kt.ext.getColor
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.BezierRadarHeader
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout

class MyApplication : Application(){

    companion object{
        private val TAG = "MyApplication"

        lateinit var mContext : Context

        init {
            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                layout.setPrimaryColorsId(R.color.theme, android.R.color.white)//全局设置主题颜色
                MaterialHeader(context)//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
    }
}