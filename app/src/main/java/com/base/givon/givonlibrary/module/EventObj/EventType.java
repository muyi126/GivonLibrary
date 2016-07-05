package com.base.givon.givonlibrary.module.EventObj;

import com.base.givon.givonlibrary.api.entity.element.Repositories;
import com.hwangjr.rxbus.annotation.Produce;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;

import android.os.Bundle;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/5 下午4:18 - Guzhu
 * @email:muyi126@163.com
 */
public interface EventType {

    String getTag();
    Bundle getBundle();
    void post();
}
