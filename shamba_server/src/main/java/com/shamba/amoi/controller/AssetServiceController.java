package com.shamba.amoi.controller;

/**
 * Created by amoi on 09/07/2018.
 */

import com.shamba.amoi.Repository.AssetServiceRepository;
import com.shamba.amoi.Utils.DateUtil;
import com.shamba.amoi.model.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class AssetServiceController {
    
    String class_name=getClass().getSimpleName();

    @Autowired
    AssetServiceRepository assetServiceRepository;

    @GetMapping("/assetService")
    public List<AssetService> index() {

        List<AssetService> assetServices =assetServiceRepository.findAll(new Sort(Sort.Order.asc("id")));

        System.out.print(class_name+"|index/ hit. # of asset services pooled from db:"+
                assetServices!=null?assetServices.size():0);

        return assetServices;
    }

    @GetMapping("/assetService/{id}")
    public AssetService show(@PathVariable String id) {
        int asset_service_Id = Integer.parseInt(id);
        
        AssetService assetServices=assetServiceRepository.getOne(asset_service_Id);

        System.out.print(class_name+"|assetService/ hit. # of asset services pooled from db:"+
                assetServices!=null?assetServices.toString():null);
        return assetServices;
    }

    @PostMapping("/assetService/search")
    public List<AssetService> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
//        return plantingRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
        return null;
    }

    @PostMapping("/createAssetService")
    public AssetService create(@RequestBody Map<String, String> body) {

        int asset_id=Integer.parseInt(body.get("asset_id"));
        int service_type_id=Integer.parseInt(body.get("service_type_id"));
        int service_provider_id=Integer.parseInt(body.get("service_provider_id"));
        Date planned_service_start_date= DateUtil.stringToDate(body.get("planned_service_start_date"));
        Date planned_service_end_date= DateUtil.stringToDate(body.get("planned_service_end_date"));
        Date actual_service_start_date= DateUtil.stringToDate(body.get("actual_service_start_date"));
        Date actual_service_end_date= DateUtil.stringToDate(body.get("actual_service_end_date"));
        int pay_rate_id=Integer.parseInt(body.get("pay_rate_id"));
        double work_size=Double.parseDouble(body.get("work_size"));
        double service_cost=Double.parseDouble(body.get("service_cost"));
        boolean service_completed=Boolean.parseBoolean(body.get("service_completed"));
        String details=body.get("details");

        return assetServiceRepository.save(new AssetService( asset_id,service_type_id,service_provider_id,
        planned_service_start_date,planned_service_end_date,actual_service_start_date,actual_service_end_date,
        pay_rate_id,work_size,service_cost,service_completed,details));
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