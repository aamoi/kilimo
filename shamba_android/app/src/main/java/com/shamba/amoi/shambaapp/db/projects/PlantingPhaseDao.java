package com.shamba.amoi.shambaapp.db.projects;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by amoi on 09/02/2018.
 */
@Dao
public interface PlantingPhaseDao {
    @Insert
    public void insertPlantingPhase(PlantingPhase p);

    @Update
    public void updatePlantingPhase(PlantingPhase ...ps);

    @Delete
    public void deletePlantingPhase(PlantingPhase... ps);

    @Query("select * from PlantingPhase")
    public List<PlantingPhase> getAllPlantingPhase();

    @Query("select * from PlantingPhase where phase_id in (:phase_ids)")
    public List<PlantingPhase> getPlantingPhase(int phase_ids);
}

