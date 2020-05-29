package com.example.wanandroid_kt.base

import android.content.Context

interface IBaseView {
    fun getContext(): Context?
    fun onError(error: String)
}