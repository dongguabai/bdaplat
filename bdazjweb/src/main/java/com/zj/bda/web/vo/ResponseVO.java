package com.zj.bda.web.vo;

import com.zj.bda.web.enums.ResponseEnum;

import java.io.Serializable;

/**
 * Created by Dongguabai on 2018-06-13.
 * 统一返回对象
 */
public class ResponseVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 返回消息
     * @see ResponseEnum
     */
    private String msg = "success";

    /**
     * 0   : 成功
     * >0 :未定义异常
     * <0 : 自定义异常
     * @see ResponseEnum
     */
    private Integer code = 0;

    /**
     * 返回数据
     */
    private T data;

    public ResponseVO(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public ResponseVO(Integer code, String msg, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public ResponseVO() {
        super();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
