package com.base.givon.givonlibrary.common.internal.di.module;

import com.base.givon.givonlibrary.common.App;
import com.base.givon.givonlibrary.common.internal.di.qualifier.ForApplication;
import com.github.pwittchen.prefser.library.Prefser;
import com.lzy.okhttputils.OkHttpUtils;

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
    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    @ForApplication
    Context provideAppContext() {
        return app;
    }

    @Provides
    @Singleton
    Prefser providePrefser(@ForApplication Context context) {
        return new Prefser(context);
    }

    @Provides
    @Singleton
    AccountManager provideAccountManager(@ForApplication Context context) {
        return AccountManager.get(context);
    }


//    @Provides
//    @Singleton
//    AuthAccountManager provideAuthAccountManager(@ForApplication Context context) {
//        return new AuthAccountManager(context);
//    }
}
