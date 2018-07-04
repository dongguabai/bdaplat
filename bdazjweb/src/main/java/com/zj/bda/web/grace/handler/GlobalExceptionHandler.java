package com.zj.bda.web.grace.handler;

import com.google.common.base.Joiner;
import com.zj.bda.common.exception.*;
import com.zj.bda.common.unspecific.util.CusStringUtil;
import com.zj.bda.common.web.constant.enums.ResponseEnum;
import com.zj.bda.common.web.helper.ResponseHelper;
import com.zj.bda.common.web.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * Created by Dongguabai on 2018-06-10.
 * 可优化，但是要考虑Customer问题
 */
@ControllerAdvice(annotations = {Controller.class})
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NoPermissionException.class)
    @ResponseBody
    public ResponseVO handler(NoPermissionException e) {
        return responseError(ResponseEnum.ERROR_NO_PERMISSION,e);
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseBody
    public ResponseVO handler(NotFoundException e) {
        return responseError(ResponseEnum.ERROR_NOT_FOUND,e);
    }

    @ExceptionHandler(value = UnLoginException.class)
    @ResponseBody
    public ResponseVO handler(UnLoginException e) {
        return responseError(ResponseEnum.ERROR_UNLOGIN,e);
    }

    @ExceptionHandler(value = LimitedOperationException.class)
    @ResponseBody
    public ResponseVO handler(LimitedOperationException e) {
        return responseError(ResponseEnum.ERROR_LIMITED_OPERATION,e);
    }

    @ExceptionHandler(value = RequirementException.class)
    @ResponseBody
    public ResponseVO handler(RequirementException e) {
        return responseError(ResponseEnum.ERROR_REQUIREMENT,e,appendErrorMessage(ResponseEnum.ERROR_REQUIREMENT,e.getMessage()));
    }

    /**
     * 非法参数
     * @param e
     * @return
     */
    @ExceptionHandler(value = InvalidParameterException.class)
    @ResponseBody
    public ResponseVO handler(InvalidParameterException e) {
        return responseError(ResponseEnum.ERROR_INVALID,e,appendErrorMessage(ResponseEnum.ERROR_INVALID,e.getMessage()));
    }

    /**
     * 非法参数
     * @param e
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public ResponseVO handler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        return responseError(ResponseEnum.ERROR_INVALID,e,appendErrorMessage(ResponseEnum.ERROR_INVALID,violations.iterator().next().getMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseVO defaultErrorHandler(HttpServletRequest request, Exception e) {
        log.error(String.format("请求方法[%s]发生异常，错误信息：[%s]", request.getRequestURI(), e.getMessage()), e);
        return ResponseHelper.error(ResponseEnum.ERROR_UNKNOW);
    }

    private ResponseVO responseError(ResponseEnum re,RuntimeException e){
        log.error(re.getMessage(), e);
        return ResponseHelper.error(re);
    }

    private ResponseVO responseError(ResponseEnum re,RuntimeException e,String cusErrorMessage){
        log.error(cusErrorMessage, e);
        return ResponseHelper.error(re.getCode(),cusErrorMessage);
    }

    private String appendErrorMessage(ResponseEnum re, String cusErrorMessage) {
        return Joiner.on("").join(re.getMessage(), CusStringUtil.ifNullReturnEmpty(cusErrorMessage), "！");
    }
}
