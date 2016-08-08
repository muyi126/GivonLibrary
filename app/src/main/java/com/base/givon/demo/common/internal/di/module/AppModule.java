package com.base.givon.demo.common.internal.di.module;

import com.github.pwittchen.prefser.library.Prefser;

import android.accounts.AccountManager;
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
 * @六月 16/6/21 下午4:48 - Guzhu
 * @email:muyi126@163.com
 */
@Module
public class AppModule {
    private final com.base.givon.demo.common.App app;

    public AppModule(com.base.givon.demo.common.App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    @com.base.givon.demo.common.internal.di.qualifier.ForApplication
    Context provideAppContext() {
        return app;
    }

    @Provides
    @Singleton
    Prefser providePrefser(@com.base.givon.demo.common.internal.di.qualifier.ForApplication Context context) {
        return new Prefser(context);
    }

    @Provides
    @Singleton
    AccountManager provideAccountManager(@com.base.givon.demo.common.internal.di.qualifier.ForApplication Context context) {
        return AccountManager.get(context);
    }


//    @Provides
//    @Singleton
//    AuthAccountManager provideAuthAccountManager(@ForApplication Context context) {
//        return new AuthAccountManager(context);
//    }
}
