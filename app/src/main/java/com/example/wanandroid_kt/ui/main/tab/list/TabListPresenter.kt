package com.zs.wanandroid.ui.main.tab.list

import com.example.wanandroid_kt.base.BasePresenter
import com.example.wanandroid_kt.const.Constants
import com.example.wanandroid_kt.entity.Article
import com.example.wanandroid_kt.entity.Wrapper
import com.example.wanandroid_kt.ext.log
import com.example.wanandroid_kt.net.ApiCallBack
import com.example.wanandroid_kt.net.RetrofitManager
import com.example.wanandroid_kt.net.SchedulerUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * @author zs
 * @date 2020-03-14
 */
class TabListPresenter(view: TabListContract.View): BasePresenter<TabListContract.View>(view)
    ,TabListContract.Presenter{

    override fun loadData(type:Int,id: Int, pageNum: Int) {
        when(type){
            Constants.PROJECT_TYPE->{
                RetrofitManager.service
                    .getProjectList(pageNum,id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
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
            Constants.ACCOUNT_TYPE->{
                RetrofitManager.service
                    .getAccountList(id,pageNum)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
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
        }

    }

    override fun collect(id: Int) {
        RetrofitManager.service
            .collect(id)
            .compose(SchedulerUtil.ioToMain())
            .subscribe(object : ApiCallBack<Any>(){
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
            .subscribe(object : ApiCallBack<Any>(){
                override fun disposible(d: Disposable) {
                    "disposible".log()
                    addSubscrible(d)
                }

                override fun success(t: Any) {
                    "collect sucess presenter".log()
                    view?.unCollectSuccess()
                }

                override fun error(errorMsg: String) {
                    "onerror".log()
                    view?.onError(errorMsg)
                }

            })
    }

}