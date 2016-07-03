package com.base.givon.givonlibrary.common.internal.di.component;

import com.base.givon.givonlibrary.common.App;
import com.base.givon.givonlibrary.common.Navigator;
import com.base.givon.givonlibrary.common.internal.di.module.AppModule;
import com.base.givon.givonlibrary.common.net.RetrofitUtils;
import com.base.givon.givonlibrary.ui.view.RepositoriesFragment;


import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

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
@Singleton
@Component(modules = {AppModule.class,RetrofitUtils.class})
public interface AppComponent {
    Navigator navigator();
    void inject(Retrofit retrofit);
    void inject(RepositoriesFragment repositoriesFragment);
    void inject(App app);
}
