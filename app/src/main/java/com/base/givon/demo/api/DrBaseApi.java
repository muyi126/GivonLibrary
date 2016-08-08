package com.base.givon.demo.api;



import com.base.givon.demo.api.entity.HttpResult;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/7 下午5:32 - Guzhu
 * @email:muyi126@163.com
 */
public interface DrBaseApi {
    //获取验证码 method=checkVerifyCode 校验验证码 createVerifyCode 获取验证码
//    @GET("back/verifyCode.do")
//    Observable<HttpResult<String>> verifyCode(@Query("method") String method, @Query("mobileNumber") String mobileNumber, @Query("code") String code);
    @FormUrlEncoded
    @POST("back/verifyCode.do")
    Observable<HttpResult<String>> verifyCodeGet(@Field("method") String method, @Field("mobileNumber") String mobileNumber, @Field("code") String code);

    @FormUrlEncoded
    @POST("back/verifyCode.do")
    Observable<HttpResult<com.base.givon.demo.api.entity.element.Verify>> verifyCode(@Field("method") String method, @Field("mobileNumber") String mobileNumber, @Field("code") String code);

    //注册 register 登录 login
    @FormUrlEncoded
    @POST("back/api/driver/base/base.do")
    Observable<HttpResult<com.base.givon.demo.api.entity.element.User>> userAction(@Field("method") String method, @Field("name") String name, @Field("mobileNumber") String mobileNumber, @Field("code") String code, @Field("password") String password);

    //获取首页订单数量 getOrderCount
    @FormUrlEncoded
    @POST("back/api/driver/common/order.do")
    Observable<HttpResult<com.base.givon.demo.api.entity.OrderCountEntity>> order(@Field("method") String method, @Field("token") String token);

    //获取司机信息 getDriverInfo(token)
    @FormUrlEncoded
    @POST("back/api/driver/common/account.do")
    Observable<HttpResult<com.base.givon.demo.api.entity.element.User>> account(@Field("method") String method, @Field("token") String token, @Field("password") String password, @Field("newPassword") String newPassword);

    //修改密码 updatePassword(token,password,newPassword)
    @FormUrlEncoded
    @POST("back/api/driver/common/account.do")
    Observable<HttpResult<String>> accountUpdatePassword(@Field("method") String method, @Field("token") String token, @Field("password") String password, @Field("newPassword") String newPassword);

    //获取订单列表 findOrderList pageNo（从0开始) dateType（0：今日；1：全部) tripDate（2016、2016-07、2016-07-11) isComplete（0：未完成；1：已完成）
    @FormUrlEncoded
    @POST("back/api/driver/common/order.do")
    Observable<HttpResult<com.base.givon.demo.api.entity.OrderListEntity>> findOrderList(@Field("method") String method, @Field("token") String token, @Field("pageNo") int pageNo, @Field("dateType") int dateType, @Field("tripDate") String tripDate, @Field("isComplete") int isComplete);


    //获取订单详情
    @FormUrlEncoded
    @POST("back/api/driver/common/order.do")
    Observable<HttpResult<com.base.givon.demo.api.entity.OrderEntity>> viewDriverOrder(@Field("method") String method, @Field("token") String token, @Field("driverOrderId") String driverOrderId);


    //确认接受订单
    @FormUrlEncoded
    @POST("back/api/driver/common/order.do")
    Observable<HttpResult<String>> confirmDriverOrder(@Field("method") String method, @Field("token") String token, @Field("driverOrderId") String driverOrderId);


    //完成订单   可以合并
    @FormUrlEncoded
    @POST("back/api/driver/common/order.do")
    Observable<HttpResult<String>> completeDriverOrder(@Field("method") String method, @Field("token") String token, @Field("driverOrderId") String driverOrderId);


    //忘记密码  forgetPassword 可以合并
    @FormUrlEncoded
    @POST("back/api/driver/base/base.do")
    Observable<HttpResult<String>> forgetPassword(@Field("method") String method, @Field("mobileNumber") String mobileNumber, @Field("code") String code, @Field("password") String password);


    //设置消息为已读 readMessage
    @FormUrlEncoded
    @POST("back/api/driver/common/message.do")
    Observable<HttpResult<String>> readMessage(@Field("method") String method, @Field("token") String token, @Field("messageId") String messageId);


    //-------------------------GET METHOD----------------------------------///

    //获取消息列表 findMessageList
    @GET("back/api/driver/common/message.do")
    Observable<HttpResult<com.base.givon.demo.api.entity.OrderMessageEntity>> findMessageList(@Query("method") String method, @Query("token") String token, @Query("pageNo") int pageNo);

}
