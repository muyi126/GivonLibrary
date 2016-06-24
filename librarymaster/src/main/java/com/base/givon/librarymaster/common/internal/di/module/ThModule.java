package com.base.givon.librarymaster.common.internal.di.module;

import com.base.givon.librarymaster.common.App;
import com.base.givon.librarymaster.common.internal.di.qualifier.ForApplication;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 *
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @六月 16/6/21 上午12:19 - Guzhu
 * @email:muyi126@163.com
 */
@Module
public class ThModule {
    private final App mApp;

    public ThModule(App app) {
        mApp = app;
    }

    @Provides
    //
    @Singleton
    //作用空间 有效范围
    @ForApplication
    Context provideApContext() {
        return mApp;
    }
}
