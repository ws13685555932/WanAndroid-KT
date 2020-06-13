package com.example.wanandroid_kt.utils

import com.example.wanandroid_kt.const.Constants
import com.example.wanandroid_kt.entity.LogoutEvent
import org.greenrobot.eventbus.EventBus

object AppUtil {
    fun isLogin() : Boolean{
        return SharedPrefUtil.getValue(Constants.IS_LOGIN, false) as Boolean
    }

    fun resetUser() {
        EventBus.getDefault().post(LogoutEvent())
        SharedPrefUtil.removeKey(Constants.USER_INFO)
        SharedPrefUtil.saveValue(Constants.IS_LOGIN, false)
    }
}