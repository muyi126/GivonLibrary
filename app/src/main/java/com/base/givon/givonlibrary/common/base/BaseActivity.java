package com.base.givon.givonlibrary.common.base;

import com.base.givon.givonlibrary.R;
import com.base.givon.givonlibrary.common.App;
import com.base.givon.givonlibrary.common.Navigator;
import com.base.givon.givonlibrary.common.internal.di.component.ApiComponent;
import com.base.givon.givonlibrary.common.internal.di.component.AppComponent;
import com.base.givon.givonlibrary.common.internal.di.module.ActivityModule;
import com.github.pwittchen.prefser.library.Prefser;
import com.levelmoney.velodrome.Velodrome;

import android.accounts.Account;
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

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import icepick.Icepick;
import nucleus.presenter.Presenter;
import nucleus.view.NucleusAppCompatActivity;

/**
 *
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @六月 16/6/21 下午5:32 - Guzhu
 * @email:muyi126@163.com
 */
public abstract class BaseActivity<PresenterType extends Presenter> extends NucleusAppCompatActivity<PresenterType> {
    @Nullable
    @Bind(R.id.toolbar)
    Toolbar toolbarView;

    @Nullable
    @Bind(R.id.toolbar_title)
    public TextView toolbarTitleView;

    public Navigator navigator;

    @Inject
    AccountManager accountManager;

    String accountType, tokenType;

    Account[] accounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        injectorPresenter();
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);

        if(getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        setContentView(getLayoutResId());
        initializeToolbar();
        navigator = getAppComponent().navigator();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (!isChild()) {
            onTitleChanged(getTitleName(), getTitleColor());
        }
    }


    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        if (toolbarTitleView == null) {
            return;
        }
        toolbarTitleView.setText(title);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }

    protected void initializeToolbar() {
        if (toolbarView == null) {
            return;
        }
        setSupportActionBar(toolbarView);
        if (toolbarTitleView != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        if (!TextUtils.isEmpty(NavUtils.getParentActivityName(this))) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_back);
            toolbarView.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

//    protected Map<String, String> getHttpHeaderAuth() {
//        Prefser prefser = new Prefser(this);
//        String token = prefser.get(GUEST_TOKEN_KEY, String.class, "");
//
//        Map<String, String> map = new HashMap<>();
//        map.put("Authorization", "Bearer " + token);
//
//        return map;
//    }
//
//    protected boolean isLogin() {
//        accountManager = AccountManager.get(this);
//        accountType = getString(R.string.auth_account_type);
//        tokenType = getString(R.string.auth_token_type);
//        accounts = accountManager.getAccountsByType(accountType);
//
//        if (accounts.length > 0) {
//            return true;
//        }
//
//        return false;
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Velodrome.handleResult(this, requestCode, resultCode, data);
    }

    protected CharSequence getTitleName() {
        return getTitle();
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

    protected void injectorPresenter() {}

    abstract protected @LayoutRes
    int getLayoutResId();
}