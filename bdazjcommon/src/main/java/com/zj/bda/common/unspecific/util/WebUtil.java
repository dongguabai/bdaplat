package com.zj.bda.common.unspecific.util;

import com.zj.bda.common.web.helper.ResponseHelper;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Dongguabai
 * @date 2018-07-05 20:24
 */
@NoArgsConstructor
public class WebUtil {

    public static void responseErrorJson(HttpServletResponse response,Object resData,HttpStatus httpStatus,String message,Integer errorCode) throws IOException {
        response.setStatus(httpStatus.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JsonUtil.toJSON(ResponseHelper.error(errorCode,message,resData)));
    }

    public static void responseOkJson(HttpServletResponse response,Object resData,String message) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JsonUtil.toJSON(ResponseHelper.success(message,resData)));
    }
}
