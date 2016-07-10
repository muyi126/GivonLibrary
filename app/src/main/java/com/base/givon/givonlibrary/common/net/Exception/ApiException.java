package com.base.givon.givonlibrary.common.net.Exception;

/**
 *
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/6 上午10:15 - Guzhu
 * @email:muyi126@163.com
 */
public class ApiException extends RuntimeException {

    public static final int USER_NOT_EXIST = 100;
    public static final int WRONG_PASSWORD = 101;
    public static final int TOKEN_INVALID = -1;

    public ApiException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(int code){
        String message = "";
        switch (code) {
            case USER_NOT_EXIST:
                message = "该用户不存在";
                break;
            case WRONG_PASSWORD:
                message = "密码错误";
                break;
            case TOKEN_INVALID:
                message = "Token失效";
                break;
            default:
                message = "未知错误";

        }
        return message;
    }
}

