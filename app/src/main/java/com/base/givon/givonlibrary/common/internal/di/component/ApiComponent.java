package com.base.givon.givonlibrary.common.internal.di.component;


import com.base.givon.givonlibrary.common.internal.di.module.ApiModule;
import com.base.givon.givonlibrary.ui.orderList.fragment.FraOrderListPresenter;
import com.base.givon.givonlibrary.ui.presenter.RepositoriesPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApiModule.class)
public interface ApiComponent {
        void inject(RepositoriesPresenter repositoriesPresenter);

        void inject(FraOrderListPresenter fraOrderListPresenter);
}