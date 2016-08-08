package com.base.givon.demo.common.base;


import com.base.givon.baseobj.R;
import com.base.givon.baseobj.common.base.GivBaseSupportFragment;
import com.base.givon.demo.common.App;
import com.base.givon.demo.common.Navigator;
import com.base.givon.demo.common.internal.di.component.ApiComponent;
import com.base.givon.demo.common.internal.di.component.AppComponent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import nucleus.presenter.Presenter;

public abstract class BaseSupportFragment<PresenterType extends Presenter> extends GivBaseSupportFragment<PresenterType> {
    @Nullable
    @BindView(R.id.toolbar)
    public Toolbar toolbarView;

    @Nullable
    @BindView(R.id.toolbar_title)
    public TextView toolbarTitleView;

    public Navigator navigator;

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override
    public void onCreate(Bundle bundle) {
        injectorPresenter();
        super.onCreate(bundle);
        navigator = getAppComponent().navigator();
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    protected AppComponent getAppComponent() {
        return ((App) getActivity().getApplication()).getAppComponent();
    }

    protected ApiComponent getApiComponent() {
        return ((App) getActivity().getApplication()).getApiComponent();
    }

    protected void injectorPresenter() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}