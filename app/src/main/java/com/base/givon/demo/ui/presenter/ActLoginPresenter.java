package com.base.givon.demo.ui.presenter;

import com.base.givon.baseobj.common.utils.StringUtil;
import com.base.givon.demo.common.App;
import com.base.givon.demo.common.base.BaseRxPresenter;
import com.base.givon.demo.common.provider.BusProvider;
import com.base.givon.demo.module.EventObj.EventLoginResult;
import com.base.givon.demo.ui.view.ActLoginActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import static com.base.givon.demo.module.EventObj.EventLoginResult.USER_LOGIN_EVENT_TAG;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/4 下午9:42 - Guzhu
 * @email:muyi126@163.com
 */
public class ActLoginPresenter extends BaseRxPresenter<ActLoginActivity> {
    public final String USER_NAME_KEY = "user_name";
    public final String USER_PSD_KEY = "user_psd";

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

    }

    @Override
    protected void onTakeView(ActLoginActivity actLoginActivity) {
        super.onTakeView(actLoginActivity);
        String user_name = App.getInstance().getPrefser().get(USER_NAME_KEY, String.class, "");
        if (!StringUtil.isEmpty(user_name)) {
            getView().setUserName(user_name);
        }
        String user_psd = App.getInstance().getPrefser().get(USER_PSD_KEY, String.class, "");
        if (!StringUtil.isEmpty(user_psd)) {
            getView().setUserPsd(user_psd);
        }
    }

    public void onSaveUserLoginInfo(@NonNull String username, @NonNull String userPsd) {
        Bundle bundle = new Bundle();
        bundle.putString("key", "dadasda");
        BusProvider.getInstance().post(USER_LOGIN_EVENT_TAG,new EventLoginResult(bundle));
        if (!StringUtil.isEmpty(username)) {
            App.getInstance().getPrefser().put(USER_NAME_KEY, username);
        }
        if (!StringUtil.isEmpty(userPsd)) {
            App.getInstance().getPrefser().put(USER_PSD_KEY, userPsd);
        }
    }
}
