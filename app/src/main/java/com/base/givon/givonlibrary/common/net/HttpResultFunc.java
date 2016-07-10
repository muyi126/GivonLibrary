package com.base.givon.givonlibrary.common.net;


import com.jingchu.ltd.carlist.api.entity.HttpResult;
import com.jingchu.ltd.carlist.common.net.Exception.ApiException;
import com.jingchu.ltd.carlist.common.utils.StringUtil;

import rx.functions.Func1;

/**
 *
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/6 上午10:27 - Guzhu
 * @email:muyi126@163.com
 *
 * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
 *
 * @param <T>   Subscriber真正需要的数据类型，也就是Data部分的数据类型
 */

public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

    @Override
    public T call(HttpResult<T> httpResult) {
        if (httpResult.getSuccess() == 0) {
            throw new ApiException(0);
        }
        if (httpResult.getSuccess() == -1) {
            throw new ApiException(-1);
        }
        if(null==httpResult.getData()&& !StringUtil.isEmpty(httpResult.getMessage())){
            httpResult.setData((T) httpResult.getMessage());
        }
        return httpResult.getData();
    }
}
