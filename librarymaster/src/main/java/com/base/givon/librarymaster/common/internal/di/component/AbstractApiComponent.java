package com.base.givon.librarymaster.common.internal.di.component;

import com.base.givon.librarymaster.common.internal.di.module.ApiModule;

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
 * @八月 16/8/4 下午4:27 - Guzhu
 * @email:muyi126@163.com
 */
@Singleton
@Component(modules = ApiModule.class)
public abstract class AbstractApiComponent {

}
