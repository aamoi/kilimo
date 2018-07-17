package com.shamba.amoi.shambaapp.db.inventory;

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
public interface ProductStockDao {
    @Insert
    public void insertProductStock(ProductStock productStock);

    @Update
    public void updateProductStock(ProductStock productStock);

    @Delete
    public void deleteProductStock(ProductStock productStock);

    @Query("select * from productstock")
    public List<ProductStock> getAllProductStocks();

    @Query("select * from productstock where stock_id in (:stock_id)")
    public List<ProductStock> getAStock(int stock_id);

    @Query("select * from productstock where product_id in (:product_id)")
    public List<ProductStock> getProductStocks(String product_id);
}
