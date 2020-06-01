package com.example.wanandroid_kt.net

class BaseResponse<T> {
    var data: T? = null
    var errorMsg = ""
    var errorCode = 0
}