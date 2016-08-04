package com.base.givon.givonlibrary.common.net.Expand;

import android.support.annotation.Nullable;

import rx.Notification;
import rx.functions.Action1;
import rx.functions.Action2;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/21 下午12:56 - Guzhu
 * @email:muyi126@163.com
 */
public final class Delivery<T> {

    private final Notification<T> notification;

    public Delivery(Notification<T> notification) {
        this.notification = notification;
    }

    public void split(Action1<T> onNext, @Nullable Action1<Throwable> onError) {
        if (notification.getKind() == Notification.Kind.OnNext)
            onNext.call(notification.getValue());
        else if (onError != null && notification.getKind() == Notification.Kind.OnError)
            onError.call(notification.getThrowable());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Delivery<?> delivery = (Delivery<?>) o;
//        if (view != null ? !view.equals(delivery.view) : delivery.view != null) return false;
        return !(notification != null ? !notification.equals(delivery.notification) : delivery.notification != null);
    }

//    @Override
//    public int hashCode() {
//        int result = view != null ? view.hashCode() : 0;
//        result = 31 * result + (notification != null ? notification.hashCode() : 0);
//        return result;
//    }

    @Override
    public String toString() {
        return "Delivery{" +
//                "view=" + view +
                ", notification=" + notification +
                '}';
    }
}
