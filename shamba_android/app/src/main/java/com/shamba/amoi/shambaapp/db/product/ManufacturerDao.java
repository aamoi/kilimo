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
public interface ManufacturerDao {
    @Insert
    public void insertManufacturer(Manufacturer manufacturer) ;

    @Update
    public void updateManufacturer(Manufacturer manufacturer) ;

    @Delete
    public void deleteManufacturer(Manufacturer manufacturer) ;

    @Query("select * from manufacturer")
    public List<Vendor> getAllManufacturer();

    @Query("select * from manufacturer where id in (:id)")
    public List<Vendor> getManufacturerById(int id);

    @Query("select * from manufacturer where manufacturer_name in (:name)")
    public List<Vendor> getManufacturerByName(String name);
}
