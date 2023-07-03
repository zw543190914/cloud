package com.zw.cloud.common.utils;

import com.alibaba.fastjson2.JSON;
import com.zw.cloud.common.entity.dto.LocalDateDTO;
import com.zw.cloud.common.entity.dto.LocalDateTimeDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class DateTimeUtils {

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String MONTH_PATTERN = "yyyy-MM";

    public static void main(String[] args) throws Exception{
        //获取秒数
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        Long second = now.toEpochSecond(ZoneOffset.of("+8"));
        System.out.println(second);
        //获取毫秒数
        Long milliSecond = now.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println("=========毫秒数===========");
        System.out.println(milliSecond);
        System.out.println(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println("=========毫秒数转为LocalDateTime===========");
        System.out.println(LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSecond), ZoneId.systemDefault()));

        System.out.println("=========LocalDateTime 转为 Instant===========");
        System.out.println(now.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("=========Instant 转为 LocalDateTime===========");
        System.out.println(LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSecond), ZoneId.of("UTC+8")));
        System.out.println("=========Instant===========");
        Instant instant = Instant.ofEpochSecond(second);
        System.out.println(instant);
        System.out.println(instant.atZone(ZoneId.systemDefault()));
        System.out.println(ZoneId.systemDefault());
        System.out.println(instant.toEpochMilli());
        System.out.println(Instant.parse("2021-11-03T20:37:30.00Z"));
        String date = "2017-03-08T12:30:54";
        LocalDateTime localdatetime = LocalDateTime.parse(date);
        System.out.println("localdatetime:" + localdatetime);

        LocalDateTime dateTime = LocalDateTime.of(localdatetime.getYear(), localdatetime.getMonth(), localdatetime.getDayOfMonth(), Integer.parseInt("09"), Integer.parseInt("59"), 0);
        System.out.println(dateTime);
        LocalDateTime localDateTime = now.minusHours(7);
        Duration between = Duration.between(localDateTime, now);
        System.out.println(between.getSeconds());

        System.out.println(getNumOfMonth("2021-02"));
        System.out.println("localdatetime2:" + LocalDateTime.now().toString());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String localDateTimeStr = dateTimeFormatter.format(LocalDateTime.now());
        System.out.println("localDateTimeStr==" + localDateTimeStr);
        System.out.println(LocalDateTime.parse(localDateTimeStr,dateTimeFormatter));

        System.out.println(3600 * 24 * 30 * 3);
        String start = "2022-05-18T23:59:00";
        LocalDateTime startTime = LocalDateTime.parse(start);
        String end = "2022-05-19T23:59:00";
        LocalDateTime endTime = LocalDateTime.parse(end);
        String config = "2022-05-19T23:50:00";
        LocalDateTime configTime = LocalDateTime.parse(config);
        List<LocalDateTimeDTO> localDateTimeDTOS = calBetweenTime(startTime, endTime, configTime);
        System.out.println("根据指定时间 切分时间:" + JSON.toJSONString(localDateTimeDTOS));
        System.out.println(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
        System.out.println(LocalDateTime.ofInstant(Instant.ofEpochMilli(1656915925000L), ZoneId.systemDefault()));
        Instant instant1 = Instant.ofEpochSecond(System.currentTimeMillis()/1000);
        System.out.println(instant1);

        System.out.println("=========时间间隔===========");
        Duration duration = Duration.between(LocalDateTime.parse("2022-05-18T23:58:20"), LocalDateTime.parse("2022-05-18T23:59:00"));
        System.out.println(duration.toMinutes());
        System.out.println("instant = " + instant + ",instant + 60s = " + instant.plusSeconds(60));
        Duration between1 = Duration.between(instant, instant.plusSeconds(60));
        System.out.println(between1.toMinutes());
        System.out.println(Duration.between(instant.plusSeconds(60), now.atZone(ZoneId.systemDefault()).toInstant()).toMinutes());

        between(LocalDateTime.parse("2023-04-03T18:47:00"),LocalDateTime.parse("2023-04-04T14:28:00"));
        System.out.println("zero:" + transferTimeToZero(LocalDateTime.now()));

        System.out.println(parse2datetime("2023-04-03 18:47:02",null));
        System.out.println(parse2Str(LocalDateTime.now(),null));
        System.out.println(parse2date("2023-04-03"));
        System.out.println(LocalDateTime.parse("2023-04-03T18:47:00").compareTo(LocalDateTime.parse("2023-04-03T18:47:00")));
        System.out.println("=========将时间按照每7天进行截取===========");
        System.out.println(getTimePeriods(now.minusDays(21),now.minusDays(1)));

        LocalDate firstDayOfMonth = parse2date("2023-06-04").with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("指定日期月份的第一天:" + firstDayOfMonth);
        LocalDate sunday = firstDayOfMonth.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        System.out.println("指定日期的本周日为:" + sunday);

        List<LocalDateDTO> weekPeriodsByMonth = getWeekPeriodsByMonth(parse2date("2023-06-30"));
        System.out.println("按 自然周 拆分指定月份:" + JSON.toJSONString(weekPeriodsByMonth));

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


    /**
     * 获取每季度 第一天或者最后一天
     */
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

    /**
     * 获取每年 第一天或者最后一天
     */
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

        SimpleDateFormat sdf = new SimpleDateFormat(MONTH_PATTERN);
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
        Assert.isTrue(start != null && end != null, "start and end is not null");
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
    /**
     * 根据年月（字符串）获取当前月的天数
     */
    public static int getNumOfMonth(String date) throws Exception{
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM");
        calendar.setTime(simpleDate.parse(date));
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 根据指定时间 切分时间
     */
    public static List<LocalDateTimeDTO> calBetweenTime(LocalDateTime startTime, LocalDateTime endTime, LocalDateTime configTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        // 日开始统计时间
        List<LocalDateTimeDTO> result = new ArrayList<>();
        // 开始时间和结束时间都在配置时间之前
        if (startTime.isBefore(configTime) && endTime.isBefore(configTime)) {
            LocalDateTimeDTO productDefectStatisticDTO = new LocalDateTimeDTO();
            productDefectStatisticDTO.setStartTime(startTime);
            productDefectStatisticDTO.setEndTime(endTime);
            productDefectStatisticDTO.setDateStr(dateTimeFormatter.format(startTime.minusDays(1)));
            result.add(productDefectStatisticDTO);
            return result;
        }
        // 开始时间和结束时间都在配置时间之后
        if (startTime.isAfter(configTime) && endTime.isAfter(configTime) && endTime.isBefore(configTime.plusDays(1))) {
            LocalDateTimeDTO productDefectStatisticDTO = new LocalDateTimeDTO();
            productDefectStatisticDTO.setStartTime(startTime);
            productDefectStatisticDTO.setEndTime(endTime);
            productDefectStatisticDTO.setDateStr(dateTimeFormatter.format(startTime.plusDays(1)));
            result.add(productDefectStatisticDTO);
            return result;
        }
        if (startTime.isBefore(configTime)) {
            LocalDateTimeDTO first = new LocalDateTimeDTO();
            first.setStartTime(startTime);
            first.setEndTime(configTime);
            first.setDateStr(dateTimeFormatter.format(startTime.minusDays(1)));
            result.add(first);
        }

        while (endTime.isAfter(configTime)) {
            LocalDateTimeDTO dto = new LocalDateTimeDTO();
            if (startTime.isAfter(configTime)) {
                dto.setStartTime(startTime);
            } else {
                dto.setStartTime(configTime);
            }
            dto.setDateStr(dateTimeFormatter.format(dto.getStartTime()));
            configTime = configTime.plusDays(1);
            dto.setEndTime(configTime);
            result.add(dto);
        }
        LocalDateTimeDTO last = result.get(result.size() - 1);
        if (!last.getEndTime().equals(endTime)){
            last.setEndTime(endTime);
        }
        return result;
    }

    /**
     * 将时间按照每7天进行截取
     */
    public static List<LocalDateTimeDTO> getTimePeriods(LocalDateTime startDate, LocalDateTime endDate) {
        List<LocalDateTimeDTO> timePeriods = new ArrayList<>();
        LocalDateTime periodStart = startDate;
        while (periodStart.isBefore(endDate)) {
            LocalDateTime periodEnd = periodStart.plusDays(7);
            if (periodEnd.isAfter(endDate)) {
                periodEnd = endDate;
            }
            LocalDateTimeDTO timePeriod = new LocalDateTimeDTO();
            timePeriod.setStartTime(periodStart);
            timePeriod.setEndTime(periodEnd);
            timePeriods.add(timePeriod);
            periodStart = periodEnd;
        }
        return timePeriods;
    }

    /**
     * 按 自然周 拆分指定月份
     */
    public static List<LocalDateDTO> getWeekPeriodsByMonth(LocalDate localDate) {
        if (Objects.isNull(localDate)) {
            return Collections.emptyList();
        }
        String month2Str = parseMonth2Str(localDate, null);
        LocalDate firstDayOfMonth = localDate.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());
        if (lastDayOfMonth.isAfter(LocalDate.now())) {
            lastDayOfMonth = LocalDate.now();
        }
        LocalDateDTO first = new LocalDateDTO();
        first.setStartTime(firstDayOfMonth);
        // 第一个周日
        LocalDate firstSunday = firstDayOfMonth.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        first.setEndTime(firstSunday);
        first.setDateStr(month2Str + "月:第一周:(" + parse2Str(firstDayOfMonth,"MM/dd") + "-" +  parse2Str(firstSunday,"MM/dd") + ")");
        List<LocalDateDTO> localDateDTOList = new ArrayList<>();
        localDateDTOList.add(first);
        if (firstSunday.isAfter(lastDayOfMonth)) {
            return localDateDTOList;
        }
        LocalDate sunday = firstSunday;
        int num = 2;
        while (sunday.isBefore(lastDayOfMonth)) {
            LocalDateDTO localDateDTO = new LocalDateDTO();
            localDateDTO.setStartTime(sunday.plusDays(1));
            sunday = sunday.plusDays(7);
            if (sunday.isAfter(lastDayOfMonth)) {
                localDateDTO.setEndTime(lastDayOfMonth);
            } else {
                localDateDTO.setEndTime(sunday);
            }
            localDateDTO.setDateStr(parseMonth2Str(localDate, null) + ":第" + convertNumToCN(num ++) + "周:("  + parse2Str(localDateDTO.getStartTime(),"MM/dd") + "-" +  parse2Str(localDateDTO.getEndTime(),"MM/dd") + ")");
            localDateDTOList.add(localDateDTO);
        }
        return localDateDTOList;
    }

    private static String convertNumToCN(int num) {
        switch (num) {
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
            default:
                return "七";
        }
    }

    private LocalDate getLocalDateDTO(LocalDate firstLocalDate) {
        return firstLocalDate.plusDays(7);
    }

    public static String parse2Str(LocalDateTime localDateTime, String pattern) {
        if (Objects.isNull(localDateTime)) {
            return "";
        }
        if (StringUtils.isBlank(pattern)) {
            pattern = DATE_TIME_PATTERN;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dateTimeFormatter);
    }

    public static String parse2Str(LocalDate localDate, String pattern) {
        if (Objects.isNull(localDate)) {
            return "";
        }
        if (StringUtils.isBlank(pattern)) {
            pattern = DATE_PATTERN;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDate.format(dateTimeFormatter);
    }

    public static String parseMonth2Str(LocalDate localDate, String pattern) {
        if (Objects.isNull(localDate)) {
            return "";
        }
        if (StringUtils.isBlank(pattern)) {
            pattern = MONTH_PATTERN;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDate.format(dateTimeFormatter);
    }

    public static LocalDateTime parse2datetime(String date, String pattern) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        if (StringUtils.isBlank(pattern)) {
            pattern = DATE_TIME_PATTERN;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(date, dateTimeFormatter);
    }

    public static LocalDate parse2date(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return LocalDate.parse(date, dateTimeFormatter);
    }

    public static void between(LocalDateTime start,LocalDateTime end) {
        Duration duration = Duration.between(start,end);
        long days = duration.toDays(); //相差的天数
        long hours = duration.toHours();//相差的小时数
        long minutes = duration.toMinutes();//相差的分钟数
        System.out.println("between:" + minutes);
        long millis = duration.toMillis();//相差毫秒数
        long nanos = duration.toNanos();//相差的纳秒数
    }

    /**
     * 秒转为 LocalDateTime
     */
    public LocalDateTime secondToLocalDateTime(Long second) {
        return LocalDateTime.ofEpochSecond(second, 0, ZoneOffset.of("+8"));
    }

    /**
     * 毫秒转为 LocalDateTime
     */
    public LocalDateTime milliSecondToLocalDateTime(Long milliSecond) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSecond), ZoneId.systemDefault());
    }

    /**
     * 时分秒 置为0
     */
    public static LocalDateTime transferTimeToZero(LocalDateTime localDateTime) {
        return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), 0, 0, 0);
        //return localDateTime.withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

}
