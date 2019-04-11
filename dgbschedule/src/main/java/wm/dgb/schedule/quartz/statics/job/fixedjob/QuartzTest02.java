package wm.dgb.schedule.quartz.statics.job.fixedjob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;

/**
 * @author Dongguabai
 * @date 2018-07-01 10:48
 */
public class QuartzTest02 implements Job{
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
      // System.out.println("执行了QuartzTest02-------" + LocalDateTime.now());
    }
}
