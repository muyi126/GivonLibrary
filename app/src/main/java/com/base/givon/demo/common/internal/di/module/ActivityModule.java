package com.base.givon.demo.common.internal.di.module;

import android.app.Activity;

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
 * @六月 16/6/21 下午5:39 - Guzhu
 * @email:muyi126@163.com
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @com.base.givon.demo.common.internal.di.scope.PerActivity
    Activity provideActivity() {
        return activity;
    }
}
