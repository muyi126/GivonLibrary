package com.base.givon.demo.api.entity.element;

/**
 *
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/8 上午9:20 - Guzhu
 * @email:muyi126@163.com
 */
public class User {
//    {"name":"严道永","mobileNumber":"18384330833","token":"f5da1db6c6fc0ddb43a42704434b25d5","id":1}
    public String name;
    public String mobileNumber;
    public String token;
    public String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
