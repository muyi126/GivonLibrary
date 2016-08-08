package com.base.givon.demo.module.EventObj;

import android.os.Bundle;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/5 下午9:44 - Guzhu
 * @email:muyi126@163.com
 */
public class EventLoginResult implements EventType {
    public static final String USER_LOGIN_EVENT_TAG = "user_login_event_tag";
    private Bundle mBundle;

    public EventLoginResult(Bundle bundle) {
        mBundle = bundle;
    }

    @Override
    public String getTag() {
        return USER_LOGIN_EVENT_TAG;
    }

    @Override
    public Bundle getBundle() {
        return mBundle;
    }

    @Override
    public void post() {

    }
}
