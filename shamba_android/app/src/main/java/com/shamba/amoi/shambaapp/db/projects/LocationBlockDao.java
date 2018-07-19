package com.shamba.amoi.shambaapp.db.projects;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.shamba.amoi.shambaapp.db.product.Product;

import java.util.List;

/**
 * Created by amoi on 13/02/2018.
 */
@Dao
public interface LocationBlockDao {
    @Insert
    public void insertLocation(LocationBlock location) ;

    @Update
    public void updateLocation(LocationBlock location) ;

    @Delete
    public void deleteLocation(LocationBlock location) ;

    @Query("select * from locationblock")
    public List<LocationBlock> getAllLocations();

    @Query("select * from locationblock where id in (:id)")
    public List<Product> getLocationById(int id);

    @Query("select * from locationblock where location_block_name in (:name)")
    public List<Product> getLocationByName(String name);
}
