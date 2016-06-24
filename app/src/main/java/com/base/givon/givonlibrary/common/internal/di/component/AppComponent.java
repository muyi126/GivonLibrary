package com.base.givon.givonlibrary.common.internal.di.component;

import com.base.givon.givonlibrary.common.Navigator;
import com.base.givon.givonlibrary.common.internal.di.module.AppModule;


import javax.inject.Singleton;

import dagger.Component;

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
@Component(modules = AppModule.class)
public interface AppComponent {
    Navigator navigator();
}
