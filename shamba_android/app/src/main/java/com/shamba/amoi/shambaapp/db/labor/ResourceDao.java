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
public interface ResourceDao {
    @Insert
    public void insertResource(Resource resource);

    @Update
    public void updateResource(Resource resource);

    @Delete
    public void deleteResource(Resource resource);

    @Query("select * from Resource")
    public List<Resource> getAllResources();

    @Query("select * from Resource where location_id in (:location_id)")
    public List<Resource> getResourcesByLocation(int location_id);

    @Query("select * from Resource where resource_name in (:resource_name)")
    public List<Resource> getResourcesByName(String resource_name);
}
