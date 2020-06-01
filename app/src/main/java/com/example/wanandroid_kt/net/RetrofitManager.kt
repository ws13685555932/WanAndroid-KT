package com.example.wanandroid_kt.net

import android.util.TimeUtils
import com.example.wanandroid_kt.MyApplication
import com.example.wanandroid_kt.const.UrlConst
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object RetrofitManager{
    val service : ApiService by lazy(LazyThreadSafetyMode.SYNCHRONIZED){
        getRetrofit().create(ApiService::class.java)
    }

    private fun getRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(UrlConst.BASE_URL)
            .client(getOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

//    private fun addQueryParameterInterceptor() : Interceptor{
//        return Interceptor{chain ->
//            val originRequest = chain.request()
//            val request : Request
//            val modifiedUrl = originRequest.url().newBuilder()
//                .addQueryParameter("udid", "")
//                .addQueryParameter("deviceModel","apputils.getmobilemodel")
//                .build()
//            request = originRequest.newBuilder().url(modifiedUrl).build()
//            chain.proceed()
//        }
//    }



    private fun getOkHttpClient() : OkHttpClient{
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

//        val cacheFile = File(MyApplication.context.cacheDir, "cache")
//        val cache = Cache(cacheFile, 1024 * 1024 * 50)

        return OkHttpClient.Builder()
//            .addInterceptor(httpLoggingInterceptor)
//            .cache(cache)
            .connectTimeout(60L , TimeUnit.SECONDS)
            .readTimeout(60L ,TimeUnit.SECONDS)
            .writeTimeout(60L , TimeUnit.SECONDS)
            .build()
    }
}