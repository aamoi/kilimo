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
public interface AssetFuelingDao {

    @Insert
    public void insertAssetFueling(AssetFueling assetFueling);

    @Update
    public void updateAssetFueling(AssetFueling assetFueling);

    @Delete
    public void deleteAssetFueling(AssetFueling... assetFuelings);

    @Query("select * from assetfueling")
    public List<AssetFueling> getAllAssetFuelings(  );

    @Query("select * from assetfueling where asset_fueling_id IN (:asset_fueling_ids)")
    public List<AssetFueling> getAllAssetFueling(String ...asset_fueling_ids);
}
