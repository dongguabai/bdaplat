package com.zj.bda.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zj.bda.common.util.JsonUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by Dongguabai on 2018-06-14.
 */
@AllArgsConstructor
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

    @JsonProperty("customerName")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public static void main(String[] args) {
        String s = JsonUtil.toJSON(new Person("aa", "bb"));
        System.out.println(s);
    }
}
