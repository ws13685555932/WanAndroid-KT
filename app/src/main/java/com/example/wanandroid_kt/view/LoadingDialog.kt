package com.example.wanandroid_kt.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.wanandroid_kt.R
import kotlinx.android.synthetic.main.dialog_loading.*

class LoadingDialog : DialogFragment(){
    private val TAG = "LoadingDialog"
    private var messageResId : Int? = null

    companion object{
        fun newInstance() = LoadingDialog()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_loading, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvMessage.text = getString(messageResId ?: R.string.loading)
//        tvMessage.text = message?:"加载中"
    }

    fun show(
        fragmentManager: FragmentManager,
        @StringRes messageResId: Int,
        isCancelable:Boolean = false
    ){
        this.messageResId = messageResId
        this.isCancelable = isCancelable
        show(fragmentManager, TAG)
    }


}