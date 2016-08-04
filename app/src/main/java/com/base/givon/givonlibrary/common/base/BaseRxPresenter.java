package com.base.givon.givonlibrary.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import icepick.Icepick;
import nucleus.presenter.RxPresenter;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action2;
import rx.functions.Func0;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @六月 16/6/23 下午2:29 - Guzhu
 * @email:muyi126@163.com
 */
public class BaseRxPresenter<View> extends RxPresenter<View> {
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        Icepick.restoreInstanceState(this, savedState);
    }

    @Override
    protected void onSave(Bundle state) {
        super.onSave(state);
        Icepick.saveInstanceState(this, state);
    }


    public <T> void startOnce(final Func0<Observable<T>> observableFactory,
                               final Action2<View, T> onNext, @Nullable final Action2<View, Throwable> onError) {
        observableFactory.call().compose(BaseRxPresenter.this.<T>deliverFirst()).subscribe(split(onNext, onError));
    }

}
