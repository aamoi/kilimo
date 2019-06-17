package com.shamba.amoi.shambaapp.db.product;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ProductVarietyDao {

    @Insert
    public void insertProductVariety(ProductVariety productVariety) ;

    @Update
    public void updateProductVariety(ProductVariety productVariety) ;

    @Delete
    public void deleteProductVariety(ProductVariety productVariety) ;

    @Query("select * from productvariety")
    public List<ProductVariety> getAllProductVarietis();

    @Query("select * from ProductVariety where id in (:id)")
    public List<ProductVariety> getProductVarietyById(int id);

    @Query("select * from ProductVariety where variety_name in (:variety_name)")
    public List<ProductVariety> getProductVarietyByName(String variety_name);
}
