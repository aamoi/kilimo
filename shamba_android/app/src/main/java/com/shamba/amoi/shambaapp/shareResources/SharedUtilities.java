package com.shamba.amoi.shambaapp.shareResources;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.models.projects.PhaseItem;
import com.shamba.amoi.shambaapp.models.projects.PlantingProgramItem;
import com.shamba.amoi.shambaapp.models.projects.TaskItem;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by amoi on 10/02/2018.
 */

public class SharedUtilities {

    public static String getTimeStamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String str_timestamp = timestamp.toString();
        str_timestamp = str_timestamp.replaceAll("-", "");
        str_timestamp = str_timestamp.replaceAll(" ", "_");
        return str_timestamp;
    }

    public static String getDateStamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String str_timestamp = timestamp.toString();
        str_timestamp = str_timestamp.replaceAll("-", "");
        str_timestamp = str_timestamp.replaceAll(" ", "_");
        str_timestamp = str_timestamp.substring(0, 8);
        return str_timestamp;
    }

    public static String getMonthStamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String str_timestamp = timestamp.toString();
        str_timestamp = str_timestamp.replaceAll("-", "");
        str_timestamp = str_timestamp.replaceAll(" ", "_");
        str_timestamp = str_timestamp.substring(0, 6);
        return str_timestamp;
    }

    public static List<String> getHashMapKeyList(HashMap<String, ?> hashMap) {

        ArrayList<String> key_list = new ArrayList<>();

        for (String key : hashMap.keySet()) {
            key_list.add(key);
        }

        return key_list;

    }

    public static List<String> getHashMapStringKeySetList(Set<String> set) {

        ArrayList<String> key_list = new ArrayList<>();

        for (String key : set) {
            key_list.add(key);
        }
        return key_list;
    }

    public static EditText getEditTextById(View view, int id) {
        return (EditText) view.findViewById(id);
    }

    public static Spinner getSpinnerById(View view, int id) {
        return (Spinner) view.findViewById(id);
    }

    public static Button getButtonById(View view, int id) {
        return (Button) view.findViewById(id);
    }
}

/**
 * Listener utility when spinner item is selected!
 */
class OnSpinnerItemSelectedUtility {

}
