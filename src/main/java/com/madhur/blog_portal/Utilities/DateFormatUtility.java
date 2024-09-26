package com.madhur.blog_portal.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for formatting dates.
 */
public final class DateFormatUtility {
    private DateFormatUtility() {
    }

    /**
     * Returns the current date and time in the format "dd-MM-yyyy HH:mm:ss".
     * @return Formatted date string.
     */
    public static String newDate() {
        Date date = new Date();
        String pattern = "dd-MMM-yyyy HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }
}
