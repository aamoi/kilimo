package com.shamba.amoi.shambaapp.db.product;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
@Dao
public interface ProductCategoryDao {
    @Insert
    public void insertProductCategory(ProductCategory product) ;

    @Update
    public void updateProductCategory(ProductCategory product) ;

    @Delete
    public void deleteProductCategory(ProductCategory product) ;

    @Query("select * from productcategory")
    public List<ProductCategory> getAllProductCategories();

    @Query("select * from productcategory where id in (:id)")
    public List<Product> getProductCategoryById(int id);

    @Query("select * from productcategory where category_name in (:category_name)")
    public List<Product> getProductCategoryByName(String category_name);
}
