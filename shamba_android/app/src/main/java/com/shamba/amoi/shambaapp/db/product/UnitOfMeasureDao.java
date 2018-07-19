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
public interface UnitOfMeasureDao {
    @Insert
    public void insertUnitOfMeasure(UnitOfMeasure unitOfMeasure) ;

    @Update
    public void updateUnitOfMeasure(UnitOfMeasure unitOfMeasure) ;

    @Delete
    public void deleteUnitOfMeasure(UnitOfMeasure unitOfMeasure) ;

    @Query("select * from unitofmeasure")
    public List<UnitOfMeasure> getAllUnitOfMeasure();

    @Query("select * from unitofmeasure where id in (:id)")
    public List<UnitOfMeasure> getUnitOfMeasureById(int id);

    @Query("select * from unitofmeasure where uom_name in (:uom_name)")
    public List<UnitOfMeasure> getUnitOfMeasureByName(String uom_name);
}
