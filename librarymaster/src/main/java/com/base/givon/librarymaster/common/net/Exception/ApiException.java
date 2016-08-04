package com.base.givon.librarymaster.common.net.Exception;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/6 上午10:15 - Guzhu
 * @email:muyi126@163.com
 */
public class ApiException extends RuntimeException {

    public static enum ErrorCode {
        USER_NOT_EXIST(100, "该用户不存在"),
        WRONG_PASSWORD(101, "密码错误"),
        TOKEN_INVALID(-1, "Token失效"),;
        int code;
        String msg;

        ErrorCode(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public static String getCodeMsg(int code) {
            for (ErrorCode errorCode : ErrorCode.values()) {
                if (errorCode.code == code) {
                    return errorCode.msg;
                }
            }
            return "";
        }

        public static boolean hasCode(int code) {
            boolean isHas = false;
            for (ErrorCode errorCode : ErrorCode.values()) {
                if (errorCode.code == code) {
                    return true;
                }
            }
            return false;
        }
    }

    public ApiException(int resultCode) {
        this(ErrorCode.getCodeMsg(resultCode));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

}

