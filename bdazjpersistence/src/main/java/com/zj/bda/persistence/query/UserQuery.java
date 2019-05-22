package com.zj.bda.persistence.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author dongguabai
 * @Description
 * @Date 创建于 2019-05-17 23:04
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery {

    private String username;

    private Integer balance;
}
