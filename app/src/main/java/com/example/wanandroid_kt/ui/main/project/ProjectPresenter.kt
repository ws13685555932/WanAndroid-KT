package com.example.wanandroid_kt.ui.main.project

import com.example.wanandroid_kt.base.BasePresenter
import com.example.wanandroid_kt.ui.main.playground.PlaygroundContract

class ProjectPresenter (view : ProjectContract.View) : BasePresenter<ProjectContract.View>(view),
    ProjectContract.Presenter<ProjectContract.View>{

}