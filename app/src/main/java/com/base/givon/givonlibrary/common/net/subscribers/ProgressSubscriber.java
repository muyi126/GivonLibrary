package com.base.givon.givonlibrary.common.net.subscribers;

import com.jingchu.ltd.carlist.common.App;
import com.jingchu.ltd.carlist.common.net.progress.ProgressCancelListener;
import com.jingchu.ltd.carlist.common.net.progress.ProgressDialogHandler;

import android.content.Context;
import android.widget.Toast;


import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/6 上午10:27 - Guzhu
 * @email:muyi126@163.com
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 */
public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {

    private SubscriberOnNextListener mSubscriberOnNextListener;
    private ProgressDialogHandler mProgressDialogHandler;


    public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        mProgressDialogHandler = new ProgressDialogHandler(App.getInstance(), this, true);
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        showProgressDialog();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        dismissProgressDialog();
        Toast.makeText(App.getInstance(), "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     */
    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(App.getInstance(), "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(App.getInstance(), "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(App.getInstance(), "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        dismissProgressDialog();

    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}