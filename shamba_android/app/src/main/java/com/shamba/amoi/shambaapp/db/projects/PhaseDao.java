package com.shamba.amoi.shambaapp.db.projects;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by amoi on 23/01/2018.
 */
@Dao
public interface PhaseDao {
    @Insert(onConflict = android.arch.persistence.room.OnConflictStrategy.REPLACE)
    public void insertPhase(Phase phase);

    @Update(onConflict = android.arch.persistence.room.OnConflictStrategy.REPLACE)
    public void updatePhase(Phase phase);

    @Delete
    public void deletePhase(Phase phase);

    @Query("select * from phase")
    public List<Phase> getAllPhases();

    @Query("select * from  phase where id in (:id)")
    public List<Phase> getPhaseById(int id);

    @Query("select * from  phase where phase_name in (:name)")
    public List<Phase> getPhaseByName(String name);

    @Query("select * from  phase where is_dairy_phase in (:dairy)")
    public List<Phase> getDairyPhase(String dairy);

    @Query("select * from  phase where is_planting_phase in (:planting)")
    public List<Phase> getPlantingPhase(String planting);

    @Query("select * from  phase where is_fishing_phase in (:fishing)")
    public List<Phase> getFishingPhase(String fishing);

    @Query("select * from  phase where is_poultry_phase in (:poultry)")
    public List<Phase> getPoultryPhase(String poultry);
}
