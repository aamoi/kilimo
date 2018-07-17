package com.shamba.amoi.shambaapp.db.power;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by amoi on 31/12/2017.
 */

@Entity
public class PowerSource {
    @PrimaryKey(autoGenerate = true)
    private int power_source_id;
    private String power_source_name;
    private String power_source_unitOfMeasure;
    private String power_source_use;
    private String comments_on_power_source;

    public void setPower_source_id(int power_source_id) {
        this.power_source_id = power_source_id;
    }

    public int getPower_source_id() {
        return power_source_id;
    }

    public void setComments_on_power_source(String comments_on_power_source) {
        this.comments_on_power_source = comments_on_power_source;
    }

    public void setPower_source_unitOfMeasure(String power_source_unitOfMeasure) {
        this.power_source_unitOfMeasure = power_source_unitOfMeasure;
    }

    public void setPower_source_name(String power_source_name) {
        this.power_source_name = power_source_name;
    }

    public void setPower_source_use(String power_source_use) {
        this.power_source_use = power_source_use;
    }

    public String getComments_on_power_source() {
        return comments_on_power_source;
    }

    public String getPower_source_name() {
        return power_source_name;
    }

    public String getPower_source_unitOfMeasure() {
        return power_source_unitOfMeasure;
    }

    public String getPower_source_use() {
        return power_source_use;
    }
}
