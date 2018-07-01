package com.zj.bda.dgbschedule.config;

import com.zj.bda.dgbschedule.job.fixedjob.QuartzTest01;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

/**
 * @author Dongguabai
 * @date 2018-07-01 17:15
 */
@Configuration
public class QuartzConfig {

    /**
     * 将Job放入JobDetailFactoryBean
     * @return
     */
    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean(){
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        //关联我们定义的Job类
        jobDetailFactory.setJobClass(QuartzTest01.class);
        return jobDetailFactory;
    }

    @Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(JobDetailFactoryBean jobDetailFactory){
        SimpleTriggerFactoryBean simpleTriggerFactory = new SimpleTriggerFactoryBean();
        simpleTriggerFactory.setJobDetail(jobDetailFactory.getObject());
        simpleTriggerFactory.setRepeatInterval(2000);
        simpleTriggerFactory.setRepeatCount(20);
        return simpleTriggerFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(SimpleTriggerFactoryBean simpleTriggerFactory){
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setTriggers(simpleTriggerFactory.getObject());
        return schedulerFactory;
    }
}
