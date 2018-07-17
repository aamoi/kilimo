package com.shamba.amoi.shambaapp.db.projects;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by amoi on 09/02/2018.
 */
@Entity
public class Crop {
    @PrimaryKey
    int crop_id;
    String crop_name;
    String crop_description;
}
