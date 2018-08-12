package com.shamba.amoi.shambaapp.db.assets;

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
public interface AssetDao {
    @Insert
    public void insertAsset(Asset asset);

    @Update
    public void updateAsset(Asset ...assets);

    @Delete
    public void deleteAssets(Asset ...assets);

    @Query("select * from asset")
    public List<Asset> getAllAssets(  );

    @Query("select * from asset where id IN (:id)")
    public List<Asset> getAssetById(int id);

    @Query("select * from asset where name IN (:name)")
    public List<Asset> getAssetByName(String  name);
}
