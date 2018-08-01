package com.zj.bda.persistence.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author Dongguabai
 * @date 2018/7/31 22:19
 */
public interface IdCardMapper {

     int checkIdCard(@Param("idCard") String idCard);


     int checkIdCardSelect(@Param("idCard") String idCard);


}
