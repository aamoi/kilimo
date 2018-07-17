package com.shamba.amoi.shambaapp.db.power;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by amoi on 31/12/2017.
 */
@Dao
public interface PowerSourceDao {

    @Insert
    public void insertPowerSource(PowerSource ps);

    @Update
    public void updatePowerSource(PowerSource...powerSources);

    @Delete
    public void deletePowerSource(PowerSource...powerSources);

    @Query("Select * from powersource")
    public List<PowerSource> getAllPowerSources();

    @Query("Select * from powersource where power_source_id IN (:power_source_id)")
    public List<PowerSource> getPowerSource(int ...power_source_id);
}
