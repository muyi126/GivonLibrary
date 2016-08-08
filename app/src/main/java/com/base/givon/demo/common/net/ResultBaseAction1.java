package com.base.givon.demo.common.net;

import rx.functions.Action1;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/8 下午3:38 - Guzhu
 * @email:muyi126@163.com
 */
public abstract class ResultBaseAction1<T1> implements Action1<T1> {

    @Override
    public void call(T1 t1) {
        toCall(t1);
    }

    public abstract void toCall(T1 t1);

}
