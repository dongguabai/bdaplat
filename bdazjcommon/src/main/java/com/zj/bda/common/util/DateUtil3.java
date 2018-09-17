package com.zj.bda.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期加减
 *     cd.add(Calendar.DATE, 1);//增加一天
 //cal.add(Calendar.DATE, -1);      //减一天
 //cd.add(Calendar.MONTH, 1);//增加一月
 */

/**
 * @author Dongguabai
 * @date 2018/8/21 16:31
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil3 {

    private static final SimpleDateFormat FORMAT_1 = new SimpleDateFormat("yyyy-MM-dd");
    private static final String DATE_FMT_1 = "yyyy-MM-dd";

    /**
     * 获取上月第一天
     * @return
     */
    public synchronized static String getThisMonth1Day() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return FORMAT_1.format(cal.getTime());
    }

    /**
     * 获取本月最后一天
     * @return
     */
    public synchronized static String getLastMonthLastDay() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DATE, -1);//减少一天
        return FORMAT_1.format(cal.getTime());
    }

    public static void main(String[] args) {
        System.out.println(getLastMonth1Day());
    }

    /**
     * 获取本个月第一天
     * @return
     */
    public synchronized static String getLastMonth1Day() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return FORMAT_1.format(c.getTime());
    }


    /**
     * 获取上周的今天
     * @return
     */
    public static String getNowLastWeek(){
        return formatLocalDateTimeToString( LocalDate.now().plus(-1, ChronoUnit.WEEKS),DATE_FMT_1);
    }

    public static String getNowLastWeekNextDay(){
        return formatLocalDateTimeToString( LocalDate.now().plus(-6, ChronoUnit.DAYS),DATE_FMT_1);
    }

    public static String formatLocalDateTimeToString(LocalDate localDate, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDate.format(formatter);
    }

    /**
     * 获取本周周一
     * @param date
     * @return
     */
    public static Date getTheWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    /**
     * 获取上周周一
     * @param date
     * @return
     */
    public static Date getTheWeekLastWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTheWeekMonday(date));
        cal.add(Calendar.DATE, -7);
        return cal.getTime();
    }

    /**
     * 获取上个月
     * @return
     */
    public static LocalDate getLastMonth(){
        return LocalDate.now().plus(-1,ChronoUnit.MONTHS);
    }

    /**
     * 获取某月天数
     * @param localDate
     * @return
     */
    public static int getTheMonthDays(LocalDate localDate){
        return localDate.lengthOfMonth();
    }

    /**
     * 当前月第一天
     *
     * @return 当前月第一天
     */
    public String getThisMonth() {
// 获取前月的第一天
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        String firstDay = format.format(calendar.getTime());
        return firstDay;
    }

    /**
     * 日期是本月的第几天
     *
     * @param date
     *            天
     * @return 当前天数
     * @throws ParseException
     *             数据转换异常
     */
    public static String getMostDay(String date) throws ParseException {
        // 当天日期是本月的第几天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDay = format.parse(date);
        Calendar ca = Calendar.getInstance();
        ca.setTime(nowDay);
        int num = ca.get(Calendar.DAY_OF_MONTH);
        String time = String.valueOf(num);
        return time;
    }

    /**
     * 获取日期在当月天数
     *
     * @param date
     *            天
     * @return 当月天数
     * @throws Exception
     *             异常
     */
    public static String getMonthDay(String date) throws Exception {
        // 当前月最大日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dates = sdf.parse(date);// String-->Date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dates);// 设置日历时间
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        String mostDate = String.valueOf(day);
        return mostDate;
    }

    /**
     * 当年第一天
     *
     * @param date
     *            日期
     * @return 天
     * @throws Exception
     *             异常
     */
    public static String getThisYear(String date) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format3 = new SimpleDateFormat("yyyy");
        Date time = format3.parse(date);
        String time1 = format3.format(time);
        Date startTime = format.parse(time1 + "-01-01");
        String dates = format.format(startTime);
        return dates;
    }

    /**
     * 当月第一天
     *
     * @param date
     *            天
     * @return 天
     * @throws ParseException
     *             异常
     */
    public static String getFirstDayToMonth(String date) throws ParseException {
        // 获取截止当前天数
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date nowDay = format.parse(date);
        String time = format.format(nowDay);
        String firstDay = time + "-01";
        return firstDay;
    }

    /**
     * 当年第一天
     *
     * @param date
     *            天
     * @return 天
     * @throws ParseException
     *             异常
     */
    public static String getFirstDay(String date) throws ParseException {
        // 获取截止当前天数
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        Date nowDay = format.parse(date);
        String time = format.format(nowDay);
        String firstDay = time + "-01-01";
        return firstDay;
    }

    /**
     * 获取给定日去年同期
     *
     * @param date
     *            天
     * @return 天
     * @throws ParseException
     *             异常
     */
    public static String getToLastYearDay(String date) throws Exception {
        // 获取给定日起过去15天
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date dateTemp = sdf.parse(date);
        cal.setTime(dateTemp);
        cal.add(Calendar.YEAR, -1);
        Date dates = cal.getTime();
        String toLastYearDay = sdf.format(dates);
        return toLastYearDay;
    }

    /**
     * 获取今年第一天
     * @return
     */
    public static String getThisYearFirstDay() {
        return formatLocalDateTimeToString(LocalDate.now(), "yyyy") + "-01-01";
    }

    /**
     * 获取今年一月份
     * @return
     */
    public static String getThisJanuary() {
        return formatLocalDateTimeToString(LocalDate.now(), "yyyy") + "-01";
    }

    /**
     * 获取LocalDate的周一
     */
    public static LocalDate getMonday(LocalDate localDate){
        return localDate.with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1);
    }

    /**
     * 获取LocalDate的周一
     */
    public static String getParseMonday(LocalDate localDate){
        return formatLocalDateTimeToString(getMonday(localDate),DATE_FMT_1);
    }


    /**
     * 获取上周周几的日期
     * @param dayOfWeek   周日是1
     * @param weekOffset  周偏移，上周为-1，本周为0，下周为1，以此类推
     * @return
     */
    public static Date getDayOfWeek(int dayOfWeek,int weekOffset){
        return getDayOfWeek(Calendar.MONDAY,dayOfWeek,weekOffset);
    }

    /**
     * 获取上(下)周周几的日期
     * @param firstDayOfWeek {@link Calendar}
     * 值范围 <code>SUNDAY</code>,
     * <code>MONDAY</code>, <code>TUESDAY</code>, <code>WEDNESDAY</code>,
     * <code>THURSDAY</code>, <code>FRIDAY</code>, and <code>SATURDAY</code>
     * @param dayOfWeek {@link Calendar}
     * @param weekOffset  周偏移，上周为-1，本周为0，下周为1，以此类推
     * @return
     */
    public static Date getDayOfWeek(int firstDayOfWeek,int dayOfWeek,int weekOffset){
        if(dayOfWeek>Calendar.SATURDAY || dayOfWeek<Calendar.SUNDAY){
            return null;
        }
        if(firstDayOfWeek>Calendar.SATURDAY || firstDayOfWeek < Calendar.SUNDAY){
            return null;
        }
        Calendar date=Calendar.getInstance(Locale.CHINA);
        date.setFirstDayOfWeek(firstDayOfWeek);
        //周数减一，即上周
        date.add(Calendar.WEEK_OF_MONTH,weekOffset);
        //日子设为周几
        date.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        //时分秒全部置0
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        return date.getTime();
    }
}
