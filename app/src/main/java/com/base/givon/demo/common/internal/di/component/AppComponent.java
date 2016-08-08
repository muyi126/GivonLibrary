package com.base.givon.demo.common.internal.di.component;

import com.base.givon.demo.ui.orderList.fragment.OrderListFragment;
import com.base.givon.demo.ui.view.RepositoriesFragment;


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
@Component(modules = {com.base.givon.demo.common.internal.di.module.AppModule.class, com.base.givon.demo.common.net.RetrofitUtils.class})
public interface AppComponent {
    com.base.givon.demo.common.Navigator navigator();
    void inject(Retrofit retrofit);
    void inject(RepositoriesFragment repositoriesFragment);
    void inject(com.base.givon.demo.common.App app);
    void inject(OrderListFragment orderListFragment);
}
