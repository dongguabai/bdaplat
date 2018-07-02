package com.zj.bda.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Setter;

/**
 * Created by Dongguabai on 2018-06-21 0:28
 */
@Setter
@Builder
public class User {
    public interface UserDetail{}

    public interface UserInfo extends UserDetail{}

    /**
     * 在UserInfo视图上展示userNamw这个字段；
     * 要注意的是UserInfo继承UserDetail，所以展示的时候也会展示UserDetail视图中的字段，也就是password字段
     * @return
     */
    @JsonView(UserInfo.class)
    public String getUserName() {
        return userName;
    }

    /**
     * 在UserDetail视图上展示password字段
     * @return
     */
    @JsonView(UserDetail.class)
    public String getPassword() {
        return password;
    }

    private String userName;
    private String password;

}
