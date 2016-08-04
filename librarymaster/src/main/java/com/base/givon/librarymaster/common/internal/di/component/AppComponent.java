package com.base.givon.librarymaster.common.internal.di.component;

import com.base.givon.librarymaster.common.App;
import com.base.givon.librarymaster.common.Navigator;
import com.base.givon.librarymaster.common.internal.di.module.AppModule;
import com.base.givon.librarymaster.common.net.RetrofitUtils;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;


/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @六月 16/6/20 下午10:57 - Guzhu
 * @email:muyi126@163.com
 */
@Singleton
@Component(modules = {AppModule.class, RetrofitUtils.class})
public abstract class AppComponent {
    abstract Navigator navigator();

    abstract void inject(Retrofit retrofit);

    abstract void inject(App app);
}
