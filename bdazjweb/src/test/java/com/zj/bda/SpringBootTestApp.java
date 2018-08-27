package com.zj.bda;

import com.zj.bda.common.init.IInitExpand;
import com.zj.bda.common.util.JsonUtil;
import com.zj.bda.common.util.SpringUtil;
import com.zj.bda.persistence.entity.UnStrTag;
import com.zj.bda.persistence.mapper.IdCardMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wm.dgb.security.grace.user.DgbSecurityUserHelper;

import java.util.List;
import java.util.Map;

/**
 * @author Dongguabai
 * @date 2018-06-30 15:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootTestApp {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testTestMapper(){
        IdCardMapper idCardMapper = sqlSessionFactory.openSession().getMapper(IdCardMapper.class);
        List<UnStrTag> unStrTags = idCardMapper.checkIdCardSelect("123456789");
    }

    @Autowired(required = false)
    private Map<String,IInitExpand> map;

    @Test
    public void test01(){

//        map.forEach((k,v)->{
//            System.out.println("key:"+k+":value:"+v);
//        });

    }
    @Test
    public void test03(){
       Object currentPrincipal = DgbSecurityUserHelper.getCurrentPrincipal();
        String s = JsonUtil.toJSON(currentPrincipal);
        System.out.println(s);
    }

    @Test
    public void test02(){
        Map<String, IInitExpand> beansOfType = SpringUtil.getBeansOfType(IInitExpand.class);
        beansOfType.forEach((k,v)->{
            System.out.println("key:"+k+":value:"+v);
        });

    }
}
