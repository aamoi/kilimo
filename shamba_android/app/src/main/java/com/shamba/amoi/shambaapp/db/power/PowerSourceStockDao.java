package com.shamba.amoi.shambaapp.db.power;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;

/**
 * Created by amoi on 14/02/2018.
 */
@Dao
public interface PowerSourceStockDao {
    @Insert
    public void insertPowerSourceStock(PowerSourceStock powerSourceStock);

    @Update
    public void updatePowerSourceStock(PowerSourceStock powerSourceStock);

    @Delete
    public void deletePowerSourceStock(PowerSourceStock powerSourceStock);

    @Query("select * from PowerSourcestock")
    public List<PowerSourceStock> getAllPowerSourceStocks();

    @Query("select * from PowerSourcestock where stock_id in (:stock_id)")
    public List<PowerSourceStock> getAStock(int stock_id);

    @Query("select * from PowerSourcestock where power_source_id in (:power_source_id)")
    public List<PowerSourceStock> getPowerSourceStocks(String power_source_id);
}
