package com.example.wanandroid_kt.ui.web

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.webkit.*
import com.example.wanandroid_kt.R
import com.example.wanandroid_kt.base.AppBaseActivity
import com.example.wanandroid_kt.base.BaseActivity
import com.example.wanandroid_kt.const.Constants
import kotlinx.android.synthetic.main.activity_web.*
import kotlinx.android.synthetic.main.include_toolbar.*

class WebActivity : AppBaseActivity<WebContract.Presenter>(), WebContract.View {

    private var loadUrl: String? = null
    private var title: String? = null

    override fun initView() {
        ivBack.setOnClickListener {
            finish()
        }
        tvTitleTool.text = Html.fromHtml(title)
        initWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        //自适应屏幕
        webView.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        webView.settings.loadWithOverviewMode = true

        //如果不设置WebViewClient，请求会跳转系统浏览器
        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址）
                //均交给webView自己处理，这也是此方法的默认处理
                return false
            }

            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址）
                //均交给webView自己处理，这也是此方法的默认处理

                return false
            }
        }

        webView?.loadUrl(loadUrl)
        //webView加载成功回调
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress==100)loadingTip.dismiss()
            }
        }
    }

    override fun initData() {
        val bundle: Bundle? = intent.extras
        loadUrl = bundle?.getString(Constants.WEB_URL)
        title = bundle?.getString(Constants.WEB_TITLE)
    }

    override fun layoutId(): Int {
        return R.layout.activity_web
    }

    override fun createPresenter(): WebContract.Presenter? {
        return WebPresenter(this)
    }

}
