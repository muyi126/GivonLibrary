package com.base.givon.givonlibrary.api.entity;

/**
 *
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/8 下午4:56 - Guzhu
 * @email:muyi126@163.com
 * 这个名称可能需要修改   这个类在menu时显示订单数量使用
 */
public class OrderCountEntity {
    private int todayOrderCount;
    private int allOrderCount;
    private int unReadMessageCount;

    public int getTodayOrderCount() {
        return todayOrderCount;
    }

    public void setTodayOrderCount(int todayOrderCount) {
        this.todayOrderCount = todayOrderCount;
    }

    public int getAllOrderCount() {
        return allOrderCount;
    }

    public void setAllOrderCount(int allOrderCount) {
        this.allOrderCount = allOrderCount;
    }

    public int getUnReadMessageCount() {
        return unReadMessageCount;
    }

    public void setUnReadMessageCount(int unReadMessageCount) {
        this.unReadMessageCount = unReadMessageCount;
    }
}
