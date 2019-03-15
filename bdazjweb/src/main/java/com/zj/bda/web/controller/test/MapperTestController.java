package com.zj.bda.web.controller.test;

import com.zj.bda.persistence.entity.UserAccount;
import com.zj.bda.persistence.mapper.UserAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

/**
 * @author dongguabai
 * @date 2019-03-14 22:21
 */
@RestController
public class MapperTestController {

    @Autowired
    private UserAccountMapper userAccountMapper;

    @RequestMapping("/mapper")
    private Object select(@RequestParam("id") Integer id){
        Example example = new Example(UserAccount.class);
        example.createCriteria().andEqualTo("id",id);
        UserAccount userAccount = userAccountMapper.selectOneByExample(example);
        return userAccount;
    }

}
