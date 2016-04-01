package com.example.thierry.todolist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by thierry on 01/04/16.
 */
public class ConvertisseurDate {
    public static String strSlashFromDate(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return String.valueOf(new StringBuilder()
                .append(cal.get(Calendar.DAY_OF_MONTH))
                .append("/")
                .append(cal.get(Calendar.MONTH) + 1)
                .append("/")
                .append(cal.get(Calendar.YEAR)));
    }

    public static String strTextFromDate(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);

        return String.valueOf(new StringBuilder(cal.get(Calendar.DAY_OF_MONTH))
                .append(" ").append(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()))
                .append(" ")
                .append(cal.get(Calendar.YEAR)));
    }

    public static Date dateFromSlashStr(SimpleDateFormat sd, String date) {
        Date d = new Date();
        try {
            d = sd.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }
}
