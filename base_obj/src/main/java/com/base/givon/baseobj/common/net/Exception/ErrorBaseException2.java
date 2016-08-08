package com.base.givon.baseobj.common.net.Exception;

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
public abstract class ErrorBaseException2<T> extends ErrorBaseException1 implements Action2<T, Throwable> {

    private T t;

    @Override
    public void call(T t, Throwable throwable) {
        this.t = t;
        super.call(throwable);
    }

    @Override
    public void toCall(Throwable throwable) {
        toCall(t, throwable);
    }

    public abstract void toCall(T t, Throwable throwable);
}
