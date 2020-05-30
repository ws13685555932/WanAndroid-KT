package com.example.wanandroid_kt.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.base.AppBaseActivity

class LoginActivity : AppBaseActivity<LoginContract.Presenter<LoginContract.View>>(), LoginContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun createPresenter(): LoginContract.Presenter<LoginContract.View>? {
        return LoginPresenter(this)
    }
}
