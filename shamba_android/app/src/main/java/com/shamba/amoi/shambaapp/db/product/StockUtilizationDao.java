package com.shamba.amoi.shambaapp.db.product;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by amoi on 13/02/2018.
 */
@Dao
public interface StockUtilizationDao {
    @Insert
    public void insertStockUtilization(StockUtilization stockUtilization) ;

    @Update
    public void updateStockUtilization(StockUtilization stockUtilization) ;

    @Delete
    public void deleteStockUtilization(StockUtilization stockUtilization) ;

    @Query("select * from stockutilization")
    public List<StockUtilization> getAllStockUtilization();

    @Query("select * from stockutilization where id in (:id)")
    public List<StockUtilization> getStockUtilizationById(int id);

    @Query("select * from stockutilization where task_id in (:task_id)")
    public List<StockUtilization> getStockUtilizationByTask(int task_id);

    @Query("select * from stockutilization where asset_id in (:asset_id)")
    public List<StockUtilization> getStockUtilizationByAsset(int asset_id);
}
