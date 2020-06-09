package com.example.wanandroid_kt.net

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io



object SchedulerUtil{
    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}