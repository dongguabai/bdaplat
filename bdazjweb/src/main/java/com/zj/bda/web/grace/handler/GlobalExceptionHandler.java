package com.zj.bda.web.grace.handler;

import com.google.common.base.Joiner;
import com.zj.bda.common.exception.InvalidParameterException;
import com.zj.bda.common.exception.LimitedOperationException;
import com.zj.bda.common.exception.NotFoundException;
import com.zj.bda.common.util.StringUtil;
import com.zj.bda.common.web.ServerResponseEnum;
import com.zj.bda.common.web.ServerResponseHelper;
import com.zj.bda.common.web.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import wm.dgb.security.grace.exception.NoPermissionException;
import wm.dgb.security.grace.exception.UnLoginException;
import wm.dgb.security.support.verificationcode.grace.exception.VerificationCodeException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
/**
 * @author Dongguabai
 * @date 2018-07-01 13:30
 * 可优化，但是要考虑Customer问题
 */
@ControllerAdvice(annotations = {Controller.class})
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 权限不足
     * @param e
     * @return
     */
    @ExceptionHandler(value = NoPermissionException.class)
    @ResponseBody
    public ServerResponse handler(NoPermissionException e) {
        return responseError(ServerResponseEnum.ERROR_NO_PERMISSION,e);
    }

    /**
     * 路径无法匹配
     * @param e
     * @return
     */
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseBody
    public ServerResponse handler(NotFoundException e) {
        return responseError(ServerResponseEnum.ERROR_NOT_FOUND,e);
    }

    /**
     * 未登陆
     * @param e
     * @return
     */
    @ExceptionHandler(value = UnLoginException.class)
    @ResponseBody
    public ServerResponse handler(UnLoginException e) {
        return responseError(ServerResponseEnum.ERROR_UNLOGIN,e);
    }

    /**
     * 操作受限（如表单重复提交）
     * @param e
     * @return
     */
    @ExceptionHandler(value = LimitedOperationException.class)
    @ResponseBody
    public ServerResponse handler(LimitedOperationException e) {
        return responseError(ServerResponseEnum.ERROR_LIMITED_OPERATION,e);
    }

    /**
     * 验证码异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = VerificationCodeException.class)
    @ResponseBody
    public ServerResponse handler(VerificationCodeException e) {
        return responseError(ServerResponseEnum.ERROR_VERIFICATION_CODE,e,appendErrorMessage(ServerResponseEnum.ERROR_VERIFICATION_CODE,e.getMessage()));
    }
    /**
     * 非法参数
     * @param e
     * @return
     */
    @ExceptionHandler(value = InvalidParameterException.class)
    @ResponseBody
    public ServerResponse handler(InvalidParameterException e) {
        return responseError(ServerResponseEnum.ERROR_INVALID,e,appendErrorMessage(ServerResponseEnum.ERROR_INVALID,e.getMessage()));
    }

    /**
     * 非法参数
     * @param e
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public ServerResponse handler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        return responseError(ServerResponseEnum.ERROR_INVALID,e,appendErrorMessage(ServerResponseEnum.ERROR_INVALID,violations.iterator().next().getMessage()));
    }

    /**
     * 默认异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ServerResponse defaultErrorHandler(HttpServletRequest request, Exception e) {
        log.error(String.format("请求方法[%s]发生异常，错误信息：[%s]", request.getRequestURI(), e.getMessage()), e);
        return ServerResponseHelper.error(ServerResponseEnum.ERROR_UNKNOW);
    }

    private ServerResponse responseError(ServerResponseEnum re, RuntimeException e){
        log.error(re.getMessage(), e);
        return ServerResponseHelper.error(re);
    }

    private ServerResponse responseError(ServerResponseEnum re, RuntimeException e, String cusErrorMessage){
        log.error(cusErrorMessage, e);
        return ServerResponseHelper.error(re.getCode(),cusErrorMessage);
    }

    private String appendErrorMessage(ServerResponseEnum re, String cusErrorMessage) {
        return Joiner.on("").join(re.getMessage(), StringUtil.ifNullReturnEmpty(cusErrorMessage), "！");
    }
}
