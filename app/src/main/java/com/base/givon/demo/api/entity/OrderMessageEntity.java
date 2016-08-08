package com.base.givon.demo.api.entity;


import com.base.givon.demo.api.entity.element.Order;

import java.util.List;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/19 下午5:28 - Guzhu
 * @email:muyi126@163.com
 */
public class OrderMessageEntity {
    private List<Order> messageList;
    private int totalPage;
    private int pageNo;
    private int recordSize;

    public List<Order> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Order> messageList) {
        this.messageList = messageList;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(int recordSize) {
        this.recordSize = recordSize;
    }
}
