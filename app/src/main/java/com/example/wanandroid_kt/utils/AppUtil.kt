package com.example.wanandroid_kt.utils

import com.example.wanandroid_kt.const.Const
import com.example.wanandroid_kt.entity.LogoutEvent
import org.greenrobot.eventbus.EventBus

object AppUtil {
    fun isLogin() : Boolean{
        return PrefUtil.getValue(Const.IS_LOGIN, false) as Boolean
    }

    fun resetUser() {
        EventBus.getDefault().post(LogoutEvent())
        PrefUtil.removeKey(Const.USER_INFO)
        PrefUtil.saveValue(Const.IS_LOGIN, false)
    }
}