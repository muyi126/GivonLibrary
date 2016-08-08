package com.base.givon.demo.module;


import com.base.givon.demo.api.DrBaseApi;
import com.base.givon.demo.api.entity.OrderEntity;
import com.base.givon.demo.api.entity.OrderListEntity;
import com.base.givon.demo.api.entity.element.User;
import com.base.givon.demo.common.transformer.BaseApiTransformer;

import android.content.Context;
import android.support.annotation.NonNull;

import rx.Observable;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/7 下午5:40 - Guzhu
 * @email:muyi126@163.com
 */
public class DrBaseModel extends com.base.givon.demo.common.base.BaseModel<DrBaseApi, DrBaseModel> {
    public DrBaseModel(@NonNull Context context) {
        super(context);
    }

    @Override
    protected Class<DrBaseApi> getServiceClass() {
        return DrBaseApi.class;
    }

    //获取验证码
    public Observable<String> getVerifyCode(String mobileNumber) {
        return getService().verifyCodeGet("createVerifyCode", mobileNumber, "").compose(new BaseApiTransformer<String>());
    }

    //校验验证码
    public Observable<com.base.givon.demo.api.entity.element.Verify> checkVerifyCode(String mobileNumber, String code) {
        return getService().verifyCode("checkVerifyCode", mobileNumber, code).compose(new BaseApiTransformer<com.base.givon.demo.api.entity.element.Verify>());
    }

    //注册
    public Observable<User> actionRegister(String name, String mobileNumber, String code, String password) {
        return getService().userAction("register", name, mobileNumber, code, password).compose(new BaseApiTransformer<User>());
    }

    //登录
    public Observable<User> actionLogin(String mobileNumber, String password) {
        return getService().userAction("login", "", mobileNumber, "", password).compose(new BaseApiTransformer<User>());
    }

    //获取首页订单数量
    public Observable<com.base.givon.demo.api.entity.OrderCountEntity> getOrderCount(String token) {
        return getService().order("getOrderCount", token).compose(new BaseApiTransformer<com.base.givon.demo.api.entity.OrderCountEntity>());
    }

    //获取司机信息
    public Observable<User> getDriverInfo(String token) {
        return getService().account("getDriverInfo", token, "", "").compose(new BaseApiTransformer<User>());
    }

    //修改密码
    public Observable<String> updatePassword(String token, String password, String newPassword) {
        return getService().accountUpdatePassword("updatePassword", token, password, newPassword).compose(new BaseApiTransformer<String>());
    }

    //获取订单列表
    public Observable<OrderListEntity> findOrderList(String token, int pageNo, int dateType, String tripDate, int isComplete) {
        return getService().findOrderList("findOrderList", token, pageNo, dateType, tripDate, isComplete).compose(new BaseApiTransformer<OrderListEntity>());
    }

    //获取订单详情
    public Observable<OrderEntity> viewDriverOrder(String token, String driverOrderId) {
        return getService().viewDriverOrder("viewDriverOrder", token, driverOrderId).compose(new BaseApiTransformer<OrderEntity>());
    }

    //确认接受订单
    public Observable<String> confirmDriverOrder(String token, String driverOrderId) {
        return getService().confirmDriverOrder("confirmDriverOrder", token, driverOrderId).compose(new BaseApiTransformer<String>());
    }

    //确认完成订单
    public Observable<String> completeDriverOrder(String token, String driverOrderId) {
        return getService().completeDriverOrder("completeDriverOrder", token, driverOrderId).compose(new BaseApiTransformer<String>());
    }

    //忘记密码 更新密码
    public Observable<String> forgetPassword(String mobileNumber, String code, String password) {
        return getService().forgetPassword("forgetPassword", mobileNumber, code, password).compose(new BaseApiTransformer<String>());
    }

    //获取消息列表
    public Observable<com.base.givon.demo.api.entity.OrderMessageEntity> findMessageList(String token, int pageNo) {
        return getService().findMessageList("findMessageList", token, pageNo).compose(new BaseApiTransformer<com.base.givon.demo.api.entity.OrderMessageEntity>());
    }

    //设置消息为已读
    public Observable<String> readMessage(String token, String messageId) {
        return getService().readMessage("readMessage", token, messageId).compose(new BaseApiTransformer<String>());
    }
}
