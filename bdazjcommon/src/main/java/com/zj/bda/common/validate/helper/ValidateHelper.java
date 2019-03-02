package com.zj.bda.common.validate.helper;

import com.zj.bda.common.exception.InvalidParameterException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

/**
 * @author Dongguabai
 * @date 2018-06-30 1:02
 * 主要是拥有校验model参数
 * 类似（@RequestBody @Valid User user,BindingResult result，@Valid User user,BindingResult result)即可
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidateHelper {

    public static void validate(BindingResult... results) {
        for (BindingResult result : results) {
            if (result != null && result.hasErrors()) {
                throw new InvalidParameterException(result.getAllErrors().get(0).getDefaultMessage());
            }
        }
    }

}
