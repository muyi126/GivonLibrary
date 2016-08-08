package com.base.givon.baseobj.common.net.Converter;
import android.util.Log;

import com.google.gson.Gson;


import com.base.givon.baseobj.api.entity.HttpResult;
import com.base.givon.baseobj.common.net.Exception.ApiException;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
/**
 *
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/8 上午7:37 - Guzhu
 * @email:muyi126@163.com
 * 直接在Converter就判断SuccessCode
 *
 * 暂时不用  需要用时还要对response处理   只需要转换data到object返回就行
 */



class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        Log.d("Network", "response>>" + response);
        //httpResult 只解析result字段
        HttpResult httpResult = gson.fromJson(response, HttpResult.class);
        //
        if (httpResult.getSuccess() == 0) {
            throw new ApiException(100);
        }
        return gson.fromJson(response, type);


    }
}
