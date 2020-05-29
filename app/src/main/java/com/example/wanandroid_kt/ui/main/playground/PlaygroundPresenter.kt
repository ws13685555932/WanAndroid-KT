package com.example.wanandroid_kt.ui.main.playground

import com.example.wanandroid_kt.base.BasePresenter
import com.example.wanandroid_kt.ui.main.mine.MineContract


class PlaygroundPresenter (view : PlaygroundContract.View) : BasePresenter<PlaygroundContract.View>(view),
    PlaygroundContract.Presenter<PlaygroundContract.View>{

}