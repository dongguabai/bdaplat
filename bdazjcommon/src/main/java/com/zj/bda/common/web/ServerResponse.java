package com.zj.bda.common.web;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

/**
 * 统一响应对象
 *
 * @author Dongguabai
 * @date 2018-07-05 19:33
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
////将该标记放在属性上，如果该属性为NULL则不参与序列化
////如果放在类上边,那对这个类的全部属性起作用
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse<T> implements Serializable {

    /**
     *
     * 0   : 重复请求
     * >0 :成功
     * <0 : 自定义异常
     *
     * @see ServerResponseEnum
     */
    private Integer code;

    /**
     * 返回消息
     *
     * @see ServerResponseEnum
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public ServerResponse(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.code.equals(ServerResponseEnum.SUCCESS.getCode());
    }

}
