package com.zj.bda.common.web;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
/**
 * 登陆成功Handler
 * @author Dongguabai
 * @date 2018-07-05 19:33
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServerResponseHelper {

    /**
     * 成功返回
     * @param message  成功返回信息
     * @param resData  成功返回数据
     * @return
     */
    public static ServerResponse success(String message, Object resData){
        return new ServerResponse(ServerResponseEnum.SUCCESS.getCode(),message,resData);
    }

    /**
     * 成功返回（使用默认成功返回信息）
     * @param resData   成功返回数据
     * @return
     */
    public static ServerResponse success(Object resData){
        return success(ServerResponseEnum.SUCCESS.getMessage(),resData);
    }

    /**
     * 成功返回（使用默认成功返回信息，无返回数据）
     * @return
     */
    public static ServerResponse success(){
        return success(null);
    }

    /**
     * 异常返回
     * @param code  错误码
     * @param errorMessage  异常信息
     * @param resData   错误返回数据
     * @return
     */
    public static ServerResponse error(Integer code, String errorMessage, Object resData){
        return new ServerResponse(code,errorMessage,resData);
    }

    /**
     * 异常返回
     * @param code  错误码
     * @param errorMessage  异常信息
     * @return
     */
    public static ServerResponse error(Integer code, String errorMessage){
        return error(code,errorMessage,null);
    }

    /**
     * 异常返回
     * @param serverResponseEnum
     * @return
     */
    public static ServerResponse error(ServerResponseEnum serverResponseEnum){
        return error(serverResponseEnum.getCode(), serverResponseEnum.getMessage());
    }

}
