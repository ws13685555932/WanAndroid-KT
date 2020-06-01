package com.example.wanandroid_kt.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wanandroid_kt.MyApplication
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.base.AppBaseActivity
import com.example.wanandroid_kt.ext.toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_mine.*

/**需求：
 * - password明文切换
 * - 清除功能
 * - 账号密码判断
 * - 注册界面跳转
 * - 登录时显示dialog
 */


class LoginActivity : AppBaseActivity<LoginContract.Presenter<LoginContract.View>>(), LoginContract.View {

    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun createPresenter(): LoginContract.Presenter<LoginContract.View>? {
        return LoginPresenter(this)
    }

    override fun initView() {
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            showDialog("登陆中...")
            presenter?.login(username, password)
        }
    }

    override fun loginSuccess() {
        hideDialog()
        "login success".toast()
        finish()
    }
}
