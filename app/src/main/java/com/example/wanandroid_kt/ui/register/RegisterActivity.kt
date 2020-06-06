package com.example.wanandroid_kt.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.base.AppBaseActivity
import com.example.wanandroid_kt.ext.toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppBaseActivity<RegisterContract.Presenter>(), RegisterContract.View {


    override fun initView() {
        ivBack.setOnClickListener { finish() }

        btnRegister.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etPasswordConfirm.text.toString()

            when{
                username.isEmpty() -> R.string.please_input_username.toast()
                password.isEmpty() -> R.string.please_input_password.toast()
                confirmPassword.isEmpty() -> R.string.please_input_confirm_password.toast()
                password != confirmPassword -> R.string.password_not_same.toast()
                else -> {
                    showLoadingDialog(R.string.registering)
                    presenter?.register(username, password, confirmPassword)
                }
            }
        }

    }

    override fun layoutId(): Int {
        return R.layout.activity_register
    }

    override fun createPresenter(): RegisterContract.Presenter? {
        return RegisterPresenter(this)
    }

    override fun registerSuccess() {
        hideLoadingDialog()
        R.string.register_success.toast()
        finish()
    }
}
