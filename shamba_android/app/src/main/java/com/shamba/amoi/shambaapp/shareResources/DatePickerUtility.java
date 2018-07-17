package com.shamba.amoi.shambaapp.shareResources;

import android.app.DatePickerDialog;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by amoi on 01/01/2018.
 */

public class DatePickerUtility  {

    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    EditText editText;


    public DatePickerUtility(EditText editText){
        this.editText = editText;
        setDateOnEditTextField();
    }

    public void setDateOnEditTextField(){

        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Log.d("Create Master Plan", "View has focus");
                    new DatePickerDialog(v.getContext(), date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }else {
                    Log.d("Create Master Plan", "View has no focus");
                }
            }
        });
    }

    private void updateLabel() {
//        String myFormat = "MM/dd/yy";
        String myFormat = "dd/MM/yyyy";

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(sdf.format(myCalendar.getTime()));
    }
}
