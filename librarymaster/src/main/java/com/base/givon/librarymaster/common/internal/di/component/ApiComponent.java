package com.base.givon.librarymaster.common.internal.di.component;


import com.base.givon.librarymaster.common.internal.di.module.ApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApiModule.class)
public abstract class ApiComponent extends AbstractApiComponent {

}