package com.zj.bda.common.util;

import com.zj.bda.common.web.util.ResponseUtil;
import com.zj.bda.common.web.vo.ResponseVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Dongguabai
 * @date 2018-07-05 20:24
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebUtil {

    public static boolean isAjax(HttpServletRequest request){
        boolean isAjaxRequest = false;
        if(!StringUtils.isBlank(request.getHeader("x-requested-with")) && StringUtils.equals(request.getHeader("x-requested-with"),"XMLHttpRequest")){
            isAjaxRequest = true;
        }
        return isAjaxRequest;
    }


    public static void responseErrorJson(HttpServletResponse response,Object resData,HttpStatus httpStatus,String message,Integer errorCode) throws IOException {
        response.setStatus(httpStatus.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JsonUtil.toJSON(ResponseUtil.error(errorCode,message,resData)));
    }

    public static void responseErrorJson(HttpServletResponse response, HttpStatus httpStatus, ResponseVO result) throws IOException {
        response.setStatus(httpStatus.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JsonUtil.toJSON(result));
    }

    public static void responseOkJson(HttpServletResponse response,Object resData,String message) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JsonUtil.toJSON(ResponseUtil.success(message,resData)));
    }
}
