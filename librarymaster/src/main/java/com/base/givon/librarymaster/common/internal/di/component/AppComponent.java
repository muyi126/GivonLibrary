package com.base.givon.librarymaster.common.internal.di.component;

import com.base.givon.librarymaster.common.Navigator;
import com.base.givon.librarymaster.common.internal.di.module.AppModule;
import com.base.givon.librarymaster.common.internal.di.module.ThModule;

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
 * @六月 16/6/20 下午10:57 - Guzhu
 * @email:muyi126@163.com
 */
@Singleton
@Component(modules = {AppModule.class , ThModule.class})
public interface AppComponent {
    Navigator navigator();
}
