package com.shamba.amoi.shambaapp.db;

import android.app.Activity;
import android.arch.persistence.room.Room;

/**
 * Created by amoi on 24/12/2017.
 */

/**
 * Initializes and returns Room SQLite database object
 */
public class DBAdaptor {

    Activity context_activity;

    public DBAdaptor(Activity activity) {
        context_activity = activity;
    }

    public ShambaAppDB getDB() {

//        ShambaAppDB db = Room.databaseBuilder(context_activity.getApplicationContext(),
//                ShambaAppDB.class, "shamba_app_db").fallbackToDestructiveMigration().build();
        ShambaAppDB db = Room.databaseBuilder(context_activity.getApplicationContext(),
                ShambaAppDB.class, "shamba_app_db").build();
        return db;
    }
}

