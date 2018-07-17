package com.shamba.amoi.shambaapp.db.projects;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by amoi on 30/12/2017.
 */
@Dao
public interface PlantingProgramDao {
    @Insert
    public void insertPlantingProgram(PlantingProgram p);

    @Update
    public void updatePlantingProgram(PlantingProgram ...ps);

    @Delete
    public void deletePlantingProgram(PlantingProgram... ps);

    @Query("select * from PlantingProgram")
    public List<PlantingProgram> getAllPlantingPrograms();

    @Query("select * from plantingprogram where plan_id in (:plan_ids)")
    public List<PlantingProgram> getPlantingProgram(int plan_ids);
}
