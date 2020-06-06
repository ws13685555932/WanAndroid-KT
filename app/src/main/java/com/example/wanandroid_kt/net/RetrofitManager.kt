package com.example.wanandroid_kt.net

import com.example.wanandroid_kt.const.UrlConst
import com.franmontiel.persistentcookiejar.ClearableCookieJar
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager{
    val service : ApiService by lazy(LazyThreadSafetyMode.SYNCHRONIZED){
        getRetrofit().create(ApiService::class.java)
    }

    private fun getRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(UrlConst.BASE_URL)
            .client(OKHttpClient.getOkHttpClientBuilder().build())
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