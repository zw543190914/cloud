package com.zw.cloud.tools.utils;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

@Service
public class DateTimeUtils {
    public LocalDateTime UDateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public LocalDate UDateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.toLocalDate();
    }

    public Date LocalDateTimeToUDate(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public Date LocalDateToUDate(LocalDate localDate) {
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public Date minusMonths(int num) {
        LocalDate localDate =  LocalDate.now().minusMonths(num);
        return LocalDateToUDate(localDate);
    }

    public Date plusMonths(int num) {
        LocalDate localDate =  LocalDate.now().plusMonths(num);
        return LocalDateToUDate(localDate);
    }

    public Date getFirstDayOfMonth(LocalDate start){
        LocalDate firstday = start.with(TemporalAdjusters.firstDayOfMonth());
        return LocalDateToUDate(firstday);
    }

    public Date getLastDayOfMonth(LocalDate localDate){
        LocalDate lastTheMonthDay = localDate.with(TemporalAdjusters.lastDayOfMonth());
        return LocalDateToUDate(lastTheMonthDay);
    }

    public Date getTodayStart(LocalDate localDate){
        LocalDateTime todayStart = LocalDateTime.of(localDate, LocalTime.MIN);
        return LocalDateTimeToUDate(todayStart);
    }

    public Date getTodayEnd(LocalDate localDate){
        LocalDateTime todayEnd = LocalDateTime.of(localDate, LocalTime.MAX);
        return LocalDateTimeToUDate(todayEnd);
    }

    //获取本周的开始时间
    public Date getBeginDayOfWeek() {
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
    public Date getEndDayOfWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,59);
        return cal.getTime();

    }
}
