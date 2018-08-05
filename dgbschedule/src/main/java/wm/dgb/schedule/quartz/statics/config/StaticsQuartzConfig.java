package wm.dgb.schedule.quartz.statics.config;

import wm.dgb.schedule.quartz.statics.job.fixedjob.QuartzTest01;
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
public class StaticsQuartzConfig {

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

    /**
     * 设置SimpleTrigger
     * @param jobDetailFactory
     * @return
     */
    @Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(JobDetailFactoryBean jobDetailFactory){
        SimpleTriggerFactoryBean simpleTriggerFactory = new SimpleTriggerFactoryBean();
        simpleTriggerFactory.setJobDetail(jobDetailFactory.getObject());
        //设置间隔时间
        simpleTriggerFactory.setRepeatInterval(2000);
        //设置重复次数
        simpleTriggerFactory.setRepeatCount(20);
        return simpleTriggerFactory;
    }

    /**
     * 将SimpleTrigger加入SchedulerFactoryBean
     * @param simpleTriggerFactory
     * @return
     */
   @Bean
    public SchedulerFactoryBean schedulerFactoryBean(SimpleTriggerFactoryBean simpleTriggerFactory){
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setTriggers(simpleTriggerFactory.getObject());
        return schedulerFactory;
    }

/*    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean){
        CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
        factory.setJobDetail(jobDetailFactoryBean.getObject());
        //设置触发时间，每3秒执行一次
        factory.setCronExpression("0/3 * * * * ?");
        return factory;
    }

    *//**
     * 3.创建Scheduler对象
     *//*
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(CronTriggerFactoryBean cronTriggerFactoryBean){
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        //关联trigger
        factory.setTriggers(cronTriggerFactoryBean.getObject());

        return factory;
    }*/

}
