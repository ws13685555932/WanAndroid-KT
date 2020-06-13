package com.example.wanandroid_kt.net;

import com.example.wanandroid_kt.MyApplication;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class OKHttpClient {

    public static OkHttpClient.Builder getOkHttpClientBuilder() {

        File cacheFile = new File(MyApplication.mContext.getCacheDir(), "cache");
        //100Mb
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);

        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(),
                        new SharedPrefsCookiePersistor(MyApplication.mContext));
        return new OkHttpClient.Builder()
                .readTimeout(60L, TimeUnit.SECONDS)
                .connectTimeout(60L, TimeUnit.SECONDS)
//                .addInterceptor(new AddCookiesInterceptor())
//                .addInterceptor(new SaveCookiesInterceptor())
                .cookieJar(cookieJar)
                .cache(cache);
    }
}
