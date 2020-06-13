package com.example.wanandroid_kt.ext

import android.util.Log
import android.widget.Toast
import com.example.wanandroid_kt.MyApplication

fun String.toast(duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(MyApplication.mContext, this, duration).show()
}

fun String.log(){
    Log.d("myapp", this)
}