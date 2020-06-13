package com.example.wanandroid_kt.ext

import android.widget.Toast
import com.example.wanandroid_kt.MyApplication
import com.example.wanandroid_kt.utils.ColorUtil

fun Int.toast(duration: Int = Toast.LENGTH_SHORT){
    val showStr = MyApplication.mContext.resources.getString(this)
    Toast.makeText(MyApplication.mContext, showStr, duration).show()
}


fun Int.getColor() : Int{
    return ColorUtil.getColor(this)
}

fun Int.str() : String{
    return MyApplication.mContext.resources.getString(this)
}
