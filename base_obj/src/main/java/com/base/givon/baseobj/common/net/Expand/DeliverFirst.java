package com.base.givon.baseobj.common.net.Expand;

import rx.Notification;
import rx.Observable;
import rx.functions.Func1;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/21 下午12:55 - Guzhu
 * @email:muyi126@163.com
 */
public class DeliverFirst<T> implements Observable.Transformer<T, Delivery<T>> {

    public DeliverFirst() {

    }

    @Override
    public Observable<Delivery<T>> call(Observable<T> observable) {
        return observable.materialize().take(1).flatMap(new Func1<Notification<T>, Observable<Delivery<T>>>() {
            @Override
            public Observable<Delivery<T>> call(Notification<T> notification) {
                Delivery<T> deliv = new Delivery<>(notification);
                return Observable.just(deliv);
            }
        });
    }
}
