package com.example.wanandroid_kt.utils

import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.example.wanandroid_kt.MyApplication

object ColorUtil {
    fun getColor(@ColorRes colorId: Int): Int {
        return ContextCompat.getColor(MyApplication.mContext, colorId)
    }
}