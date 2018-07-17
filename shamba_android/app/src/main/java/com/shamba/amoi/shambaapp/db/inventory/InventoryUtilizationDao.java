package com.shamba.amoi.shambaapp.db.inventory;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by amoi on 01/03/2018.
 */
@Dao
public interface InventoryUtilizationDao {
    @Insert
    public void insertInventoryUtilization(InventoryUtilization inventoryUtilization);

    @Update
    public void updateInventoryUtilization(InventoryUtilization inventoryUtilization);

    @Delete
    public void deleteInventoryUtilization(InventoryUtilization inventoryUtilization);

    @Query("select * from inventoryutilization")
    public List<InventoryUtilization> getAllInventoryUtilization();

    @Query("select * from inventoryutilization where utilization_id in (:utilization_id)")
    public List<InventoryUtilization> getAnInventoryUtilization(String utilization_id);
}
