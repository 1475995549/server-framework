package cn.zhihan.framework.base.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDateUtil {
    
    public static final Integer MILLISECOND = 1;
    public static final Integer SECOND = 1000 * MILLISECOND;
    public static final Integer MINUTE = 60 * SECOND;
    public static final Integer HOUR = 60 * MINUTE;
    public static final Integer DAY = 24 * HOUR;
    
    private static SimpleDateFormat sdf = new SimpleDateFormat("");
    
    /**
     * description: 日期转日期字符
     * date: 2019/5/19 7:21 PM
     * version: 1.0
     * author: suzui
     *
     * @param date
     * @return java.lang.String
     */
    
    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }
        sdf.applyPattern(format);
        return sdf.format(date);
    }
    
    public static String format(Long date, String format) {
        if (date == null) {
            return null;
        }
        return format(new Date(date), format);
    }
    
    public static String format(Date date) {
        if (date == null) {
            return null;
        }
        return format(date, "yyyy/MM/dd HH:mm");
    }
    
    public static String format(Long date) {
        if (date == null) {
            return null;
        }
        return format(date, "yyyy/MM/dd HH:mm");
    }
    
    
    public static Date format(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        try {
            return DateUtils.parseDate(date, new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "HH:mm"});
        } catch (ParseException e) {
            return null;
        }
    }
    
    public static Date format(String date, String format) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        try {
            return DateUtils.parseDate(date, new String[]{format, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "HH:mm"});
        } catch (ParseException e) {
            return null;
        }
    }
    
    public static Date truncate(Date date, int field) {
        return DateUtils.truncate(date, field);
    }
    
    public static Date truncate(Date date) {
        return truncate(date, Calendar.DAY_OF_MONTH);
    }
    
    public static Date truncate(Long date) {
        return truncate(new Date(date));
    }
    
    public static Date ceiling(Date date, int field) {
        return DateUtils.ceiling(date, field);
    }
    
    public static Date ceiling(Date date) {
        return ceiling(date, Calendar.DAY_OF_MONTH);
    }
    
    public static Date ceiling(Long date) {
        return ceiling(new Date(date));
    }
    
    public static int dayBetween(Long startDay, Long endDay) {
        return (int) ((DateUtils.truncate(new Date(endDay), Calendar.DAY_OF_MONTH).getTime() - DateUtils.truncate(new Date(startDay), Calendar.DAY_OF_MONTH).getTime()) / DAY);
    }
    
    public static boolean today(Long time) {
        return dayBetween(time, System.currentTimeMillis()) == 0;
    }
    
    public static int dayOfWeek(Long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(time));
        int day = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return day == 0 ? 7 : day;
    }
    
    public static int dayOfMonth(Long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(time));
        return cal.get(Calendar.DAY_OF_MONTH);
    }
    
    public static int dayOfYear(Long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(time));
        return cal.get(Calendar.DAY_OF_YEAR);
    }
    
    public static int dayOfAll(Long time) {
        return (int) (truncate(time).getTime() / DAY + 1);
    }
    
    public static Date addDays(Date date, Integer day) {
        return DateUtils.addDays(date, day);
    }
    
    public static Date addDays(Long date, Integer day) {
        return DateUtils.addDays(new Date(date), day);
    }
    
    public static int year(Long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(time));
        return cal.get(Calendar.YEAR);
    }
    
    public static int quarter(Long time) {
        int month = month(time);
        return (month - 1) / 3 + 1;
    }
    
    public static int month(Long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(time));
        return cal.get(Calendar.MONTH) + 1;
    }
    
    public static int totalMonth(Long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(time));
        return cal.get(Calendar.YEAR) * 12 + cal.get(Calendar.MONTH) + 1;
    }
    
    public static int lastDayOfMonth(Long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(time));
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        return cal.get(Calendar.DAY_OF_MONTH);
    }
    
    public static long dayToTime(int day) {
        return truncate(new Date(day * DAY)).getTime();
    }
    
    public static long cronToTime(String cron) {
        if (StringUtils.isBlank(cron)) {
            return 0l;
        }
        if (cron.contains("秒") || cron.contains("s")) {
            return Integer.parseInt(cron.replace("秒", "").replace("s", "")) * SECOND;
        }
        if (cron.contains("分钟") || cron.contains("分") || cron.contains("mn")) {
            return (long) (Double.parseDouble(cron.replace("分钟", "").replace("分", "").replace("mn", "")) * MINUTE);
        }
        if (cron.contains("小时") || cron.contains("时") || cron.contains("h")) {
            return (long) (Double.parseDouble(cron.replace("小时", "").replace("时", "").replace("h", "")) * HOUR);
        }
        if (cron.contains("天") || cron.contains("d")) {
            return (long) (Double.parseDouble(cron.replace("天", "").replace("d", "")) * DAY);
        }
        return 0l;
    }
    
    public static String timeToCron(Long time, int field) {
        String cron = "";
        if (time == null) {
            return cron;
        }
        long day = time / DAY;
        long hour = (time % DAY) / HOUR;
        long minute = ((time % DAY) % HOUR) / MINUTE;
        long second = (((time % DAY) % HOUR) % MINUTE) / SECOND;
        if (field >= Calendar.DATE) {
            if (day > 0) {
                cron += day + "天";
            }
        }
        if (field >= Calendar.HOUR) {
            if (hour > 0) {
                cron += hour + "小时";
            }
        }
        if (field >= Calendar.MINUTE) {
            if (minute > 0) {
                cron += minute + "分钟";
            }
        }
        if (field >= Calendar.SECOND) {
            if (second > 0) {
                cron += second + "秒";
            }
        }
        return cron;
    }
    
}
