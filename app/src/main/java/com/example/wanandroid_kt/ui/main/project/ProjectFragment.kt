package com.example.wanandroid_kt.ui.main.project

import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.base.AppLazyFragment

class ProjectFragment : AppLazyFragment<ProjectContract.Presenter<ProjectContract.View>>(), ProjectContract.View{
    override fun lazyInit() {
    }

    override fun createPresenter(): ProjectContract.Presenter<ProjectContract.View>? {
        return ProjectPresenter(this)
    }

    override fun layoutId(): Int {
        return R.layout.fragment_project
    }

    override fun onError(error: String) {
    }

}