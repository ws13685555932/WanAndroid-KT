package com.zs.wanandroid.ui.collect

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
 * @date 2020-03-13
 */
class CollectPresenter(view: CollectContract.View) : BasePresenter<CollectContract.View>(view)
    , CollectContract.Presenter {


    override fun loadData(page: Int) {
        RetrofitManager.service
            .getCollectData(page)
            .compose(SchedulerUtil.ioToMain())
            .subscribe(object : ApiCallBack<Wrapper<Article>>() {

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

    override fun cancelCollect(id: Int) {
        RetrofitManager.service
            .unCollect(id)
            .compose(SchedulerUtil.ioToMain())
            .subscribe(object : ApiCallBack<Any>() {

                override fun disposible(d: Disposable) {
                    addSubscrible(d)
                }

                override fun success(t: Any) {
                    view?.cancelCollectSuccess()
                }

                override fun error(errorMsg: String) {
                    view?.onError(errorMsg)
                }
            })
    }
}