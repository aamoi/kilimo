package com.shamba.amoi.shambaapp.db.projects;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import java.util.List;

/**
 * Created by amoi on 30/12/2017.
 */
@Dao
public interface MasterPlantingPlanDao {
    @Insert
    public void insertMasterPlantingPlan(MasterPlantingPlan p);

    @Update
    public void updateMasterPlantingPlans(MasterPlantingPlan... Plans);

    @Delete
    public void deleteMasterPlantingPlans(MasterPlantingPlan...plans);

    @Query("select * from masterplantingplan")
    public List<MasterPlantingPlan> getAllPlans();

    @Query("SELECT * FROM masterplantingplan WHERE plan_id IN (:plan_ids)")
    public List<MasterPlantingPlan> getMasterPlantingPlan(int ...plan_ids);}
