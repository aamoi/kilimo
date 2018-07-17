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
public interface HumanResourceDao {
    @Insert
    public void insertHumanResource(HumanResource hr);

    @Update
    public void updateHumanResources(HumanResource hrs);

    @Delete
    public void deleteHumanResource(HumanResource hrs);

    @Query("select * from humanresource")
    public List<HumanResource> getAllHumanResource();

    @Query("select * from humanresource where resource_id in (:hr_ids)")
    public List<HumanResource> getHumanResource(String hr_ids);
}
