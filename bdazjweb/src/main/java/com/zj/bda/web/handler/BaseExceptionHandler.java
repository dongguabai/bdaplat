package com.zj.bda.web.handler;

import com.google.common.base.Joiner;
import com.zj.bda.web.enums.ResultEnum;
import com.zj.bda.web.exception.NoPermissionException;
import com.zj.bda.web.exception.NotFoundException;
import com.zj.bda.web.exception.UnLoginException;
import com.zj.bda.web.result.OperaterResultUtil;
import com.zj.bda.web.result.vo.OperaterResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 *
 * Created by Dongguabai on 2018-06-10.
 */
@ControllerAdvice(annotations = {Controller.class})
public class BaseExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);

    @ExceptionHandler(value = NoPermissionException.class)
    @ResponseBody
    public OperaterResultVO handler(NoPermissionException e) {
        logger.error(ResultEnum.ERROR_NO_PERMISSION.getMessage(), e);
        return OperaterResultUtil.error(ResultEnum.ERROR_NO_PERMISSION);
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseBody
    public OperaterResultVO handler(NotFoundException e) {
        logger.error(ResultEnum.ERROR_NOT_FOUND.getMessage(), e);
        return OperaterResultUtil.error(ResultEnum.ERROR_NOT_FOUND);
    }

    @ExceptionHandler(value = UnLoginException.class)
    @ResponseBody
    public OperaterResultVO handler(UnLoginException e) {
        logger.error(ResultEnum.ERROR_UNLOGIN.getMessage(), e);
        return OperaterResultUtil.error(ResultEnum.ERROR_UNLOGIN);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public OperaterResultVO handler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        System.out.println(violations.iterator().next().getMessage());
        String errorMessage=Joiner.on("").join(ResultEnum.ERROR_INVALID.getMessage(),violations.iterator().next().getMessage(),"！");
        logger.error(errorMessage, e);
        return OperaterResultUtil.error(ResultEnum.ERROR_INVALID.getCode(),errorMessage);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public OperaterResultVO defaultErrorHandler(HttpServletRequest request, Exception e) {
        logger.error(String.format("请求方法[%s]发生异常，错误信息：[%s]",request.getRequestURI(), e.getMessage()), e);
        return OperaterResultUtil.error(ResultEnum.ERROR_UNKNOW);
    }
}
