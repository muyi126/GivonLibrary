package com.base.givon.librarymaster.common;

import com.base.givon.librarymaster.common.internal.di.component.AppComponent;
import com.base.givon.librarymaster.common.internal.di.component.DaggerAppComponent;
import com.base.givon.librarymaster.common.internal.di.module.AppModule;
import com.base.givon.librarymaster.common.internal.di.module.ThModule;
import com.base.givon.librarymaster.common.network.MyOkHttpImagePipelineConfigFactory;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.github.pwittchen.prefser.library.Prefser;
import com.squareup.leakcanary.LeakCanary;

import android.app.Application;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
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
    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

    public static App getInstance() {
        return mApp;
    }
}
