package cn.zhihan.framework.base.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

/**
 * description: 时间处理工具类
 * date: 2019-06-03 14:59
 * version: 1.0
 * author: chenzhongyi
 */
public class MyTimeUtil {

    public static final long MILLISECOND = 1L;
    public static final long SECOND = MILLISECOND * 1000;
    public static final long MINUTE = SECOND * 60;
    public static final long HOUR = MINUTE * 60;
    public static final long DAY = HOUR * 24;
    public static final long WEEK = DAY * 7;

    private static final String DEFAULT_FORMATTER = "yyyy-MM-dd HH:mm:ss";

    /**
     * description: 时间戳转 localDateTime
     * date: 2019-05-25 10:30
     * version: 1.0
     * author: chenzhongyi
     *
     * @param timeMillis
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime toDateTime(long timeMillis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timeMillis), ZoneId.systemDefault());
    }

    /**
     * description: date 转 localDataTime
     * date: 2019-05-25 15:29
     * version: 1.0
     * author: chenzhongyi
     *
     * @param date
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime toDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * description: localDateTime 转时间戳
     * date: 2019-05-25 10:30
     * version: 1.0
     * author: chenzhongyi
     *
     * @param dateTime
     * @return long
     */
    public static long toTimeMillis(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * description: 获取当天最小时间戳
     * date: 2019-05-25 10:18
     * version: 1.0
     * author: chenzhongyi
     *
     * @return long
     */
    public static long dayMinTimeMillis() {
        return toTimeMillis(LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
    }

    /**
     * description: 获取该时间戳下日期最小时间戳
     * date: 2019-05-25 10:25
     * version: 1.0
     * author: chenzhongyi
     *
     * @param timeMillis
     * @return long
     */
    public static long dayMinTimeMillis(long timeMillis) {
        LocalDateTime localDateTime = toDateTime(timeMillis);
        return toTimeMillis(LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MIN));
    }

    /**
     * description: 获取该时间戳过去minusDay天的最小时间戳
     * date: 2019-05-25 10:50
     * version: 1.0
     * author: chenzhongyi
     *
     * @param timeMillis 时间戳
     * @param plusDays   未来天数 注:当plusDays为负数时表示过去天数
     * @return long
     */
    public static long dayMinTimeMillis(long timeMillis, int plusDays) {
        LocalDateTime dateTime = toDateTime(timeMillis);
        return toTimeMillis(LocalDateTime.of(dateTime.toLocalDate().plusDays(plusDays), LocalTime.MIN));
    }

    /**
     * description: 获取昨天最小时间戳
     * date: 2019-05-25 10:55
     * version: 1.0
     * author: chenzhongyi
     *
     * @param
     * @return long
     */
    public static long beforeDayMinTimeMillis() {
        return dayMinTimeMillis(System.currentTimeMillis(), -1);
    }

    /**
     * description: 获取该时间戳下日期最大时间戳
     * date: 2019-05-25 11:01
     * version: 1.0
     * author: chenzhongyi
     *
     * @param timeMillis
     * @return long
     */
    public static long dayMaxTimeMillis(long timeMillis) {
        LocalDateTime dateTime = toDateTime(timeMillis);
        return toTimeMillis(LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MAX));
    }

    /**
     * description: 获取周一最小时间戳
     * date: 2019-05-25 11:31
     * version: 1.0
     * author: chenzhongyi
     *
     * @param timeMillis
     * @return long
     */
    public static long mondayMinTimeMillis(long timeMillis) {
        return dayOfWeekMinTimeMillis(timeMillis, DayOfWeek.MONDAY);
    }

    /**
     * description: 获取周日最小时间戳
     * date: 2019-05-25 11:36
     * version: 1.0
     * author: chenzhongyi
     *
     * @param timeMillis
     * @return long
     */
    public static long sundayMinTimeMillis(long timeMillis) {
        return dayOfWeekMinTimeMillis(timeMillis, DayOfWeek.SUNDAY);
    }

    /**
     * description: 获取周几最小时间戳
     * date: 2019-05-25 11:50
     * version: 1.0
     * author: chenzhongyi
     *
     * @param timeMillis 时间戳
     * @param dayOfWeek  周几
     * @return long
     */
    public static long dayOfWeekMinTimeMillis(long timeMillis, DayOfWeek dayOfWeek) {
        LocalDateTime dateTime = toDateTime(timeMillis);
        return toTimeMillis(LocalDateTime.of(((LocalDateTime) dayOfWeek.adjustInto(dateTime)).toLocalDate(), LocalTime.MIN));
    }

    /**
     * description: 获取本月第一天最小时间戳
     * date: 2019-05-25 14:23
     * version: 1.0
     * author: chenzhongyi
     *
     * @param timeMillis
     * @return long
     */
    public static long firstDayOfMonthMinTimeMillis(long timeMillis) {
        LocalDateTime dateTime = toDateTime(timeMillis);
        return toTimeMillis(LocalDateTime.of(dateTime.with(firstDayOfMonth()).toLocalDate(), LocalTime.MIN));
    }

    /**
     * description: 获取本月最后一天最小时间戳
     * date: 2019-05-25 14:30
     * version: 1.0
     * author: chenzhongyi
     *
     * @param timeMillis
     * @return long
     */
    public static long lastDayOfMonthMinTimeMillis(long timeMillis) {
        LocalDateTime dateTime = toDateTime(timeMillis);
        return toTimeMillis(LocalDateTime.of(dateTime.with(lastDayOfMonth()).toLocalDate(), LocalTime.MIN));
    }

    /**
     * description: 两时间戳的天数差
     * date: 2019-05-27 16:46
     * version: 1.0
     * author: chenzhongyi
     *
     * @param startTime
     * @param endTime
     * @return int
     */
    public static int differDays(long startTime, long endTime) {
        long differ = startTime - endTime;
        return (int) (Math.abs(differ) / DAY);
    }

    /**
     * HH:mm 相差分钟数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static int differMinutes(String startTime, String endTime) {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startLocalTime = LocalTime.parse(startTime, dateTimeFormat);
        LocalTime endLocalTime = LocalTime.parse(endTime, dateTimeFormat);
        return (int) Duration.between(startLocalTime, endLocalTime).toMinutes();
    }

    /**
     * description: 在当前时间的基础上增加plusMonths月
     * date: 2019-05-25 14:39
     * version: 1.0
     * author: chenzhongyi
     *
     * @param timeMillis
     * @param plusMonths
     * @return long
     */
    public static long plusMonths(long timeMillis, int plusMonths) {
        LocalDateTime dateTime = toDateTime(timeMillis);
        return toTimeMillis(dateTime.plusMonths(plusMonths));
    }

    /**
     * description: 前一个月时间戳
     * date: 2019-05-25 14:40
     * version: 1.0
     * author: chenzhongyi
     *
     * @param timeMillis
     * @return long
     */
    public static long beforeMonth(long timeMillis) {
        return plusMonths(timeMillis, -1);
    }

    /**
     * description: 获取某月第几个礼拜一的凌晨时间戳
     * date: 2019-05-25 14:43
     * version: 1.0
     * author: chenzhongyi
     *
     * @param timeMillis
     * @param week
     * @return long
     */
    public static long weekOfMonth(long timeMillis, int week) {
        timeMillis = firstDayOfMonthMinTimeMillis(timeMillis);
        long firstDayOfWeek = mondayMinTimeMillis(timeMillis);
        if (firstDayOfWeek >= timeMillis) {
            week--;
        }
        return firstDayOfWeek + DAY * 7 * week;
    }

    /**
     * description: 格式化时间
     * date: 2019-05-25 15:26
     * version: 1.0
     * author: chenzhongyi
     *
     * @param timeMillis
     * @param formatter
     * @return java.lang.String
     */
    public static String formatter(long timeMillis, String formatter) {
        return toDateTime(timeMillis).format(DateTimeFormatter.ofPattern(formatter));
    }

    /**
     * description: 格式化时间 yyyy-MM-dd HH:mm:ss
     * date: 2019-05-25 15:26
     * version: 1.0
     * author: chenzhongyi
     *
     * @param timeMillis
     * @return java.lang.String
     */
    public static String formatter(long timeMillis) {
        return formatter(timeMillis, DEFAULT_FORMATTER);
    }

    /**
     * description: 格式化时间
     * date: 2019-05-25 15:32
     * version: 1.0
     * author: chenzhongyi
     *
     * @param date
     * @param formatter
     * @return java.lang.String
     */
    public static String formatter(Date date, String formatter) {
        return toDateTime(date).format(DateTimeFormatter.ofPattern(formatter));
    }

    /**
     * description: 默认格式化时间 yyyy-MM-dd HH:mm:ss
     * date: 2019-05-25 15:32
     * version: 1.0
     * author: chenzhongyi
     *
     * @param date
     * @return java.lang.String
     */
    public static String formatter(Date date) {
        return formatter(date, DEFAULT_FORMATTER);
    }

    /**
     * description: dateTime 转时间戳
     * date: 2019-05-25 16:22
     * version: 1.0
     * author: chenzhongyi
     *
     * @param dateTime
     * @param formatter
     * @return long
     */
    public static long formatter(String dateTime, String formatter) {
        return toTimeMillis(LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(formatter)));
    }

    /**
     * description: dateTime 转时间戳
     * date: 2019-05-25 16:23
     * version: 1.0
     * author: chenzhongyi
     *
     * @param dateTime
     * @return long
     */
    public static long formatter(String dateTime) {
        return formatter(dateTime, DEFAULT_FORMATTER);
    }

    /**
     * description: 获取时间是周几
     * version: 1.0
     *
     * @param timeMillis
     * @return int
     * @date: 2019/11/21 20:20
     * @author: liuzhenjun
     */
    public static int weekDay(long timeMillis) {
        LocalDateTime dateTime = toDateTime(timeMillis);
        return dateTime.getDayOfWeek().getValue();
    }

    /**
     * description: 相差时间（只计算时分秒）
     * version: 1.0
     *
     * @param startTimeMillis
     * @param endTimeMillis
     * @return java.lang.Long
     * @date: 2020/3/1 10:48
     * @author: liuzhenjun
     */
    public static Long diffTime(long startTimeMillis, long endTimeMillis) {
        LocalDateTime startDateTime = toDateTime(startTimeMillis);
        long startS = startDateTime.getHour() * HOUR + startDateTime.getMinute() * MINUTE + startDateTime.getSecond() * SECOND;
        LocalDateTime endDateTime = toDateTime(endTimeMillis);
        long endS = endDateTime.getHour() * HOUR + endDateTime.getMinute() * MINUTE + endDateTime.getSecond() * SECOND;
        return endS - startS;
    }

    private static void print(long timeMillis) {
        System.out.println(formatter(timeMillis, DEFAULT_FORMATTER));
    }

    public static void main(String[] args) {
        long timeMillis = System.currentTimeMillis();
        print(timeMillis);
        print(dayMinTimeMillis());
        print(dayMinTimeMillis(timeMillis));
        print(dayMaxTimeMillis(timeMillis));
        print(dayMinTimeMillis(timeMillis, 1));
        print(dayMinTimeMillis(timeMillis, 2));
        print(beforeDayMinTimeMillis());
        print(mondayMinTimeMillis(timeMillis));
        print(sundayMinTimeMillis(timeMillis));
        print(dayOfWeekMinTimeMillis(timeMillis, DayOfWeek.MONDAY));
        print(dayOfWeekMinTimeMillis(timeMillis, DayOfWeek.TUESDAY));
        print(dayOfWeekMinTimeMillis(timeMillis, DayOfWeek.WEDNESDAY));
        print(dayOfWeekMinTimeMillis(timeMillis, DayOfWeek.THURSDAY));
        print(dayOfWeekMinTimeMillis(timeMillis, DayOfWeek.FRIDAY));
        print(dayOfWeekMinTimeMillis(timeMillis, DayOfWeek.SATURDAY));
        print(dayOfWeekMinTimeMillis(timeMillis, DayOfWeek.SUNDAY));
        print(firstDayOfMonthMinTimeMillis(timeMillis));
        print(lastDayOfMonthMinTimeMillis(timeMillis));
        System.out.println(differDays(timeMillis, dayMinTimeMillis(timeMillis, 3)));
        System.out.println(differMinutes("13:20", "14:21"));
        print(plusMonths(timeMillis, 2));
        print(beforeMonth(timeMillis));
        print(weekOfMonth(timeMillis, 1));
        print(weekOfMonth(timeMillis, 2));
        print(weekOfMonth(timeMillis, 3));
        print(weekOfMonth(timeMillis, 4));
        Instant instant = Instant.now();
        print(instant.with(ChronoField.NANO_OF_SECOND, 0).toEpochMilli());
        System.out.println(diffTime(1583034551000L, 1583030951000L));
    }
}
