package com.example.wanandroid_kt.ui.login

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.base.AppBaseActivity
import com.example.wanandroid_kt.const.SaveConstants
import com.example.wanandroid_kt.entity.UserEntity
import com.example.wanandroid_kt.ext.toast
import com.example.wanandroid_kt.ui.register.RegisterActivity
import com.example.wanandroid_kt.utils.SharedPrefUtil
import kotlinx.android.synthetic.main.activity_login.*

/**需求：
 * - password明文切换
 * - 清除功能
 * - 账号密码判断
 * - 注册界面跳转
 * - 登录时显示dialog
 */

class LoginActivity : AppBaseActivity<LoginContract.Presenter<LoginContract.View>>(), LoginContract.View {

    private var isPasswordShow = false

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
            when{
                username.isEmpty() -> R.string.please_input_username.toast()
                password.isEmpty() -> R.string.please_input_password.toast()
                else -> {
                    showLoadingDialog(R.string.logining)
                    presenter?.login(username, password)
                }
            }
        }

        tvRegister.setOnClickListener {
            goto(RegisterActivity::class.java, false)
        }

        ivBack.setOnClickListener { finish() }

        ivClear.setOnClickListener {
            etUsername.requestFocus()
            etUsername.setText("")
        }

        ivHidePassword.setOnClickListener {
            etPassword.apply {
                requestFocus()
                transformationMethod = if (isPasswordShow){
                    isPasswordShow = false
                    ivHidePassword.setImageResource(R.mipmap.password_show)
                    PasswordTransformationMethod.getInstance()
                }else{
                    isPasswordShow = true
                    ivHidePassword.setImageResource(R.mipmap.password_hide)
                    HideReturnsTransformationMethod.getInstance()
                }
                setSelection(etPassword.text.length)
            }
        }

    }

    override fun loginSuccess(t: UserEntity) {
        hideLoadingDialog()
        SharedPrefUtil.saveValue(SaveConstants.IS_LOGIN, true)
        R.string.login_success.toast()
        finish()
    }
}
