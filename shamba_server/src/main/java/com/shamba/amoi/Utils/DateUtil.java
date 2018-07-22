package com.shamba.amoi.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by amoi on 09/07/2018.
 */
public class DateUtil {
    public static Date stringToDate(String date_string) {

        Date date = null;

        try {

            date = new SimpleDateFormat("yyyy-MM-dd").parse(date_string);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

}
