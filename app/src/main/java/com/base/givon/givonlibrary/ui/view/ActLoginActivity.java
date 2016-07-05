package com.base.givon.givonlibrary.ui.view;

import com.base.givon.givonlibrary.R;
import com.base.givon.givonlibrary.common.base.BaseActivity;
import com.base.givon.givonlibrary.ui.presenter.ActLoginPresenter;

import android.content.Context;
import android.content.Intent;

import nucleus.factory.RequiresPresenter;

/**
 *
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/4 下午9:36 - Guzhu
 * @email:muyi126@163.com
 */
@RequiresPresenter(ActLoginPresenter.class)
public class ActLoginActivity extends BaseActivity<ActLoginPresenter>{
    @Override
    protected int getLayoutResId() {
        return R.layout.login_layout;
    }

    public  static Intent getCallingIntent(Context context){
        return new Intent(context,ActLoginActivity.class);
    }
}
