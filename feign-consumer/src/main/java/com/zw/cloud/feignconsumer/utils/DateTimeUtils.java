package com.zw.cloud.feignconsumer.utils;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
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
}
