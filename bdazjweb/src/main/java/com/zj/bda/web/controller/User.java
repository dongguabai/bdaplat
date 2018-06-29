package com.zj.bda.web.controller;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * Created by Dongguabai on 2018-06-21 0:28
 */
@Data
public class User {
    @Length(min = 1, max = 20, message = "aaaaaaaaaaaaaaaa不能为空")
    private String userName;
    private String password;
}
