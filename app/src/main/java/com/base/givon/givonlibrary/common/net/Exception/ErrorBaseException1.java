package com.base.givon.givonlibrary.common.net.Exception;

import com.google.gson.Gson;


import com.base.givon.givonlibrary.api.entity.HttpResult;
import com.base.givon.givonlibrary.common.App;
import com.base.givon.givonlibrary.common.utils.StringUtil;
import com.base.givon.givonlibrary.common.utils.ToastUtils;
import com.base.givon.givonlibrary.common.utils.UiUtil;

import android.widget.Toast;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.functions.Action1;
import rx.functions.Action2;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/8 上午10:27 - Guzhu
 * @email:muyi126@163.com
 */
public abstract class ErrorBaseException1 implements Action1<Throwable> {
    @Override
    public void call(Throwable throwable) {
        String json = null;
        String msg = null;
        if (throwable instanceof SocketTimeoutException) {
            Toast.makeText(App.getInstance(), "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
            ToastUtils.showMessage("链接超时,请检查你的网络");
        } else if (throwable instanceof ConnectException) {
            ToastUtils.showMessage("链接中断,请检查你的网络");
        } else if (throwable instanceof HttpException) {
            if (((HttpException) throwable).code() == 500) {
                try {
                    json = ((HttpException) throwable).response().errorBody().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (null != json && json.length() > 0) {
                    Gson gson = new Gson();
                    HttpResult httpResult = gson.fromJson(json, HttpResult.class);
                    if (!StringUtil.isEmpty(httpResult.getMessage())) {
                        ToastUtils.showMessage(httpResult.getMessage());
                        msg = httpResult.getMessage();
                    }
                } else {
                    ToastUtils.showMessage("服务器内部错误");
                }

            } else if (((HttpException) throwable).code() == 404) {
                ToastUtils.showMessage("页面找不到了");
            }
        } else if (throwable instanceof ApiException) {
            String detailMsg = throwable.getMessage();
            if ("Token失效".equals(detailMsg)) {
                //重新登录
                UiUtil.showLogOutDialog();
            } else if (!StringUtil.isEmpty(throwable.getMessage())) {
                ToastUtils.showMessage(throwable.getMessage());
            }
        } else {
            if (!StringUtil.isEmpty(throwable.getMessage())) {
//                ToastUtils.showMessage(throwable.getMessage());
            }
        }
        toCall(throwable);
        toCall(throwable, json, msg);
    }

    public abstract void toCall(Throwable throwable);

    public void toCall(Throwable throwable, String json, String msg) {

    }

}
