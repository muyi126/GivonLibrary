package com.base.givon.librarymaster.common;

import com.base.givon.librarymaster.common.internal.di.component.ApiComponent;
import com.base.givon.librarymaster.common.internal.di.component.AppComponent;
import com.base.givon.librarymaster.common.internal.di.module.AppModule;
import com.base.givon.librarymaster.common.net.RetrofitUtils;
import com.github.pwittchen.prefser.library.Prefser;

import android.app.Application;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @六月 16/6/20 下午12:57 - Guzhu
 * @email:muyi126@163.com fixMe
 * 1.初始化fresco的网络加载库
 */
public class App extends Application {
    public static App mApp;
    private AppComponent appComponent;
    private ApiComponent apiComponent;
    //    private User mUser;
    //初始化了Okhttp
    @Inject
    Retrofit mRetrofit;
    @Inject
    Prefser mPrefser;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        initializeInjector();
        initializeInjectorApi();
    }

    public static App getInstance() {
        return mApp;
    }

    private void initializeInjector() {
//        appComponent = DaggerAppComponent.builder()
//                .appModule(new AppModule(this))
//                .retrofitUtils(new RetrofitUtils())
//                .build();
//        getAppComponent().inject(this);
    }

    private void initializeInjectorApi() {
//        apiComponent = DaggerApiComponent.builder()
//                .appModule(new AppModule(this))
//                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public ApiComponent getApiComponent() {
        return apiComponent;
    }
}
