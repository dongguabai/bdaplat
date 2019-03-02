package wm.dgb.schedule.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Dongguabai
 * @date 2018/8/5 11:28
 */
@Component
public class ScheduledTest {

    @Scheduled(cron = "0/3 * * * * ?")
    public void test01(){
       // System.out.println("-===触发了");
    }
}
