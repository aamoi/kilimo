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
public interface VendorDao {
    @Insert
    public void insertVendor(Vendor vendor) ;

    @Update
    public void updateVendor(Vendor vendor) ;

    @Delete
    public void deleteVendor(Vendor vendor) ;

    @Query("select * from vendor")
    public List<Vendor> getAllVendor();

    @Query("select * from vendor where id in (:id)")
    public List<Vendor> getVendorById(int id);

    @Query("select * from vendor where vendor_name in (:name)")
    public List<Vendor> getVendorByName(String name);
}
