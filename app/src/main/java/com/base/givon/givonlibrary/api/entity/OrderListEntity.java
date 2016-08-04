package com.base.givon.givonlibrary.api.entity;


import com.base.givon.givonlibrary.api.entity.element.Order;

import java.io.Serializable;
import java.util.List;

public class OrderListEntity implements Serializable {
    private int unCompleteCount;
    private int totalPage;
    private int pageNo;
    private int completeCount;
    private int recordSize;
    private List<Order> driverOrderList;

    public int getUnCompleteCount() {
        return this.unCompleteCount;
    }

    public void setUnCompleteCount(int unCompleteCount) {
        this.unCompleteCount = unCompleteCount;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getCompleteCount() {
        return this.completeCount;
    }

    public void setCompleteCount(int completeCount) {
        this.completeCount = completeCount;
    }

    public int getRecordSize() {
        return this.recordSize;
    }

    public void setRecordSize(int recordSize) {
        this.recordSize = recordSize;
    }

    public List<Order> getDriverOrderList() {
        return driverOrderList;
    }

    public void setDriverOrderList(List<Order> driverOrderList) {
        this.driverOrderList = driverOrderList;
    }
}
