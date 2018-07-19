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
public interface ProductDao {
    @Insert
    public void insertProduct(Product product) ;

    @Update
    public void updateProduct(Product product) ;

    @Delete
    public void deleteProduct(Product product) ;

    @Query("select * from product")
    public List<Product> getAllProducts();

    @Query("select * from product where id in (:id)")
    public List<Product> getProductById(int id);

    @Query("select * from product where product_name in (:name)")
    public List<Product> getProductByName(String name);
}
