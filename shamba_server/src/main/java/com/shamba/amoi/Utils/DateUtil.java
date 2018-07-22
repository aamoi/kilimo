package com.shamba.amoi.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by amoi on 09/07/2018.
 */
public class DateUtil {
    /**
     * Accepts only non-null date fields!
     * @param date_string
     * @return
     */
    public static Date stringToDate(String date_string) {

        Date date = null;

        try {

            date = new SimpleDateFormat("yyyy-MM-dd").parse(date_string);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static Date nullableStringToDate(String date_string) {

        Date date = null;

        if(!(date_string==null)){
            try {

                date = new SimpleDateFormat("yyyy-MM-dd").parse(date_string);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

}
