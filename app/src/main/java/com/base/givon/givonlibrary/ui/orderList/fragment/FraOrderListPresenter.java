package com.base.givon.givonlibrary.ui.orderList.fragment;


import com.base.givon.givonlibrary.api.entity.OrderListEntity;
import com.base.givon.givonlibrary.common.base.BaseRxPresenter;
import com.base.givon.givonlibrary.common.net.Exception.ErrorBaseException2;
import com.base.givon.givonlibrary.common.net.ResultBaseAction;
import com.base.givon.givonlibrary.module.DrBaseModel;
import com.base.givon.givonlibrary.ui.orderList.ActOrderLsitActivity;

import android.os.Bundle;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func0;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/7 下午2:08 - Guzhu
 * @email:muyi126@163.com
 */
public class FraOrderListPresenter extends BaseRxPresenter<OrderListFragment> {
    //这个Id是用来匹配方法的  如果没有对应id的方法将抛出异常
    private static final int REQUEST_ID = 1;
    private ActOrderLsitActivity.LaunchTag dateType;//（0：今日；1：全部)
    @Inject
    DrBaseModel mDrBaseModel;
    String tripDate = "";
    int mTag;
    protected int pageIndex = 0;


    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        restartableFirst(REQUEST_ID, new Func0<Observable<OrderListEntity>>() {
            @Override
            public Observable<OrderListEntity> call() {
                //需要设置token
                return mDrBaseModel.findOrderList("", pageIndex, dateType.code, tripDate, mTag);
            }

        }, new ResultBaseAction<OrderListFragment, OrderListEntity>() {
            @Override
            public void toCall(OrderListFragment orderListFragment, OrderListEntity orderListEntity) {
                orderListFragment.onChangeItems(orderListEntity, pageIndex);

            }
        }, new ErrorBaseException2<OrderListFragment>() {
            @Override
            public void toCall(OrderListFragment orderListFragment, Throwable throwable) {
                orderListFragment.onNetworkError(throwable, pageIndex);
            }
        });
    }

    @Override
    protected void onTakeView(OrderListFragment orderListFragment) {
        super.onTakeView(orderListFragment);

    }


    public void refresh(ActOrderLsitActivity.LaunchTag dateType, int mTag, String tripDate) {
        this.dateType = dateType;
        this.mTag = mTag;
        this.tripDate = tripDate;
        pageIndex = 0;
        start(REQUEST_ID);
    }

    public void nextPage(ActOrderLsitActivity.LaunchTag dateType, int mTag, String tripDate) {
        this.dateType = dateType;
        this.mTag = mTag;
        this.tripDate = tripDate;
        pageIndex++;
        start(REQUEST_ID);
    }
}
