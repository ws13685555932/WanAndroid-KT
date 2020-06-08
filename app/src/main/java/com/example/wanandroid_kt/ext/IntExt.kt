package com.example.wanandroid_kt.ext

import com.example.wanandroid_kt.utils.ColorUtil

fun Int.getColor() : Int{
    return ColorUtil.getColor(this)
}