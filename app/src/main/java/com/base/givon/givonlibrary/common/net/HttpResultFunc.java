package com.base.givon.givonlibrary.common.net;


import com.base.givon.givonlibrary.api.entity.HttpResult;
import com.base.givon.givonlibrary.common.net.Exception.ApiException;
import com.base.givon.givonlibrary.common.utils.StringUtil;

import rx.functions.Func1;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
 * @author givon
 * @version 1.0
 * @七月 16/7/6 上午10:27 - Guzhu
 * @email:muyi126@163.com 用来统一处理Http的resultCode, 并将HttpResult的Data部分剥离出来返回给subscriber
 */

public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {
    Class<T> clazz;

    @Override
    public T call(HttpResult<T> httpResult) {
        if (ApiException.ErrorCode.hasCode(httpResult.getSuccess())) {
            throw new ApiException(httpResult.getSuccess());
        }
        if (null == httpResult.getData() && !StringUtil.isEmpty(httpResult.getMessage())) {
            try {
                httpResult.setData((T) httpResult.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return httpResult.getData();
    }
}
