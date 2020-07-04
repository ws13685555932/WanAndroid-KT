package com.example.wanandroid_kt.view

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.example.wanandroid_kt.R
import com.scwang.smart.refresh.layout.api.RefreshFooter
import com.scwang.smart.refresh.layout.api.RefreshKernel
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.scwang.smart.refresh.layout.constant.SpinnerStyle

open class LoadMoreFooter(context: Context) : LinearLayout(context), RefreshFooter {

    private lateinit var mLoading: ProgressBar
    private lateinit var mTitleText: TextView

    private var mTextPulling = "上拉加载更多"
    private var mTextRelease = "释放立即加载"
    private var mTextLoading = "正在加载..."
    private var mTextRefreshing = "正在刷新..."
    private var mTextFinish = "加载完成"
    private var mTextFailed = "加载失败"
    private var mTextNothing = "没有更多数据了"

    private var mNoMoreData = false
    private var mFinishDuration = 500

    init {
        View.inflate(context, R.layout.footer_loadmore, this)
        val thisView = this
        mLoading = this.findViewById(R.id.pbLoading)
        mTitleText = this.findViewById(R.id.tvState)
    }

    override fun getSpinnerStyle(): SpinnerStyle {
        return SpinnerStyle.Translate
    }

    override fun onFinish(refreshLayout: RefreshLayout, success: Boolean): Int {
        if (!mNoMoreData) {
            mTitleText.text = if (success) mTextFinish else mTextFailed
            mLoading.visibility = View.GONE
            return mFinishDuration
        }
        return 0
    }

    override fun onInitialized(kernel: RefreshKernel, height: Int, maxDragHeight: Int) {
    }

    override fun onHorizontalDrag(percentX: Float, offsetX: Int, offsetMax: Int) {
    }

    override fun setNoMoreData(noMoreData: Boolean): Boolean {
        if (mNoMoreData != noMoreData) {
            mNoMoreData = noMoreData
            if (noMoreData) {
                mTitleText.text = mTextNothing
                mLoading.visibility = View.GONE
            } else {
                mTitleText.text = mTextPulling
                mLoading.visibility = View.VISIBLE
            }
        }
        return true
    }

    override fun onReleased(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {
    }

    override fun getView(): View {
        return this
    }

    override fun setPrimaryColors(vararg colors: Int) {
    }

    override fun onStartAnimator(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {
    }

    override fun onStateChanged(
        refreshLayout: RefreshLayout,
        oldState: RefreshState,
        newState: RefreshState
    ) {
        if (!mNoMoreData) {
            when (newState) {
                RefreshState.None -> {
                    mLoading.visibility = View.GONE
                    mTitleText.text = mTextPulling
                }
                RefreshState.PullUpToLoad -> {
                    mTitleText.text = mTextPulling
                }
                RefreshState.Loading, RefreshState.LoadReleased -> {
                    mLoading.visibility = View.VISIBLE
                    mTitleText.text = mTextLoading
                }
                RefreshState.ReleaseToLoad -> {
                    mTitleText.text = mTextRelease
                }
                RefreshState.Refreshing -> {
                    mTitleText.text = mTextRefreshing
                    mLoading.visibility = View.GONE
                }
            }
        }
    }

    override fun onMoving(
        isDragging: Boolean,
        percent: Float,
        offset: Int,
        height: Int,
        maxDragHeight: Int
    ) {
    }

    override fun isSupportHorizontalDrag(): Boolean {
        return false
    }


}