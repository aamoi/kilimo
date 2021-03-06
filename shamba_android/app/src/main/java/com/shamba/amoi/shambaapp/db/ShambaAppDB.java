package com.shamba.amoi.shambaapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.shamba.amoi.shambaapp.db.assets.Asset;
import com.shamba.amoi.shambaapp.db.assets.AssetDao;
import com.shamba.amoi.shambaapp.db.inventory.InventoryUtilization;
import com.shamba.amoi.shambaapp.db.inventory.InventoryUtilizationDao;
import com.shamba.amoi.shambaapp.db.labor.PayRate;
import com.shamba.amoi.shambaapp.db.labor.PayRateDao;
import com.shamba.amoi.shambaapp.db.labor.PaymentDao;
import com.shamba.amoi.shambaapp.db.labor.Resource;
import com.shamba.amoi.shambaapp.db.labor.ResourceDao;
import com.shamba.amoi.shambaapp.db.labor.TaskAssignment;
import com.shamba.amoi.shambaapp.db.labor.TaskAssignmentDao;
import com.shamba.amoi.shambaapp.db.labor.Payment;
import com.shamba.amoi.shambaapp.db.power.PowerSource;
import com.shamba.amoi.shambaapp.db.power.PowerSourceDao;
import com.shamba.amoi.shambaapp.db.power.PowerSourceStock;
import com.shamba.amoi.shambaapp.db.power.PowerSourceStockDao;
import com.shamba.amoi.shambaapp.db.product.Product;
import com.shamba.amoi.shambaapp.db.product.ProductDao;
import com.shamba.amoi.shambaapp.db.product.ProductStock;
import com.shamba.amoi.shambaapp.db.product.ProductStockDao;
import com.shamba.amoi.shambaapp.db.product.ProductVariety;
import com.shamba.amoi.shambaapp.db.product.ProductVarietyDao;
import com.shamba.amoi.shambaapp.db.product.StockUtilization;
import com.shamba.amoi.shambaapp.db.product.StockUtilizationDao;
import com.shamba.amoi.shambaapp.db.projects.MasterPlantingPlan;
import com.shamba.amoi.shambaapp.db.projects.MasterPlantingPlanDao;
import com.shamba.amoi.shambaapp.db.projects.Phase;
import com.shamba.amoi.shambaapp.db.projects.PhaseDao;
import com.shamba.amoi.shambaapp.db.projects.PlantingPhase;
import com.shamba.amoi.shambaapp.db.projects.PlantingPhaseDao;
import com.shamba.amoi.shambaapp.db.projects.PlantingProgram;
import com.shamba.amoi.shambaapp.db.projects.PlantingProgramDao;
import com.shamba.amoi.shambaapp.db.projects.Task;
import com.shamba.amoi.shambaapp.db.projects.TaskDao;
/**
 * Created by amoi on 24/12/2017.
 */
@Database(entities ={Product.class,MasterPlantingPlan.class,
        Asset.class,PlantingProgram.class, Resource.class,
        PowerSource.class, Task.class,PlantingPhase.class, ProductStock.class, StockUtilization.class,
        TaskAssignment.class, InventoryUtilization.class, Payment.class, PowerSourceStock.class,
        Phase.class, PayRate.class, ProductVariety.class},
        version = 4)
public abstract class ShambaAppDB extends RoomDatabase {
    public abstract ProductDao productDao();
    public abstract MasterPlantingPlanDao masterPlantPlanDao();
    public abstract PlantingProgramDao plantingProgramDao();
    public abstract AssetDao assetDao();
    public abstract ResourceDao resourceDao();
    public abstract PowerSourceDao powerSourceDao();
    public abstract TaskDao taskDao();
    public abstract PlantingPhaseDao plantingPhaseDao();
    public abstract TaskAssignmentDao taskAssignmentDao();
    public abstract InventoryUtilizationDao inventoryUtilizationDao();
    public abstract PaymentDao paymentDao();
    public abstract PowerSourceStockDao powerSourceStockDao();
    public abstract PhaseDao phaseDao();
    public abstract ProductStockDao productStockDao();
    public abstract StockUtilizationDao stockUtilizationDao();
    public abstract PayRateDao payRateDao();
    public abstract ProductVarietyDao productVarietyDao();
}

