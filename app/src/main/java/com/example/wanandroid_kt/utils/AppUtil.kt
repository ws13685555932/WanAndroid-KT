package com.example.wanandroid_kt.utils

import com.example.wanandroid_kt.const.SaveConstants
import com.example.wanandroid_kt.entity.LogoutEvent
import org.greenrobot.eventbus.EventBus

object AppUtil {
    fun isLogin() : Boolean{
        return SharedPrefUtil.getValue(SaveConstants.IS_LOGIN, false) as Boolean
    }

    fun resetUser() {
        EventBus.getDefault().post(LogoutEvent())
        SharedPrefUtil.removeKey(SaveConstants.USER_INFO)
        SharedPrefUtil.saveValue(SaveConstants.IS_LOGIN, false)
    }
}