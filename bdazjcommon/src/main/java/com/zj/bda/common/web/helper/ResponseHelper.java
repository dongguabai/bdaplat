package com.zj.bda.common.web.helper;

import com.zj.bda.common.web.constant.enums.ResponseEnum;
import com.zj.bda.common.web.vo.ResponseVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
/**
 * 登陆成功Handler
 * @author Dongguabai
 * @date 2018-07-05 19:33
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseHelper {

    /**
     * 成功返回
     * @param message  成功返回信息
     * @param resData  成功返回数据
     * @return
     */
    public static ResponseVO success(String message, Object resData){
        return new ResponseVO(ResponseEnum.SUCCESS.getCode(),message,resData);
    }

    /**
     * 成功返回（使用默认成功返回信息）
     * @param resData   成功返回数据
     * @return
     */
    public static ResponseVO success(Object resData){
        return success(ResponseEnum.SUCCESS.getMessage(),resData);
    }

    /**
     * 成功返回（使用默认成功返回信息，无返回数据）
     * @return
     */
    public static ResponseVO success(){
        return success(null);
    }

    /**
     * 异常返回
     * @param code  错误码
     * @param errorMessage  异常信息
     * @param resData   错误返回数据
     * @return
     */
    public static ResponseVO error(Integer code, String errorMessage, Object resData){
        return new ResponseVO(code,errorMessage,resData);
    }

    /**
     * 异常返回
     * @param code  错误码
     * @param errorMessage  异常信息
     * @return
     */
    public static ResponseVO error(Integer code, String errorMessage){
        return error(code,errorMessage,null);
    }

    /**
     * 异常返回
     * @param responseEnum
     * @return
     */
    public static ResponseVO error(ResponseEnum responseEnum){
        return error(responseEnum.getCode(), responseEnum.getMessage());
    }

}
