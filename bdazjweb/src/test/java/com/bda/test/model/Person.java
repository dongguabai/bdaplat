package com.bda.test.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by Dongguabai on 2018-06-14.
 */
public class Person {
    private String userName;
    private String password;

    @Override
    public String toString() {
       return ReflectionToStringBuilder.toString(this, ToStringStyle.DEFAULT_STYLE);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
