package com.zj.bda.common.web.vo;


import com.zj.bda.common.web.enums.ResponseEnum;
import lombok.*;

import java.io.Serializable;

/**
 * 登陆成功Handler
 * @author Dongguabai
 * @date 2018-07-05 19:33
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ResponseVO<T> implements Serializable {

    /**
     * 0   : 成功
     * >0 :未定义异常
     * <0 : 自定义异常
     *
     * @see ResponseEnum
     */
    private Integer code;

    /**
     * 返回消息
     *
     * @see ResponseEnum
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public ResponseVO(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

}
