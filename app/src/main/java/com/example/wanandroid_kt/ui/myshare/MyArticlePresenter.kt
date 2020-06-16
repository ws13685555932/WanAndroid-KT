package com.zs.wanandroid.ui.my

import com.example.wanandroid_kt.base.BasePresenter
import com.example.wanandroid_kt.entity.MyArticle
import com.example.wanandroid_kt.net.ApiCallBack
import com.example.wanandroid_kt.net.RetrofitManager
import com.example.wanandroid_kt.net.SchedulerUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author zs
 * @date 2020-03-17
 */
class MyArticlePresenter(view: MyArticleContract.View) : BasePresenter<MyArticleContract.View>(view)
    , MyArticleContract.Presenter {

    override fun loadData(pageNum: Int) {
        RetrofitManager.service
            .getMyArticle(pageNum)
            .compose(SchedulerUtil.ioToMain())
            .subscribe(object : ApiCallBack<MyArticle>() {
                override fun disposible(d: Disposable) {
                    addSubscrible(d)
                }

                override fun success(t: MyArticle) {
                    view?.showList(t)
                }

                override fun error(errorMsg: String) {
                    view?.onError(errorMsg)
                }
            })
    }

    override fun delete(id: Int) {
        RetrofitManager.service
            .deleteMyArticle(id)
            .compose(SchedulerUtil.ioToMain())
            .subscribe(object : ApiCallBack<Any>() {
                override fun disposible(d: Disposable) {
                    addSubscrible(d)
                }

                override fun success(t: Any) {
                    view?.deleteSuccess()
                }

                override fun error(errorMsg: String) {
                    view?.onError(errorMsg)
                }

            })


//            .subscribe(object : ApiCallBack<Any?>() {
//                override fun disposible(d: Disposable) {
//                    addSubscrible(d)
//                }
//
//                override fun error(errorMsg: String) {
//                    view?.onError(errorMsg)
//                }
//
//                override fun success(t: Any?) {
//                    view?.deleteSuccess()
//                }
//            })
    }
}