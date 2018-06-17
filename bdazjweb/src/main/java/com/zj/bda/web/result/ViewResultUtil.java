package com.zj.bda.web.result;

import com.zj.bda.web.enums.ResultEnum;
import com.zj.bda.web.result.vo.OperaterResultVO;

/**
 * Created by Dongguabai on 2018-06-10.
 */
public class ViewResultUtil {

    /**
     * 成功返回
     * @param message  成功返回信息
     * @param resData  成功返回数据
     * @return
     */
    public static OperaterResultVO success(String message,Object resData){
        return new OperaterResultVO(ResultEnum.SUCCESS.getCode(),message,resData);
    }

    /**
     * 成功返回（使用默认成功返回信息）
     * @param resData   成功返回数据
     * @return
     */
    public static OperaterResultVO success(Object resData){
        return success(ResultEnum.SUCCESS.getMessage(),resData);
    }

    /**
     * 成功返回（使用默认成功返回信息，无返回数据）
     * @return
     */
    public static OperaterResultVO success(){
        return success(null);
    }

    /**
     * 异常返回
     * @param code  错误码
     * @param errorMessage  异常信息
     * @param resData   错误返回数据
     * @return
     */
    public static OperaterResultVO error(Integer code, String errorMessage,Object resData){
        return new OperaterResultVO(code,errorMessage,resData);
    }

    /**
     * 异常返回
     * @param code  错误码
     * @param errorMessage  异常信息
     * @return
     */
    public static OperaterResultVO error(Integer code, String errorMessage){
        return error(code,errorMessage,null);
    }

    /**
     * 异常返回
     * @param resultEnum
     * @return
     */
    public static OperaterResultVO error(ResultEnum resultEnum){
        return error(resultEnum.getCode(),resultEnum.getMessage());
    }

    private ViewResultUtil() {
    }
}
