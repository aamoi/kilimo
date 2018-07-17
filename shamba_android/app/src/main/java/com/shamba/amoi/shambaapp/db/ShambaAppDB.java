package com.shamba.amoi.shambaapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.shamba.amoi.shambaapp.db.assets.Asset;
import com.shamba.amoi.shambaapp.db.assets.AssetDao;
import com.shamba.amoi.shambaapp.db.assets.AssetFueling;
import com.shamba.amoi.shambaapp.db.assets.AssetFuelingDao;
import com.shamba.amoi.shambaapp.db.inventory.InventoryUtilization;
import com.shamba.amoi.shambaapp.db.inventory.InventoryUtilizationDao;
import com.shamba.amoi.shambaapp.db.inventory.ProductStock;
import com.shamba.amoi.shambaapp.db.inventory.ProductStockDao;
import com.shamba.amoi.shambaapp.db.labor.HumanResource;
import com.shamba.amoi.shambaapp.db.labor.HumanResourceDao;
import com.shamba.amoi.shambaapp.db.labor.TaskAssignment;
import com.shamba.amoi.shambaapp.db.labor.TaskAssignmentDao;
import com.shamba.amoi.shambaapp.db.labor.TaskPayment;
import com.shamba.amoi.shambaapp.db.labor.TaskPaymentDao;
import com.shamba.amoi.shambaapp.db.power.PowerSource;
import com.shamba.amoi.shambaapp.db.power.PowerSourceDao;
import com.shamba.amoi.shambaapp.db.power.PowerSourceStock;
import com.shamba.amoi.shambaapp.db.power.PowerSourceStockDao;
import com.shamba.amoi.shambaapp.db.projects.MasterPlantingPlan;
import com.shamba.amoi.shambaapp.db.projects.MasterPlantingPlanDao;
import com.shamba.amoi.shambaapp.db.projects.PlantingPhase;
import com.shamba.amoi.shambaapp.db.projects.PlantingPhaseDao;
import com.shamba.amoi.shambaapp.db.projects.PlantingProgram;
import com.shamba.amoi.shambaapp.db.projects.PlantingProgramDao;
import com.shamba.amoi.shambaapp.db.projects.Task;
import com.shamba.amoi.shambaapp.db.projects.TaskDao;

/**
 * Created by amoi on 24/12/2017.
 */

@Database(entities ={Product.class,Stock.class, MasterPlantingPlan.class,
        Asset.class,PlantingProgram.class, HumanResource.class,
        PowerSource.class, Task.class, PlantingPhase.class, ProductStock.class,
        TaskAssignment.class, InventoryUtilization.class, TaskPayment.class, PowerSourceStock.class,
        AssetFueling.class},
        version = 4)
public abstract class ShambaAppDB extends RoomDatabase {
    public abstract ProductDao productDao();
    public abstract StockDao stockDao();
    public abstract MasterPlantingPlanDao masterPlantPlanDao();
    public abstract PlantingProgramDao plantingProgramDao();
    public abstract AssetDao assetDao();
    public abstract HumanResourceDao humanResourceDao();
    public abstract PowerSourceDao powerSourceDao();
    public abstract TaskDao taskDao();
    public abstract PlantingPhaseDao plantingPhaseDao();
    public abstract ProductStockDao productStockDao();
    public abstract TaskAssignmentDao taskAssignmentDao();
    public abstract InventoryUtilizationDao inventoryUtilizationDao();
    public abstract TaskPaymentDao taskPaymentDao();
    public abstract PowerSourceStockDao powerSourceStockDao();
    public abstract AssetFuelingDao assetFuelingDao();
}

