package com.base.givon.demo.api.entity;

import com.google.gson.annotations.SerializedName;

import com.base.givon.demo.api.entity.element.Repositories;

import java.util.List;

/**
 *
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @六月 16/6/30 下午8:02 - Guzhu
 * @email:muyi126@163.com
 */
public class RepositoriesEntity {
    protected int total_count;
    boolean incomplete_results;
    @SerializedName("items")
    List<Repositories> data;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<Repositories> getData() {
        return data;
    }

    public void setData(List<Repositories> data) {
        this.data = data;
    }
}
