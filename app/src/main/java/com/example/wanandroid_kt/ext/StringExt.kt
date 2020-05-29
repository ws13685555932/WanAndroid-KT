package com.example.wanandroid_kt.ext

import android.content.Context
import android.widget.Toast

fun String.toast(context: Context, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(context, this, duration).show()
}