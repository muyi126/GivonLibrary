package com.base.givon.givonlibrary.api.entity;

import com.base.givon.givonlibrary.api.entity.element.User;


import java.util.List;

public class UserEntity {

    public class Users {
        public List<User> data;

        public List<User> getData() {
            return data;
        }

        public void setData(List<User> data) {
            this.data = data;
        }
    }

    public class AUser {
        public User data;

        public User getData() {
            return data;
        }

        public void setData(User data) {
            this.data = data;
        }
    }

}
