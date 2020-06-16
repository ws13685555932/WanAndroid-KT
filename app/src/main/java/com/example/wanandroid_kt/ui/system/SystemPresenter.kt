package com.example.wanandroid_kt.ui.system

import com.example.wanandroid_kt.base.BasePresenter
import com.example.wanandroid_kt.entity.Article
import com.example.wanandroid_kt.entity.Wrapper
import com.example.wanandroid_kt.ext.log
import com.example.wanandroid_kt.net.ApiCallBack
import com.example.wanandroid_kt.net.RetrofitManager
import com.example.wanandroid_kt.net.SchedulerUtil
import io.reactivex.disposables.Disposable

class SystemPresenter(view : SystemContract.View) : BasePresenter<SystemContract.View>(view),
        SystemContract.Presenter{

    override fun getArticleListOfTag(pageNum : Int, cid : Int){
        RetrofitManager.service
            .getSystemArticle(pageNum, cid)
            .compose(SchedulerUtil.ioToMain())
            .subscribe(object : ApiCallBack<Wrapper<Article>>(){
                override fun disposible(d: Disposable) {
                    addSubscrible(d)
                }

                override fun success(t: Wrapper<Article>) {
                    view?.showList(t)
                }

                override fun error(errorMsg: String) {
                    view?.onError(errorMsg)
                }

            })
    }

    override fun uncollect(id : Int){
        RetrofitManager.service
            .unCollect(id)
            .compose(SchedulerUtil.ioToMain())
            .subscribe(object : ApiCallBack<Any>(){
                override fun disposible(d: Disposable) {
                    addSubscrible(d)
                }

                override fun success(t: Any) {
                    view?.unCollecSuccess()
                }

                override fun error(errorMsg: String) {
                    view?.onError(errorMsg)
                }

            })
    }

    override fun collect(id : Int){
        RetrofitManager.service
            .collect(id)
            .compose(SchedulerUtil.ioToMain())
            .subscribe(object : ApiCallBack<Any>(){
                override fun disposible(d: Disposable) {
                    "disposible".log()
                    addSubscrible(d)
                }

                override fun success(t: Any) {
                    "collect sucess presenter".log()
                    view?.collectSucess()
                }

                override fun error(errorMsg: String) {
                    "onerror".log()
                    view?.onError(errorMsg)
                }

            })
    }
}