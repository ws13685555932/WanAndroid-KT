package com.example.wanandroid_kt.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.text.method.TransformationMethod
import android.widget.ImageView
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.base.AppBaseActivity
import com.example.wanandroid_kt.ext.clearText
import com.example.wanandroid_kt.ext.toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppBaseActivity<RegisterContract.Presenter>(), RegisterContract.View {
    private var isPasswordShow = false

    override fun initView() {
        ivBack.setOnClickListener { finish() }

        btnRegister.setOnClickListener {
            // TODO: close the keyboard
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

        ivClear.setOnClickListener {
            etUsername.requestFocus()
            etUsername.clearText()
        }

        ivHidePassword.setOnClickListener {
            etPassword.requestFocus()
            etPassword.transformationMethod = if (isPasswordShow){
                showPassword(ivHidePassword)
            }else{
                hidePassword(ivHidePassword)
            }
            etPassword.setSelection(etPassword.text.length)
        }

        ivHidePasswordConfirm.setOnClickListener {
            etPasswordConfirm.requestFocus()
            etPasswordConfirm.transformationMethod = if (isPasswordShow){
                showPassword(ivHidePasswordConfirm)
            }else{
                hidePassword(ivHidePasswordConfirm)
            }
            etPasswordConfirm.setSelection(etPasswordConfirm.text.length)
        }

    }

    fun showPassword(view : ImageView) : TransformationMethod{
        isPasswordShow = false
        view.setImageResource(R.mipmap.password_show)
        return PasswordTransformationMethod.getInstance()
    }

    fun hidePassword(view :ImageView) : TransformationMethod{
        isPasswordShow = true
        view.setImageResource(R.mipmap.password_hide)
        return HideReturnsTransformationMethod.getInstance()
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
