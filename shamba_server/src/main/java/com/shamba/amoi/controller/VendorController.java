package com.shamba.amoi.controller;

/**
 * Created by amoi on 09/07/2018.
 */

import com.shamba.amoi.Repository.VendorRepository;
import com.shamba.amoi.model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class VendorController {

    @Autowired
    VendorRepository vendorRepository;

//    @RequestMapping("/")
//    public String index() {
//        return "Hit planting controller";
//    }

//    PlantingMockedData blogMockedData = PlantingMockedData.getInstance();

    @GetMapping("/vendor")
    public List<Vendor> index() {
        return vendorRepository.findAll(new Sort(Sort.Order.asc("id")));
    }

    @GetMapping("/vendor/{id}")
    public Vendor show(@PathVariable String id) {
        int vendor_id = Integer.parseInt(id);
        return vendorRepository.getOne(vendor_id);
    }

    @PostMapping("/vendor/search")
    public List<Vendor> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
//        return plantingRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
        return null;

    }

    @PostMapping("/createVendor")
    public Vendor create(@RequestBody Map<String, String> body) {
        String vendor_name = body.get("vendor_name");
        String vendor_phone = body.get("vendor_phone");
        String county = body.get("county");
        String town = body.get("town");
        String map = body.get("map");
        String email = body.get("email");
        String directions = body.get("directions");

        return vendorRepository.save(new Vendor(vendor_name,vendor_phone,county,town,map,email,directions));
    }

    @PutMapping("/updateVendor/{id}")
    public Vendor update(@PathVariable String id, @RequestBody Map<String, String> body) {

        int vendor_id = Integer.parseInt(id);
        Vendor vendor = vendorRepository.getOne(vendor_id);

//        String name = body.get("name");
//        product.setName(name);
//
//        double seed_quantity = Double.parseDouble(body.get("seed_quantity"));
//        planting.setSeed_quantity(seed_quantity);
//        return plantingRepository.save(planting);
        return null;
    }
//
//    @DeleteMapping("/deleteProduct/{id}")
//    public void delete(@PathVariable String id) {
//        int planting_id = Integer.parseInt(id);
//         plantingRepository.deleteById(planting_id);
//    }


}