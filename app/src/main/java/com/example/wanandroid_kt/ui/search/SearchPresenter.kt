package com.zs.wanandroid.ui.search

import com.example.wanandroid_kt.base.BasePresenter
import com.example.wanandroid_kt.entity.Article
import com.example.wanandroid_kt.entity.Wrapper
import com.example.wanandroid_kt.net.ApiCallBack
import com.example.wanandroid_kt.net.RetrofitManager
import com.example.wanandroid_kt.net.SchedulerUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author zs
 * @date 2020-03-15
 */
class SearchPresenter(view: SearchContract.View):
    BasePresenter<SearchContract.View>(view) ,
    SearchContract.Presenter {


    override fun search(pageNum: Int, key: String) {
        RetrofitManager.service
            .search(pageNum,key)
            .compose(SchedulerUtil.ioToMain())
            .subscribe(object : ApiCallBack<Wrapper<Article>>(){
                override fun disposible(d: Disposable) {
                    addSubscrible(d)
                }

                override fun success(t: Wrapper<Article>) {
                    t.datas.let { view?.showList(it) }
                }

                override fun error(errorMsg: String) {
                    view?.onError(errorMsg)
                }

            })
    }

    override fun collect(id: Int) {
        RetrofitManager.service
            .collect(id)
            .compose(SchedulerUtil.ioToMain())
            .subscribe(object : ApiCallBack<Any>() {

                override fun disposible(d: Disposable) {
                    addSubscrible(d)
                }

                override fun success(t: Any) {
                    view?.collectSuccess()
                }

                override fun error(errorMsg: String) {
                    view?.onError(errorMsg)
                }
            })
    }

    override fun unCollect(id: Int) {
        RetrofitManager.service
            .unCollect(id)
            .compose(SchedulerUtil.ioToMain())
            .subscribe(object : ApiCallBack<Any>() {

                override fun disposible(d: Disposable) {
                    addSubscrible(d)
                }

                override fun success(t: Any) {
                    view?.unCollectSuccess()
                }

                override fun error(errorMsg: String) {
                    view?.onError(errorMsg)
                }
            })
    }


}