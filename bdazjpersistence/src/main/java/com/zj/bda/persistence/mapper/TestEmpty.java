package com.zj.bda.persistence.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author Dongguabai
 * @date 2018/10/8 10:18
 */
public interface TestEmpty {

    String test1(@Param("keyWord") String keyWord);

    String test2(@Param("keyWord") String keyWord);
}
