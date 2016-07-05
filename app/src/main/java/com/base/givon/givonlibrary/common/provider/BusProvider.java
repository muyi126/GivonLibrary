package com.base.givon.givonlibrary.common.provider;

import com.hwangjr.rxbus.Bus;
import com.hwangjr.rxbus.thread.ThreadEnforcer;


public class BusProvider {

    private static final Bus BUS = new Bus();

    private BusProvider() {
    }

    public static Bus getInstance() {
        return BUS;
    }
}