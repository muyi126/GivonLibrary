package com.base.givon.librarymaster.common.qualifier;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import static com.base.givon.librarymaster.common.qualifier.AuthType.*;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 *
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @六月 16/6/21 上午11:37 - Guzhu
 * @email:muyi126@163.com
 */
@Retention(SOURCE)
@StringDef({
        AUTH_TYPE_GUEST,
        AUTH_TYPE_USER,
        AUTH_TYPE_REFRESH
})
public @interface AuthType {
    String AUTH_TYPE_GUEST = "client_credentials";

    String AUTH_TYPE_USER = "login_token";

    String AUTH_TYPE_REFRESH = "refresh_token";
}