package com.shamba.amoi.shambaapp.db.inventory;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.shamba.amoi.shambaapp.db.product.StockUtilization;

import java.util.List;

/**
 * Created by amoi on 01/03/2018.
 */
@Dao
public interface InventoryUtilizationDao {
    @Insert
    public void insertStockUtilization(StockUtilization stockUtilization);

    @Update
    public void updateStockUtilization(StockUtilization stockUtilization);

    @Delete
    public void deleteStockUtilization(StockUtilization stockUtilization);

    @Query("select * from stockutilization")
    public List<StockUtilization> getAllStockUtilization();

    @Query("select * from StockUtilization where id in (:id)")
    public List<StockUtilization> getStockUtilizationById(int id);
}
