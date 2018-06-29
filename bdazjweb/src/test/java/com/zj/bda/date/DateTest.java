package com.zj.bda.date;

import com.zj.bda.common.unspecific.util.DateUtil;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Dongguabai on 2018-06-22 16:16
 */
public class DateTest {
    @Test
    public void test01(){
        LocalDate ld = LocalDate.now();
        System.out.println(ld);
        LocalDateTime lct1 = LocalDateTime.now();
        System.out.println(lct1);
        Date date = new Date();
        System.out.println(date);
    }

    @Test
    public void test02(){
        String currentDatetime = DateUtil.getCurrentDatetime(DateUtil.TimeFormat.LONG_DATE_PATTERN_SLASH);
        System.out.println(currentDatetime);
    }
}
