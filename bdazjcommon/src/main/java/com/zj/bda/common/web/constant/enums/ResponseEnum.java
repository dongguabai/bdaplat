package com.zj.bda.common.web.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**返回结果Enum
 * @author Dongguabai
 * @date 2018-07-08 1:42
 */
@AllArgsConstructor
@Getter
public enum ResponseEnum {
    /**
     * 操作成功
     */
    SUCCESS(1,"操作成功！"),
    /**
     * 资源无法找到
     */
    ERROR_NOT_FOUND(-98,"无法找到资源！请检查资源路径！"),
    /**
     * 系统出现其他非定义异常
     */
    ERROR_UNKNOW(-99,"系统异常！"),
    /**
     * 权限不足
     */
    ERROR_NO_PERMISSION(-1,"权限不足！"),
    /**
     * 未登录
     */
    ERROR_UNLOGIN(-2,"未登录！"),
    /**
     * 参数非法，一般用于Controller层
     */
    ERROR_INVALID(-3,"非法参数！"),
    /**
     * 一般用户被连续恶意操作@LocalLockAspect
     */
    ERROR_LIMITED_OPERATION(-4,"该操作已被限制！"),
    /**
     * 验证码异常
     */
    ERROR_GRAPHIC_CAPTCHA_VALIDATE(-5,""),

    /**
     * 登陆失败
     */
    ERROR_LOGIN(-6,"登陆失败");

    /**
     * <=0 异常-》   =0可重复请求   >0成功
     */
    private Integer code;
    private String message;

}
