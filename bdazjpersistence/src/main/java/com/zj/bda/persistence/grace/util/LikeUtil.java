package com.zj.bda.persistence.grace.util;

import com.google.common.base.Joiner;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Dongguabai
 * @date 2018-07-18 15:12
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LikeUtil {

    public static String leftLike(Object value){
        return value==null?"":Joiner.on("").join("%",value);
    }

    public static String rightLike(Object value){
        return value==null?"":Joiner.on("").join(value,"%");
    }

    public static String leftAndRightLike(Object value){
        return value==null?"":Joiner.on("").join("%",value,"%");
    }

}
