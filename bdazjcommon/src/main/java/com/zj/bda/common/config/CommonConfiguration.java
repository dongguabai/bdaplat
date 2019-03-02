package com.zj.bda.common.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dongguabai
 * @date 2018/10/3 9:18
 */
@Configuration
public class CommonConfiguration {
    /**
     * 执行实体-DTO转换的主要类库——ModelMapper。
     *  搜索Spring REST API实体和DTO之间的转换
     * @return
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
