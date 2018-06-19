package com.zj.bda.service;

import com.zj.bda.persistence.mapper.UnStrTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dongguabai on 2018-06-19 11:15
 */
@Service
@Transactional
public class TestService {

    @Autowired
    private UnStrTagMapper unStrTagMapper;

    public void testTrans(){
        int i = unStrTagMapper.deleteByPrimaryKey(31);
        int o = 1/0;
        System.out.println(i);
    }
}
