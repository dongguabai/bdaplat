package com.zj.bda.web.enums;

/**返回结果Enum
 * Created by Dongguabai on 2018-06-10.
 */
public enum ResultEnum {
    SUCCESS(1,"操作成功！"),
    ERROR_NOT_FOUND(-98,"无法找到资源！请检查资源路径！"),
    ERROR_UNKNOW(-99,"系统异常！"),
    ERROR_NO_PERMISSION(-1,"权限不足！"),
    ERROR_UNLOGIN(-2,"未登录！"),
    ERROR_INVALID(-3,"非法参数！");

    //<=0 异常-》   =0可重复请求   >0成功
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
