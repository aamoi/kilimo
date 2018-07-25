package com.shamba.amoi.shambaapp.db.inventory;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by amoi on 01/03/2018.
 */
@Entity
public class InventoryUtilization {
        @PrimaryKey
        @NonNull
    int id ;
    int stock_id;
    int project_id;
    int phase_id;
    int task_id;
    int utilized_quantity;
    String  utilized_date;
    String details;


    }

