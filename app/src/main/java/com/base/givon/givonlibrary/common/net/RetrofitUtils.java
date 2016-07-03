package com.base.givon.givonlibrary.common.net;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.model.HttpHeaders;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @六月 16/6/30 下午8:57 - Guzhu
 * @email:muyi126@163.com
 */
@Module
public class RetrofitUtils {
    //服务器路径
    private static final String API_SERVER = "https://api.github.com/";

    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;

    /**
     * 获取Retrofit对象
     *
     * @return
     */
    @Singleton
    @Provides
    protected Retrofit getRetrofit() {

        if (null == mRetrofit) {

            if (null == mOkHttpClient) {
                OkHttpUtils okHttpUtils =OkHttpUtils.getInstance();
                okHttpUtils.debug("Givon");
                //此处添加header等公共参数
                mOkHttpClient = okHttpUtils.getOkHttpClient();
            }
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(API_SERVER)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();

        }

        return mRetrofit;
    }

}
