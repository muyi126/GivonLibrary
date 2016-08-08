package com.base.givon.demo.common.internal.di.component;


import com.base.givon.demo.common.internal.di.module.ApiModule;
import com.base.givon.demo.ui.orderList.fragment.FraOrderListPresenter;
import com.base.givon.demo.ui.presenter.RepositoriesPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApiModule.class)
public interface ApiComponent {
        void inject(RepositoriesPresenter repositoriesPresenter);

        void inject(FraOrderListPresenter fraOrderListPresenter);
}