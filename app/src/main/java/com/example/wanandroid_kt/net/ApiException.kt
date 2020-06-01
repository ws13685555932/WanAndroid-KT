package com.example.wanandroid_kt.net

import io.reactivex.internal.util.AtomicThrowable
import java.lang.RuntimeException

class ApiException : RuntimeException{

    private var code : Int? = null

    constructor(throwable: Throwable, code : Int) : super(throwable) {
        this.code = code
    }

    constructor(message : String) : super(Throwable(message))
}