package com.base.givon.demo.common.base;

import com.base.givon.baseobj.common.base.GivBaseActivity;
import com.base.givon.demo.R;
import com.base.givon.demo.common.App;
import com.base.givon.demo.common.Navigator;
import com.base.givon.demo.common.internal.di.component.ApiComponent;
import com.base.givon.demo.common.internal.di.component.AppComponent;
import com.base.givon.demo.common.internal.di.module.ActivityModule;
import com.levelmoney.velodrome.Velodrome;

import android.accounts.AccountManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import icepick.Icepick;
import nucleus.presenter.Presenter;
import nucleus.view.NucleusAppCompatActivity;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @六月 16/6/21 下午5:32 - Guzhu
 * @email:muyi126@163.com
 */
public abstract class BaseActivity<PresenterType extends Presenter> extends GivBaseActivity<PresenterType> {
    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbarView;

    @Nullable
    @BindView(R.id.toolbar_title)
    public TextView toolbarTitleView;

    public Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        injectorPresenter();
        super.onCreate(savedInstanceState);
        navigator = getAppComponent().navigator();
    }


    protected AppComponent getAppComponent() {
        return ((App) getApplication()).getAppComponent();
    }

    protected ApiComponent getApiComponent() {
        return ((App) getApplication()).getApiComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected void injectorPresenter() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}