package com.base.givon.demo.common.base;

import com.base.givon.baseobj.common.base.GivBaseRxPresenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import icepick.Icepick;
import nucleus.presenter.RxPresenter;
import rx.Observable;
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
public class BaseRxPresenter<View> extends GivBaseRxPresenter<View> {
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }

    @Override
    protected void onSave(Bundle state) {
        super.onSave(state);
    }

}
