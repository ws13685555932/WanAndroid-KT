package com.zs.wanandroid.ui.main.tab

import com.example.wanandroid_kt.base.BasePresenter
import com.example.wanandroid_kt.const.Constants
import com.example.wanandroid_kt.entity.TabEntity
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
class TabPresenter(view:TabContract.View): BasePresenter<TabContract.View>(view)
    , TabContract.Presenter{

    override fun loadData(type:Int) {
        when(type){
            //项目
            Constants.PROJECT_TYPE->{
                RetrofitManager.service
                    .getProjectTabList()
                    .compose(SchedulerUtil.ioToMain())
                    .subscribe(object :ApiCallBack<MutableList<TabEntity>>(){

                        override fun disposible(d: Disposable) {
                            addSubscrible(d)
                        }

                        override fun success(t: MutableList<TabEntity>) {
                            view?.showList(t)
                        }

                        override fun error(errorMsg: String) {
                            view?.onError(errorMsg)
                        }
                    })
            }
            //公众号
            Constants.ACCOUNT_TYPE->{
                RetrofitManager.service
                    .getAccountTabList()
                    .compose(SchedulerUtil.ioToMain())
                    .subscribe(object :ApiCallBack<MutableList<TabEntity>>(){

                        override fun disposible(d: Disposable) {
                            addSubscrible(d)
                        }

                        override fun success(t: MutableList<TabEntity>) {
                            view?.showList(t)
                        }

                        override fun error(errorMsg: String) {
                            view?.onError(errorMsg)
                        }
                    })
            }
        }

    }

}