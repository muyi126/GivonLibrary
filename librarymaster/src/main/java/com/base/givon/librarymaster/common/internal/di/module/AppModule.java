package com.base.givon.librarymaster.common.internal.di.module;

import com.base.givon.librarymaster.common.App;
import com.base.givon.librarymaster.common.internal.di.qualifier.ForApplication;
import com.github.pwittchen.prefser.library.Prefser;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @六月 16/6/20 下午11:26 - Guzhu
 * @email:muyi126@163.com
 *
 * //module 提供者
 */


@Module
public class AppModule {
    private final App mApp;

    public AppModule(App app) {
        mApp = app;
    }


    @Provides
    //
    @Singleton
    //作用空间 有效范围
    @ForApplication
    Context provideAppContext() {
        return mApp;
    }

    @Provides
    @Singleton
    Prefser providePrefser(@ForApplication Context context){
        return new Prefser(context);
    }
}
