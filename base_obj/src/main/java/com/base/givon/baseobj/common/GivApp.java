package com.base.givon.baseobj.common;

import com.base.givon.baseobj.common.net.RetrofitUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.github.pwittchen.prefser.library.Prefser;
import com.squareup.leakcanary.LeakCanary;

import android.app.Application;

import retrofit2.Retrofit;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @六月 16/6/21 上午8:58 - Guzhu
 * @email:muyi126@163.com
 */
public abstract class GivApp extends Application {

    public static GivApp mApp;
    //    private User mUser;
    //初始化了Okhttp

    public Retrofit mRetrofit;
    public Prefser mPrefser;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        Fresco.initialize(this);
        LeakCanary.install(this);
        mRetrofit = RetrofitUtils.getRetrofit();
    }

    public static GivApp getInstance() {
        return mApp;
    }


    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        mRetrofit = retrofit;
    }
    //
//    public Prefser getPrefser() {
//        return mPrefser;
//    }

//    public User getUser() {
//        if (null != mUser && !StringUtil.isEmpty(mUser.getToken())) {
//            return mUser;
//        }
//        return null;
//    }
//
//    public void initUser() {
//        mUser = mPrefser.get(Constant.USER_MODEL, User.class, new User());
//    }
}
