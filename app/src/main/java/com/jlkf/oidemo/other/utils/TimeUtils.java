package com.jlkf.oidemo.other.utils;

import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * description
 *
 * @author created by wuwang on 2016/5/20
 */
public class TimeUtils {

    private static TimeUtils instance;

    private TimeUtils() {

    }

//    public static TimeUtils getInstance(){
//        if(instance!=null){
//            synchronized (TimeUtils.class){
//                if(instance==null){
//                    instance=new TimeUtils();
//                }
//            }
//        }
//        return instance;
//    }

    public static CharSequence getDateWithTime(long timeStamp) {
        Calendar today = Calendar.getInstance();
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(timeStamp);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        time.set(Calendar.HOUR_OF_DAY, 0);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);
        long l = time.getTimeInMillis() - today.getTimeInMillis();
        int t = (int) (l / (24 * 60 * 60 * 1000));
        switch (t) {
            case 0:
                return "今天" + DateFormat.format("  HH:mm", timeStamp);
            case 1:
                return "明天" + DateFormat.format("  HH:mm", timeStamp);
            default:
                return DateFormat.format("yyyy-MM-dd HH:mm", timeStamp);
        }
    }

    public static CharSequence getDate(long timeStamp) {
        Calendar today = Calendar.getInstance();
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(timeStamp);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        time.set(Calendar.HOUR_OF_DAY, 0);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);
        long l = time.getTimeInMillis() - today.getTimeInMillis();
        int t = (int) (l / (24 * 60 * 60 * 1000));
        switch (t) {
            case 0:
                return "今天";
            case 1:
                return "明天";
            case -1:
                return "昨天";
            case 2:
                return "后天";
            case -2:
                return "前天";
            default:
                if (time.get(Calendar.YEAR) == today.get(Calendar.YEAR)) {
                    return DateFormat.format("MM-dd", timeStamp);
                } else {
                    return DateFormat.format("yyyy-MM-dd", timeStamp);
                }
        }
    }

    /**
     * 转化时间输入时间与当前时间的间隔
     *
     * @param timestamp
     * @return
     */
    public static String converTime(long timestamp) {
        long currentSeconds = System.currentTimeMillis() / 1000;
        long timeGap = currentSeconds - timestamp / 1000;// 与现在时间相差秒数
        String timeStr = null;
        if (timeGap > 7 * 24 * 60 * 60) {
            timeStr= (String) getAllDate(timestamp);
        } else if (timeGap > 24 * 60 * 60) {// 1天以上
            timeStr = timeGap / (24 * 60 * 60) + "天前";
        } else if (timeGap > 60 * 60) {// 1小时-24小时
            timeStr = timeGap / (60 * 60) + "小时前";
        } else if (timeGap > 60) {// 1分钟-59分钟
            timeStr = timeGap / 60 + "分钟前";
        } else {// 1秒钟-59秒钟
            timeStr = "刚刚";
        }
        return timeStr;
    }

    private static String getWeek(long timeStamp) {
        int mydate = 0;
        String week = null;
        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date(timeStamp));
        mydate = cd.get(Calendar.DAY_OF_WEEK);
        // 获取指定日期转换成星期几
        if (mydate == 1) {
            week = "星期日";
        } else if (mydate == 2) {
            week = "星期一";
        } else if (mydate == 3) {
            week = "星期二";
        } else if (mydate == 4) {
            week = "星期三";
        } else if (mydate == 5) {
            week = "星期四";
        } else if (mydate == 6) {
            week = "星期五";
        } else if (mydate == 7) {
            week = "星期六";
        }
        return week;
    }
    /**
     * 24小时内显示今天，2-7天内显示星期，超过7天显示16-11-12
     *
     * @param timestamp
     * @return
     */
    public static String converTimeCp(long timestamp) {
        long currentSeconds = System.currentTimeMillis() / 1000;
        long timeGap = currentSeconds - timestamp / 1000;// 与现在时间相差秒数
        String timeStr = null;
        if (timeGap > 7 * 24 * 60 * 60) {
            timeStr= (String) getAllDate3(timestamp);
            timeStr=timeStr.substring(2);
        } else if (timeGap > 24 * 60 * 60) {// 1天以上
            timeStr = getWeek(timestamp);
        } else {// 1小时-24小时
            timeStr ="今天";
        }
        return timeStr;
    }

    public static CharSequence getTime(long timeStamp) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
        return getDate(timeStamp) + " " + format.format(timeStamp);
    }

    public static CharSequence getTime2(long timeStamp) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.CHINA);
        return getDate(timeStamp) + " " + format.format(timeStamp);
    }

    public static CharSequence getTime(long timeStamp, String format) {
        SimpleDateFormat s = new SimpleDateFormat(format, Locale.CHINA);
        return s.format(timeStamp * 1000);
    }

    //时间戳转字符串
    public static String getStrTime(String timeStamp){
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
        long  l = Long.valueOf(timeStamp);
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }
    //时间戳转字符串
    public static String getStrTimeFromCp(String timeStamp){
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
        long  l = Long.valueOf(timeStamp);
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }


    public static CharSequence getAllDate(long timeStamp) {
        Calendar today = Calendar.getInstance();
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(timeStamp);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        time.set(Calendar.HOUR_OF_DAY, 0);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);

        return DateFormat.format("yyyy-MM-dd HH:mm:ss", timeStamp);

    }


    public static CharSequence getAllDate2(long timeStamp) {
        Calendar today = Calendar.getInstance();
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(timeStamp);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        time.set(Calendar.HOUR_OF_DAY, 0);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);

        return DateFormat.format("yyyy-MM-dd", timeStamp);

    }
    public static CharSequence getAllDate3(long timeStamp) {
        Calendar today = Calendar.getInstance();
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(timeStamp);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        time.set(Calendar.HOUR_OF_DAY, 0);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);

        return DateFormat.format("yyyy-MM-dd", timeStamp);

    }

    public static CharSequence getCnDate(long timeStamp) {
        Calendar today = Calendar.getInstance();
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(timeStamp);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        time.set(Calendar.HOUR_OF_DAY, 0);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);

        return DateFormat.format("yyyy年MM月dd日", timeStamp);
    }
}
