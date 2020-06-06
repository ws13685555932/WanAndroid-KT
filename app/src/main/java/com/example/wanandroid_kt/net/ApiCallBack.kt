package com.example.wanandroid_kt.net

import com.google.gson.JsonParseException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONException
import java.lang.reflect.ParameterizedType
import java.net.ConnectException
import java.net.UnknownHostException

abstract class ApiCallBack <T> : Observer<BaseResponse<T>> {
    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
        disposible(d)
    }

    override fun onNext(t: BaseResponse<T>) {
        when (t.errorCode) {
            0 -> t.data?.let { success(it) }
            -1001 -> loginFailed(t.errorMsg)
            -1 -> onError(ApiException(t.errorMsg))
        }
    }

    override fun onError(e: Throwable) {
        val errorMsg = if (e is UnknownHostException){
            "网络异常"
        }else if (e is JSONException || e is JsonParseException){
            "数据异常"
        }else if (e is ConnectException){
            "连接超时"
        }else if (e is ApiException){
            e.message?:"未知错误"
        } else {
            "未知错误"
        }
        error(errorMsg)
    }

    fun loginFailed(msg : String){
        onError(ApiException(msg))
    }

    abstract fun disposible(d : Disposable)
    abstract fun success(t: T)
    abstract fun error(errorMsg : String)
}