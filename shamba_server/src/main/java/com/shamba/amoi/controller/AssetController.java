package com.shamba.amoi.controller;

/**
 * Created by amoi on 09/07/2018.
 */

import com.shamba.amoi.Repository.AssetRepository;
import com.shamba.amoi.Utils.DateUtil;
import com.shamba.amoi.model.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
        return assetRepository.findAll(new Sort(Sort.Order.asc("id")));
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
        String vendor=body.get("vendor");
        int uom_id = Integer.parseInt(body.get("uom_id"));
        double capacity=Double.parseDouble(body.get("capacity"));
        double purchase_price=Double.parseDouble(body.get("purchase_price"));
        String asset_details=body.get("asset_details");
        int service_frequency_in_month=Integer.parseInt(body.get("service_frequency_in_month"));
        String service_description=body.get("service_description");
        String fuel_type=body.get("fuel_type");
        Date purchase_date= DateUtil.stringToDate(body.get("purchase_date"));
        double total_hours_worked=Double.parseDouble(body.get("total_hours_worked"));
        double hours_to_next_service=Double.parseDouble(body.get("hours_to_next_service"));
        boolean is_serviceable=Boolean.parseBoolean(body.get("is_serviceable"));
        boolean is_serviced_upto_date=Boolean.parseBoolean(body.get("is_serviced_upto_date"));
        Date last_service_date= DateUtil.stringToDate(body.get("last_service_date"));
        Date next_service_date=DateUtil.stringToDate(body.get("next_service_date"));

        return assetRepository.save(new Asset(name,vendor, uom_id, capacity,purchase_price, asset_details,
                service_frequency_in_month,service_description, fuel_type,purchase_date, total_hours_worked,
                hours_to_next_service, is_serviceable, last_service_date,next_service_date,is_serviced_upto_date));

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