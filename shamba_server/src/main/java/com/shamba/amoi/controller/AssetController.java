package com.shamba.amoi.controller;

/**
 * Created by amoi on 09/07/2018.
 */

import com.shamba.amoi.Repository.AssetRepository;
import com.shamba.amoi.Utils.DateUtil;
import com.shamba.amoi.model.Asset;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class AssetController {

    @Autowired
    AssetRepository assetRepository;

//    @RequestMapping("/")
//    public String index() {
//        return "Hit planting controller";
//    }

//    PlantingMockedData blogMockedData = PlantingMockedData.getInstance();

    @GetMapping("/asset")
    public List<Asset> index() {

        List<Asset> assets =assetRepository.findAll(new Sort(Sort.Order.asc("id")));

        System.out.print("AssetController/asset hit. # of assets pooled from db:"+assets!=null?assets.size():0);


        return assets;
    }

    @GetMapping("/asset/{id}")
    public Asset show(@PathVariable String id) {
        int asset_Id = Integer.parseInt(id);
        return assetRepository.getOne(asset_Id);
    }

    @PostMapping("/asset/search")
    public List<Asset> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
//        return plantingRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
        return null;

    }

    @PostMapping("/createAsset")
    public Asset create(@RequestBody Map<String, String> body) {

        String name=body.get("name");
        int capacity_uom_id = Integer.parseInt(body.get("capacity_uom_id"));
        double capacity_quantity=Double.parseDouble(body.get("capacity_quantity"));
        boolean is_serviceable=Boolean.parseBoolean(body.get("is_serviceable"));
        boolean is_active=Boolean.parseBoolean(body.get("is_active"));
        boolean is_decommissioned=Boolean.parseBoolean(body.get("is_decommissioned"));
        int service_type_id = Integer.parseInt(body.get("service_type_id"));
        int service_frequency_uom_id = Integer.parseInt(body.get("service_frequency_uom_id"));
        double service_frequency_quantity = Double.parseDouble(body.get("service_frequency_quantity"));
        int fuel_product_id = Integer.parseInt(body.get("fuel_product_id"));
        Date purchase_date= DateUtil.stringToDate(body.get("purchase_date"));
        double purchase_price=Double.parseDouble(body.get("purchase_price"));
        int vendor_id = Integer.parseInt(body.get("vendor_id"));
        int distributor_id = Integer.parseInt(body.get("distributor_id"));
        int manufacturer_id = Integer.parseInt(body.get("manufacturer_id"));
        int mileage_uom_id = Integer.parseInt(body.get("mileage_uom_id"));
        double total_mileage_quantity=Double.parseDouble(body.get("total_mileage_quantity"));
        Date last_service_date= DateUtil.stringToDate(body.get("last_service_date"));
        double mileage_to_next_service=Double.parseDouble(body.get("mileage_to_next_service"));
        Date next_service_date= DateUtil.stringToDate(body.get("next_service_date"));
        boolean is_serviced_upto_date=Boolean.parseBoolean(body.get("is_serviced_upto_date"));
        String asset_details=body.get("asset_details");

        return assetRepository.save(new Asset(name, capacity_uom_id, capacity_quantity, is_serviceable, is_active,
        is_decommissioned, service_type_id, service_frequency_uom_id, service_frequency_quantity,
        fuel_product_id, purchase_date, purchase_price, vendor_id, distributor_id,
        manufacturer_id, mileage_uom_id, total_mileage_quantity, last_service_date,
        mileage_to_next_service, next_service_date, is_serviced_upto_date, asset_details));

    }

//    @PutMapping("/updatePlanting/{id}")
//    public Planting update(@PathVariable String id, @RequestBody Map<String, String> body) {
//
//        int planting_id = Integer.parseInt(id);
//        Planting planting=plantingRepository.getOne(planting_id);
//
//        String name = body.get("name");
//        planting.setName(name);
//
//        double seed_quantity = Double.parseDouble(body.get("seed_quantity"));
//        planting.setSeed_quantity(seed_quantity);
//        return plantingRepository.save(planting);
////        return null;
//    }

//    @DeleteMapping("/deletePlanting/{id}")
//    public void delete(@PathVariable String id) {
//        int planting_id = Integer.parseInt(id);
//         plantingRepository.deleteById(planting_id);
//    }


}