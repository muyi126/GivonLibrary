package com.base.givon.demo.common.net;

import rx.functions.Action2;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/8 下午3:38 - Guzhu
 * @email:muyi126@163.com
 */
public abstract class ResultBaseAction2<T1, T2> implements Action2<T1, T2> {

    @Override
    public void call(T1 t1, T2 t2) {
        toCall(t1, t2);
    }

    public abstract void toCall(T1 t1, T2 t2);

}
