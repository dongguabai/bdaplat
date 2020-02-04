package com.zj.bda.common.util;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Date;

/**
 * @author 魏民
 * @Description 查看聊天发布多长时间
 * @Date 创建于 2019-07-24 09:44
 */
public class DateUtil5 {

    public static String[] timePasted(Date date){
        if(date == null)return null;
        Date d1 = new Date();
        long[] times = getDistanceTimes(d1, date);
        String[] rtv = new String[2];
        if(times[0] > 0){
            rtv[0] =  String.valueOf(times[0]);
            rtv[1] =  "月前";
            return rtv;
        }
        if(times[1] > 0){
            rtv[0] =  String.valueOf(times[1]);
            rtv[1] =  "天前";
            return rtv;
        }
        if(times[2] > 0){
            rtv[0] =  String.valueOf(times[2]);
            rtv[1] =  "小时前";
            return rtv;
        }
        if(times[3] > 0){
            rtv[0] =  String.valueOf(times[3]);
            rtv[1] =  "分钟前";
            return rtv;
        }
        if(times[4] > 0){
            rtv[0] =  String.valueOf(times[4]);
            rtv[1] =  "秒前";
            return rtv;
        }

        rtv[0] = "1";
        rtv[1] =  "秒前";

        return rtv;
    }

    /**
     * 两个时间相差距离多少月多少天多少小时多少分多少秒
     * @return long[] 返回值为：{月,天, 时, 分, 秒}
     */
    public static long[] getDistanceTimes(Date d1, Date d2) {
        if (!ObjectUtils.allNotNull(d1, d2)) {
            throw new IllegalArgumentException("params can not be empty.");
        }
        long month = 0;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;

        long time1 = d1.getTime();
        long time2 = d2.getTime();
        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        month = diff / (30 * 24 * 60 * 60 * 1000L);
        day = diff / (24 * 60 * 60 * 1000) - month * 30;
        hour = (diff / (60 * 60 * 1000) - month * 30 * 24 - day * 24);
        min = ((diff / (60 * 1000)) - month * 30 * 24 * 60 - day * 24 * 60 - hour * 60);
        sec = (diff / 1000 - month * 30 * 24 * 60 * 60 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

        long[] times = {month, day, hour, min, sec};
        return times;
    }
}
