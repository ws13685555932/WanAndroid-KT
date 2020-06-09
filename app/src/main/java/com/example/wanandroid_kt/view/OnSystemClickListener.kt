package com.example.wanandroid_kt.view

import com.chad.library.adapter.base.viewholder.BaseViewHolder

interface OnSystemClickListener {
    /**
     * @param i 外层角标
     * @param j 内层角标
     */
    fun onCollectClick(helper: BaseViewHolder, i: Int, j: Int)
}