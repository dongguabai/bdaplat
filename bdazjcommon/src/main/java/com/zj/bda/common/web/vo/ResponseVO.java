package com.zj.bda.common.web.vo;

import lombok.*;

import java.io.Serializable;

/**
 * Created by Dongguabai on 2018-06-13.
 * 统一返回对象
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ResponseVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 0   : 成功
     * >0 :未定义异常
     * <0 : 自定义异常
     *
     * @see com.zj.bda.common.web.constant.enums.ResponseEnum
     */
    private Integer code;

    /**
     * 返回消息
     *
     * @see com.zj.bda.common.web.constant.enums.ResponseEnum
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
