package com.shamba.amoi.shambaapp.db.projects;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by amoi on 09/02/2018.
 */
@Entity
public class PlantingPhase {
    @NonNull
    @PrimaryKey(autoGenerate = false)
    String phase_id;
    String phase_name;
    String stage;
    String phase_comments;

    public String getPhase_id() {
        return phase_id;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getStage() {return stage;}

    public String getPhase_comments() {
        return phase_comments;
    }

    public String getPhase_name() {
        return phase_name;
    }

    public void setPhase_comments(String phase_comments) {
        this.phase_comments = phase_comments;
    }

    public void setPhase_id(String phase_id) {
        this.phase_id = phase_id;
    }

    public void setPhase_name(String phase_name) {
        this.phase_name = phase_name;
    }
}
