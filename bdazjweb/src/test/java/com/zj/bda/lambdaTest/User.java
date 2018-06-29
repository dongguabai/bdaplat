package com.zj.bda.lambdaTest;

import lombok.*;

/**
 * Created by Dongguabai on 2018-06-28 11:32
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {

    private String username;
    private String password;
    private Integer age;
}
