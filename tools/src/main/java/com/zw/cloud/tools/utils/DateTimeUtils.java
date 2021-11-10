package com.zw.cloud.tools.utils;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class DateTimeUtils {

    public static void main(String[] args) {
        //获取秒数
        Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        System.out.println(second);
        //获取毫秒数
        Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println(milliSecond);
        Instant instant = Instant.ofEpochSecond(second);
        System.out.println(instant);
        System.out.println(instant.atZone(ZoneId.systemDefault()));
        System.out.println(ZoneId.systemDefault());
        System.out.println(Instant.parse("2021-11-03T20:37:30.00Z"));
        String date = "2017-03-08T12:30:54";
        LocalDateTime localdatetime = LocalDateTime.parse(date);
        System.out.println(localdatetime);

        LocalDateTime dateTime = LocalDateTime.of(localdatetime.getYear(), localdatetime.getMonth(), localdatetime.getDayOfMonth(), Integer.parseInt("09"), Integer.parseInt("59"), 0);
        System.out.println(dateTime);
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.toLocalDate();
    }

    public static Date localDateTimeToUDate(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date localDateToUDate(LocalDate localDate) {
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date minusMonths(int num) {
        LocalDate localDate =  LocalDate.now().minusMonths(num);
        return localDateToUDate(localDate);
    }

    public static Date plusMonths(int num) {
        LocalDate localDate =  LocalDate.now().plusMonths(num);
        return localDateToUDate(localDate);
    }

    public static Date getFirstDayOfMonth(LocalDate start){
        LocalDate firstday = start.with(TemporalAdjusters.firstDayOfMonth());
        Date date = localDateToUDate(firstday);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        return cal.getTime();
    }

    public static Date getLastDayOfMonth(LocalDate localDate){
        LocalDate lastTheMonthDay = localDate.with(TemporalAdjusters.lastDayOfMonth());
        Date date = localDateToUDate(lastTheMonthDay);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,59);
        return cal.getTime();
    }

    public static Date getTodayStart(LocalDate localDate){
        LocalDateTime todayStart = LocalDateTime.of(localDate, LocalTime.MIN);
        return localDateTimeToUDate(todayStart);
    }

    public static Date getTodayEnd(LocalDate localDate){
        LocalDateTime todayEnd = LocalDateTime.of(localDate, LocalTime.MAX);
        return localDateTimeToUDate(todayEnd);
    }

    public static Date getTodayStart(){
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        return cal.getTime();
    }

    public static Date getTodayEnd(){
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,59);
        return cal.getTime();
    }

    public static Date getBeforeDayStart(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        return cal.getTime();
    }

    public static Date getAfterDayEnd(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, +1);
        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,59);
        return cal.getTime();
    }

    //获取本周的开始时间
    public static Date getBeginDayOfWeek() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            dayOfWeek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayOfWeek);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        return cal.getTime();
    }

    //获取本周的结束时间
    public static Date getEndDayOfWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,59);
        return cal.getTime();

    }


    // 查询时间段每天开始时间
    public static List<Date> findDatesBegin(Date start, Date end) {
        List<Date> dateList = new ArrayList<>();
        dateList.add(start);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(start);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(start);
        // 测试此日期是否在指定日期之后
        while (end.after(calBegin.getTime()))  {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            dateList.add(calBegin.getTime());
        }
        return dateList;
    }


    public static Date getStartOrEndDayOfQuarter(LocalDate today, Boolean isFirst){
        LocalDate resDate = LocalDate.now();
        if (today == null) {
            today = resDate;
        }
        Month month = today.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();
        Month endMonthOfQuarter = Month.of(firstMonthOfQuarter.getValue() + 2);
        if (isFirst) {
            resDate = LocalDate.of(today.getYear(), firstMonthOfQuarter, 1);
        } else {
            resDate = LocalDate.of(today.getYear(), endMonthOfQuarter, endMonthOfQuarter.length(today.isLeapYear()));
        }
        Instant instant = resDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date getStartOrEndDayOfYear(LocalDate today, Boolean isFirst){
        LocalDate resDate = LocalDate.now();
        if (today == null) {
            today = resDate;
        }
        if (isFirst) {
            resDate = LocalDate.of(today.getYear(), Month.JANUARY, 1);
        } else {
            resDate = LocalDate.of(today.getYear(), Month.DECEMBER, Month.DECEMBER.length(today.isLeapYear()));
        }
        Instant instant = resDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**获取两个时间节点之间的月份列表**/
    public static List<String> getMonthBetween(Date start, Date endTime){
        ArrayList<String> result = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String lastMonth = sdf.format(endTime);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        while (calendar.getTime().before(endTime)) {
            result.add(sdf.format(calendar.getTime()));
            calendar.add(Calendar.MONTH, 1);
        }
        if (result.contains(lastMonth)){
            return result;
        }
        result.add(lastMonth);
        return result;
    }

    /**
     * 计算两个日期之间间隔的天数
     * @param start 开始日期
     * @param end 结束日期
     * @return 返回天数
     */
    public static int getTheNumberOfDaysBetweenTwoDates(Date start, Date end) {
        Preconditions.checkArgument(start != null && end != null, "start and end is not null");
        LocalDate startDate = dateToLocalDate(start);
        LocalDate endDate = dateToLocalDate(end);
        return (int) (endDate.toEpochDay() - startDate.toEpochDay());
    }

    /**
     * 计算两个日期之间指定间隔[分钟]的日期
     */
    public static List<Date> getDayListBetweenTwoDates(Date start,Date end,int cycle) {
        List<Date> dateList = new ArrayList<>();
        Date date = start;
        while (date.before(end) || date.equals(end)) {
            // 开始时间不统计在内
            if (!date.equals(start)) {
                dateList.add(date);
            }
            date = new Date(date.getTime() + 1000 * 60 * cycle);
        }
        return dateList;
    }

    public static String parseTime(long time){
        if (time < 3600000) {
            //这里想要只保留分秒可以写成"mm:ss"
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            //这里很重要，如果不设置时区的话，输出结果就会是几点钟，而不是毫秒值对应的时分秒数量了。
            formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
            String hms = formatter.format(time);
            System.out.println(hms);
            return hms;
        }
        long house = time / 3600000;
        long min = time % 3600000 / 60000;
        long second = time % 3600000 % 60000 / 1000;
        return String.format("%02d",house) + ":" + String.format("%02d",min) + ":" + String.format("%02d",second);
    }

}
