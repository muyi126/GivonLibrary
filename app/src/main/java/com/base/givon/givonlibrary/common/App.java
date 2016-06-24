package com.base.givon.givonlibrary.common;

import com.base.givon.givonlibrary.common.internal.di.component.ApiComponent;
import com.base.givon.givonlibrary.common.internal.di.component.AppComponent;
import com.base.givon.givonlibrary.common.internal.di.component.DaggerApiComponent;
import com.base.givon.givonlibrary.common.internal.di.component.DaggerAppComponent;
import com.base.givon.givonlibrary.common.internal.di.module.AppModule;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.github.pwittchen.prefser.library.Prefser;
import com.squareup.leakcanary.LeakCanary;

import android.app.Application;

import javax.inject.Inject;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @六月 16/6/21 上午8:58 - Guzhu
 * @email:muyi126@163.com
 */
public class App extends Application {
    private AppComponent appComponent;

    private ApiComponent apiComponent;
    @Inject
    Prefser mPrefser;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        LeakCanary.install(this);

        initializeInjector();
        initializeInjectorApi();


    }

    private void initializeInjector() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    private void initializeInjectorApi() {
        apiComponent = DaggerApiComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public ApiComponent getApiComponent() {
        return apiComponent;
    }
}