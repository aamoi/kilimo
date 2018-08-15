package com.shamba.amoi.controller;

/**
 * Created by amoi on 09/07/2018.
 */

import com.shamba.amoi.Repository.ServiceTypeRepository;
import com.shamba.amoi.model.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class ServiceTypeController {

    @Autowired
    ServiceTypeRepository serviceTypeRepository;

    @GetMapping("/serviceType")
    public List<ServiceType> index() {
        List<ServiceType> serviceTypes =serviceTypeRepository.findAll(new Sort(Sort.Order.asc("id")));

        System.out.print("AssetController/asset hit. # of assets pooled from db:"+serviceTypes!=null?
                serviceTypes.size():0);
        return serviceTypes;
    }

    @GetMapping("/serviceType/{id}")
    public ServiceType show(@PathVariable String id) {
        int asset_Id = Integer.parseInt(id);
        return serviceTypeRepository.getOne(asset_Id);
    }

    @PostMapping("/serviceType/search")
    public List<ServiceType> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
//        return plantingRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
        return null;

    }

    @PostMapping("/createServiceType")
    public ServiceType create(@RequestBody Map<String, String> body) {
        String name=body.get("name");
        String details=body.get("details");

        return serviceTypeRepository.save(new ServiceType(name, details));
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