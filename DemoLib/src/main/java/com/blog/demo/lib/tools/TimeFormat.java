package com.blog.demo.lib.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by cn on 2017/4/5.
 */

public class TimeFormat {

    public static String timeToYearMinute(long millisecond) {
        return milliToFormat(millisecond, "yyyy-MM-dd HH:mm");
    }

    public static String milliToFormat(long millisecond, String format) {
        if (millisecond == 0)
            return "";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        date.setTime(millisecond);
        return sdf.format(date);
    }
}
