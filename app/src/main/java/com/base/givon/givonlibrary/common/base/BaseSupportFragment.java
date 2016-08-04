package com.base.givon.givonlibrary.common.base;


import com.base.givon.givonlibrary.R;
import com.base.givon.givonlibrary.common.App;
import com.base.givon.givonlibrary.common.Navigator;
import com.base.givon.givonlibrary.common.internal.di.component.ApiComponent;
import com.base.givon.givonlibrary.common.internal.di.component.AppComponent;
import com.base.givon.givonlibrary.common.widget.WaitingDialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import icepick.Icepick;
import nucleus.presenter.Presenter;
import nucleus.view.NucleusSupportFragment;

public abstract class BaseSupportFragment<PresenterType extends Presenter> extends NucleusSupportFragment<PresenterType> {
    @Nullable
    @BindView(R.id.toolbar)
    public Toolbar toolbarView;

    @Nullable
    @BindView(R.id.toolbar_title)
    public TextView toolbarTitleView;

    public Navigator navigator;
    private WaitingDialog mWaitingDialog;
    private Unbinder mUnbinder;

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Icepick.saveInstanceState(this, bundle);
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
        Icepick.restoreInstanceState(this, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);

        if (toolbarTitleView != null && !TextUtils.isEmpty(getTitle())) {
            toolbarTitleView.setText(getTitle());
        }
    }

    public void showWaitingDialog(String msg) {
        if (mWaitingDialog == null) {
            mWaitingDialog = new WaitingDialog(getActivity());
            // mWaitingDialog = new ProgressDialog(this);
            mWaitingDialog.setCanceledOnTouchOutside(false);
            // mWaitingDialog.setMessage(getString(R.string.action_waiting));
            mWaitingDialog.setCancelable(true);
        }
        mWaitingDialog.setMessage(msg);
        if (null != getActivity() && !getActivity().isFinishing() && !mWaitingDialog.isShowing()) {
            mWaitingDialog.show();
        }
    }

    public void dismissWaitingDialog() {
        if (null != mWaitingDialog && mWaitingDialog.isShowing() && null != getActivity() && !getActivity().isFinishing()) {
            mWaitingDialog.dismiss();
        }
    }

    protected String getTitle() {
        return "";
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
        dismissWaitingDialog();
        if (null != mUnbinder) {
            mUnbinder.unbind();
        }
    }
}