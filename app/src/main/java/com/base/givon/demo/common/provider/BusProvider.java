package com.base.givon.demo.common.provider;

import com.hwangjr.rxbus.Bus;


public class BusProvider {

    private static final Bus BUS = new Bus();

    private BusProvider() {
    }

    public static Bus getInstance() {
        return BUS;
    }
}