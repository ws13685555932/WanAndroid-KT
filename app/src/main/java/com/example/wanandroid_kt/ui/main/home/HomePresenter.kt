package com.example.wanandroid_kt.ui.main.home

import com.example.wanandroid_kt.base.BasePresenter
import com.example.wanandroid_kt.entity.Article
import com.example.wanandroid_kt.entity.Banner
import com.example.wanandroid_kt.entity.Wrapper
import com.example.wanandroid_kt.net.ApiCallBack
import com.example.wanandroid_kt.net.RetrofitManager
import com.example.wanandroid_kt.net.SchedulerUtil
import io.reactivex.disposables.Disposable

class HomePresenter (view : HomeContract.View) : BasePresenter<HomeContract.View>(view),
    HomeContract.Presenter<HomeContract.View>{
    override fun getBanner() {
        RetrofitManager.service
            .getBanner()
            .compose(SchedulerUtil.ioToMain())
            .subscribe(object : ApiCallBack<MutableList<Banner>>(){
                override fun disposible(d: Disposable) {
                    addSubscrible(d)
                }

                override fun success(t: MutableList<Banner>) {
                    view?.showBanner(t)
                }

                override fun error(errorMsg: String) {
                    view?.onError(errorMsg)
                }

            })
    }

    override fun getArticles(pageNum : Int) {
        RetrofitManager.service
            .getHomeList(pageNum)
            .compose(SchedulerUtil.ioToMain())
            .subscribe(object : ApiCallBack<Wrapper<Article>>(){
                override fun disposible(d: Disposable) {
                    addSubscrible(d)
                }

                override fun success(t: Wrapper<Article>) {
                    getTopList(t.datas)
                }

                override fun error(errorMsg: String) {

                }

            })
    }

    override fun getTopList(list : MutableList<Article>) {
        RetrofitManager.service
            .getTopList()
            .compose(SchedulerUtil.ioToMain())
            .subscribe(object : ApiCallBack<MutableList<Article>>(){
                override fun disposible(d: Disposable) {
                    addSubscrible(d)
                }

                override fun success(t: MutableList<Article>) {
                    list.addAll(0, t)
                    view?.showList(list)
                }

                override fun error(errorMsg: String) {
                    view?.onError(errorMsg)
                }

            })
    }


}