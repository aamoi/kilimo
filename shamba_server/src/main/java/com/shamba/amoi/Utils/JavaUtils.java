package com.shamba.amoi.Utils;

import com.oracle.tools.packager.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by amoi on 15/07/2018.
 */
public class JavaUtils {

    public static double stringToDouble(String double_string) {

        double double_val = 0.00;
        String string_val = double_string;

        if (!(double_string == null))
            double_val = Double.parseDouble(string_val);

        return double_val;
    }

    /**
     * Convert string to date
     * @param date_string
     * @return
     */
    public static Date stringToDate(String date_string) {

        Date date = null;


        if (!(date_string == null)) {

            try {
                date = new SimpleDateFormat("dd-MM-yyyy").parse(date_string);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return date;
    }

    /**
     * Formats date to "dd-MM-yyyy"
     * @param date
     * @return
     */
    public static Date formatDate(Date date) {
        return stringToDate(date.toString());
    }
}
