package com.example.wanandroid_kt.ext

import android.widget.Toast
import com.example.wanandroid_kt.MyApplication

fun Int.toast(duration: Int = Toast.LENGTH_SHORT){
    val showStr = MyApplication.context.resources.getString(this)
    Toast.makeText(MyApplication.context, showStr, duration).show()
}