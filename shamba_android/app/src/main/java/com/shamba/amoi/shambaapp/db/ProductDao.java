package com.shamba.amoi.shambaapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by amoi on 24/12/2017.
 */

@Dao
public interface ProductDao {
    @Insert
    public void insertProduct(Product p);

    @Update
    public void updateProduct(Product...products );

    @Delete
    public void deleteProduct(Product ... products);

    @Query("Select * from product")
    public List<Product> getProducts();
}
