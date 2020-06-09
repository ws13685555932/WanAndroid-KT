package com.example.wanandroid_kt.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import com.example.wanandroid_kt.R

/**
 * des 辅助站位图
 *
 * @author zs
 * @date 2020-03-12
 */
class LoadingTip : RelativeLayout {

    private var llEmpty: LinearLayout? = null
    private var pbLoading: ProgressBar? = null
    private var llInternetError: LinearLayout? = null

    private var reloadListener: ReloadListener? = null

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context)
    }

    private fun initView(context: Context) {
        val view = View.inflate(context, R.layout.view_tint, this)
        llEmpty = view.findViewById(R.id.llEmpty)
        pbLoading = view.findViewById(R.id.pbLoading)
        llInternetError = view.findViewById(R.id.llInternetError)
        llInternetError?.setOnClickListener {
            reloadListener?.reload()
        }
    }

    fun setReloadListener(reloadListener: ReloadListener){
        this.reloadListener = reloadListener
    }

    /**
     * 显示空白页
     */
    fun showEmpty() {
        visibility = View.VISIBLE
        llEmpty?.visibility = View.VISIBLE
        pbLoading?.visibility = View.GONE
        llInternetError?.visibility = View.GONE
    }

    /**
     * 显示网络错误
     */
    fun showInternetError() {
        visibility = View.VISIBLE
        llInternetError?.visibility = View.VISIBLE
        llEmpty?.visibility = View.GONE
        pbLoading?.visibility = View.GONE
    }

    /**
     * 加载
     */
    fun loading() {
        visibility = View.VISIBLE
        pbLoading?.visibility = View.VISIBLE
        llInternetError?.visibility = View.GONE
        llEmpty?.visibility = View.GONE

    }

    /**
     * 隐藏loadingTip
     */
    fun dismiss() {
        visibility = View.GONE
    }

    interface ReloadListener{
        fun reload()
    }
}