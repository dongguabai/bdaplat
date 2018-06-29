package com.zj.bda.common.verification.util;

import com.zj.bda.common.exception.InvalidParameterException;
import org.springframework.validation.BindingResult;

/**
 * @author Dongguabai
 * @date 2018-06-30 1:02
 * 主要是拥有校验model参数
 * 类似（@RequestBody @Valid User user,BindingResult result，@Valid User user,BindingResult result)即可
 */
public class ValidateUtil {

    public static void validateModel(BindingResult result){
        if (result!=null && result.hasErrors()){
            throw new InvalidParameterException(result.getAllErrors().get(0).getDefaultMessage());
        }
    }

    private ValidateUtil() {
    }
}
