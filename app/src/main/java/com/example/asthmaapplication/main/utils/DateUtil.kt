package com.example.asthmaapplication.main.common;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
    private static final String dayFormat = "dd";
    private static final String monthFormat = "MMM";
    private static final String mediumFormat = "EEEE, MM/dd";
    private static final String mediumNumericFormat = "MM/dd/YYY";

    private static final String fullNumericFormat = "MM/dd/YYY hh:mm aa";

    public static String getDay(Date date) {
        return formatDate(dayFormat, date);
    }

    public static String getMonth(Date date) {
        return formatDate(monthFormat, date);
    }

    /**
     * Uses format EEEE, MM/dd
     *
     * @param date
     * @return
     */
    public static String getMediumDateFormat(Date date) {
        return formatDate(mediumFormat, date);
    }

    /**
     * Uses format MM/dd/YYY
     *
     * @param date
     * @return
     */
    public static String getMediumNumericDateFormat(Date date) {
        return formatDate(mediumNumericFormat, date);
    }

    public static String getFullNumericFormat(Date date) {
        return formatDate(fullNumericFormat, date);

    }

    public static String formatDate(String stringFormat, Date date) {
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat(stringFormat, Locale.getDefault());
            return format.format(date);
        } else {
            return "Invalid Timestamp";
        }
    }

    public static boolean isTodayInRange(Date from, Date to) {
        DateTime compareFrom = new DateTime(from);
        if (to != null) {
            DateTime compareTo = new DateTime(to);
            return compareFrom.isBeforeNow() && compareTo.isAfterNow();
        } else {
            return compareFrom.isBeforeNow();
        }

    }

    public static boolean isThisWeekInRange(Date from, Date to) {
        DateTime today = new DateTime();
        DateTime compareFrom = new DateTime(from);

        if (to != null) {
            DateTime compareTo = new DateTime(to);
            return compareFrom.getWeekOfWeekyear() <= today.getWeekOfWeekyear() &&
                    compareTo.getWeekOfWeekyear() >= today.getWeekOfWeekyear();
        } else {
            return compareFrom.getWeekOfWeekyear() <= today.getWeekOfWeekyear();
        }
    }


    public static boolean isNextWeekInRange(Date from, Date to) {
        DateTime nextWeek = new DateTime();

        DateTime compareFrom = new DateTime(from);

        if (to != null) {
            DateTime compareTo = new DateTime(to);

            return compareFrom.getWeekOfWeekyear() <= nextWeek.getWeekOfWeekyear() + 1 &&
                    compareTo.getWeekOfWeekyear() >= nextWeek.getWeekOfWeekyear() + 1;
        } else {
            return compareFrom.getWeekOfWeekyear() <= nextWeek.getWeekOfWeekyear() + 1;
        }
    }

    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }

    public static boolean isToday(Date date) {
        return isSameDay(date, Calendar.getInstance().getTime());
    }

    /**
     * Checks if a particular date is within today and daysInFuture days ahead
     *
     * @param date
     * @param daysInFuture
     * @return true if date is within range, false otherwise
     */
    public static boolean isInNextDays(Date date, int daysInFuture) {
        // daysInFuture days ahead
        DateTime now = new DateTime();
        DateTime future = now.plusDays(daysInFuture);

        DateTime dateTime = new DateTime(date);
        return dateTime.isBefore(future);
    }

    public static Date cvtToGmt(Date date) {
        TimeZone tz = TimeZone.getDefault();
        Date ret = new Date(date.getTime() - tz.getRawOffset());

        // if we are now in DST, back off by the delta.  Note that we are checking the GMT date, this is the KEY.
        if (tz.inDaylightTime(ret)) {
            Date dstDate = new Date(ret.getTime() - tz.getDSTSavings());

            // check to make sure we have not crossed back into standard time
            // this happens when we are on the cusp of DST (7pm the day before the change for PDT)
            if (tz.inDaylightTime(dstDate)) {
                ret = dstDate;
            }
        }
        return ret;
    }
}

