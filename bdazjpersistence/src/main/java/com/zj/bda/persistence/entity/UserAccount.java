package com.zj.bda.persistence.entity;

import lombok.*;

import javax.persistence.Table;

/**
 * @author dongguabai
 * @date 2019-03-14 22:17
 */
@Table(name = "user_account")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class UserAccount {

    private Integer id;
    private String username;
    private Long balance;

}
