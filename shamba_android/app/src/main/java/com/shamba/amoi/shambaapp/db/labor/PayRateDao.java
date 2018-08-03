package com.shamba.amoi.shambaapp.db.labor;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by amoi on 31/12/2017.
 */

@Dao
public interface PayRateDao {
    @Insert
    public void insertPayRate(PayRate payRate);

    @Update
    public void updatePayRate(PayRate payRate);

    @Delete
    public void deletePayRate(PayRate payRate);

    @Query("select * from PayRate")
    public List<PayRate> getAllPayRates();

    @Query("select * from PayRate where id in (:id)")
    public List<PayRate> getPayRateById( int id);

    @Query("select * from PayRate where name in (:name)")
    public List<PayRate> getPayRateByName(String name);
}
