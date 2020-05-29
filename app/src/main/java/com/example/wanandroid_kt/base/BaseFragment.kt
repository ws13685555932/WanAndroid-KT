package com.example.wanandroid_kt.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment<P : IBasePresenter<*>> : Fragment() {

    protected val TAG = javaClass.name
    protected var presenter : P? = null
    private var contentView : View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
        presenter?.let {
            lifecycle.addObserver(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        contentView = inflater.inflate(layoutId(), null)
        return contentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    protected abstract fun createPresenter() :P?
    protected abstract fun layoutId() : Int
    protected abstract fun initView()
}