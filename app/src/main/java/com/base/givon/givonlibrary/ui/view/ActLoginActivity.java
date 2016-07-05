package com.base.givon.givonlibrary.ui.view;

import com.base.givon.givonlibrary.R;
import com.base.givon.givonlibrary.common.base.BaseActivity;
import com.base.givon.givonlibrary.common.provider.BusProvider;
import com.base.givon.givonlibrary.common.widget.ClearEditText;
import com.base.givon.givonlibrary.common.widget.PassWordEditText;
import com.base.givon.givonlibrary.module.EventObj.EventLoginResult;
import com.base.givon.givonlibrary.module.EventObj.EventType;
import com.base.givon.givonlibrary.ui.presenter.ActLoginPresenter;
import com.hwangjr.rxbus.annotation.Produce;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.Bind;
import butterknife.OnClick;
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

    @Bind(R.id.et_user_name)
    ClearEditText et_userName;
    @Bind(R.id.et_psd)
    PassWordEditText et_userPasswrd;
    @Override
    protected int getLayoutResId() {
        return R.layout.login_layout;
    }

    public  static Intent getCallingIntent(Context context){
        return new Intent(context,ActLoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void setUserName(String userName) {
        et_userName.setText(userName);
    }

    public void setUserPsd(String userPsd) {
        et_userPasswrd.setText(userPsd);
    }

    @OnClick(R.id.bt_login)
    public void onClick(View v){
        String user_name = et_userName.getText().toString().trim();
        String user_psd = et_userPasswrd.getText().toString().trim();
        getPresenter().onSaveUserLoginInfo(user_name,user_psd);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
