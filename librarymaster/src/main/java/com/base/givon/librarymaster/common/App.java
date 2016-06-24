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

/**
 *
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @六月 16/6/20 下午12:57 - Guzhu
 * @email:muyi126@163.com
 *
 * fixMe
 * 1.初始化fresco的网络加载库
 */
public class App extends Application{
    private AppComponent mAppComponent;

    //Inject自动初始化
    @Inject
    Prefser mPrefser;

    @Override
    public void onCreate() {
        super.onCreate();
        //图片加载初始化
        Fresco.initialize(this);
        //内存分析初始化
        LeakCanary.install(this);
        MyOkHttpImagePipelineConfigFactory.newBuilder(this,new OkHttpClient());
        initializeInjector();
    }

    private void initializeInjector(){
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .thModule(new ThModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }


}
