package com.example.wanandroid_kt.view

import com.example.wanandroid_kt.ext.log
import com.example.wanandroid_kt.net.ApiException
import com.example.wanandroid_kt.net.BaseResponse
import com.google.gson.JsonParseException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONException
import java.lang.reflect.ParameterizedType
import java.net.ConnectException
import java.net.UnknownHostException

abstract class ViewCallback<T> : Observer<T> {

    abstract fun disposible(d : Disposable)
    abstract fun success(t: T)
    abstract fun error(errorMsg : String)

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
        disposible(d)
    }

    override fun onNext(t: T) {
        success(t)
    }

    override fun onError(e: Throwable) {
        e.message?.let { error(it) }
    }
}