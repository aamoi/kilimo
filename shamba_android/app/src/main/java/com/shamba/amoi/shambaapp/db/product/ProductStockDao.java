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
public interface ProductStockDao {
    @Insert
    public void insertProductStock(ProductStock productStock) ;

    @Update
    public void updateProductStock(ProductStock productStock) ;

    @Delete
    public void deleteProductStock(ProductStock productStock) ;

    @Query("select * from productstock")
    public List<ProductStock> getAllProductStock();

    @Query("select * from productstock where id in (:id)")
    public List<ProductStock> getProductStockById(int id);

    @Query("select * from productstock where product_id in (:product_id)")
    public List<ProductStock> getProductStockByProduct(int product_id);

    @Query("select * from productstock where location_id in (:location_id)")
    public List<ProductStock> getProductStockByLocation(int location_id);

    @Query("select * from productstock where vendor_id in (:vendor_id)")
    public List<ProductStock> getProductStockByVendor(int vendor_id);

    @Query("select * from productstock where distributor_id in (:distributor_id)")
    public List<ProductStock> getProductStockByDistributor(int distributor_id);

    @Query("select * from productstock where manufacturer_id in (:manufacturer_id)")
    public List<ProductStock> getProductStockByManufacturer(int manufacturer_id);
}
