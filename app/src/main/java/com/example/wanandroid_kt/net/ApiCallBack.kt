package com.example.wanandroid_kt.net

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.lang.reflect.ParameterizedType
import java.net.UnknownHostException

abstract class ApiCallBack <T> : Observer<BaseResponse<T>> {
    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
        disposible(d)
    }

    override fun onNext(t: BaseResponse<T>) {
        if (t.errorCode == 0){
            t.data?.let { success(it) }
        }else{
            onError(ApiException("another error"))
        }
    }

    override fun onError(e: Throwable) {
        val errorMsg = if (e is UnknownHostException){
            "unknown host exception"
        }else{
            "unknown error"
        }
        error(errorMsg)
    }

    abstract fun disposible(d : Disposable)
    abstract fun success(t: T)
    abstract fun error(errorMsg : String)
}