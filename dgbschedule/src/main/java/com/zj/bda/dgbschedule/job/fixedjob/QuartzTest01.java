package com.zj.bda.dgbschedule.job.fixedjob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author Dongguabai
 * @date 2018-07-01 10:48
 */
public class QuartzTest01 implements Job{
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
       // System.out.println("执行了Quartz-------" + LocalDateTime.now());
    }
}
