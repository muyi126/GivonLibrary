package com.base.givon.librarymaster.common.internal.di.module;

import com.base.givon.librarymaster.common.internal.di.scope.PerActivity;

import android.app.Activity;


import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity provideActivity() {
        return activity;
    }
}