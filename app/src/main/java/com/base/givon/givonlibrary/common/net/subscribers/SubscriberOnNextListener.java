package com.base.givon.givonlibrary.common.net.subscribers;

/**
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/6 上午10:30 - Guzhu
 * @email:muyi126@163.com
 **/
public interface SubscriberOnNextListener<T> {
    void onNext(T t);
}
