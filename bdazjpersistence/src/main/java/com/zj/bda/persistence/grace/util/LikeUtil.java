package com.zj.bda.persistence.grace.util;

import com.google.common.base.Joiner;
import com.zj.bda.common.exception.InvalidParameterException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 拼接like查询sql工具类
 * @author Dongguabai
 * @date 2018-07-18 15:12
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LikeUtil {

    public static String leftLike(Object value){
        if (value==null){
            throw new InvalidParameterException("模糊查询参数不能为空！");
        }
        return Joiner.on("").join("%",value);
    }

    public static String rightLike(Object value){
        if (value==null){
            throw new InvalidParameterException("模糊查询参数不能为空！");
        }
        return Joiner.on("").join(value,"%");
    }

    public static String leftAndRightLike(Object value){
        if (value==null){
            throw new InvalidParameterException("模糊查询参数不能为空！");
        }
        return Joiner.on("").join("%",value,"%");
    }

}
