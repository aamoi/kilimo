package com.shamba.amoi.shambaapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by amoi on 28/12/2017.
 */
@Dao
public interface StockDao {
    @Insert
    public void insertStock(Stock stock);

    @Update
    public void updateStocks(Stock... stocks);

    @Delete
    public void deleteStocks(Stock... stocks);

    @Query("select * from stock")
    public List<Stock> getAllStocks();

    @Query("SELECT * FROM stock WHERE product_id IN (:product_ids)")
    public List<Stock> getProductStocks(int ...product_ids);
}
