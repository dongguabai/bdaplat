package com.zj.bda.persistence.mapper;

import com.zj.bda.persistence.entity.UnStrTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Dongguabai
 * @date 2018/7/31 22:19
 */
public interface IdCardMapper {

     //int checkIdCard(@Param("idCard") String idCard);


     List<UnStrTag> checkIdCardSelect(@Param("idCard") String idCard);


}
