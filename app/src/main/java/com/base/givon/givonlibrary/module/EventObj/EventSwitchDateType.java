package com.base.givon.givonlibrary.module.EventObj;

import android.os.Bundle;

/**
 *
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/12 上午10:19 - Guzhu
 * @email:muyi126@163.com
 */
public class EventSwitchDateType implements EventType{
    public static final String SWITCH_DATE_EVENT_TAG = "switch_date_event_tag";
    public static final String SELECT_DATE_EVENT_TAG = "select_date_event_tag";
    public static final String ORDER_NUM_TAG = "order_num_tag";
    private Bundle mBundle;
    public EventSwitchDateType() {
    }
    public EventSwitchDateType(Bundle bundle) {
        mBundle = bundle;
    }

    @Override
    public String getTag() {
        return SWITCH_DATE_EVENT_TAG;
    }

    @Override
    public Bundle getBundle() {
        return mBundle;
    }

    @Override
    public void post() {

    }
}
