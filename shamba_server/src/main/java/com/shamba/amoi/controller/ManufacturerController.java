package com.shamba.amoi.controller;

/**
 * Created by amoi on 09/07/2018.
 */

import com.shamba.amoi.Repository.ManufacturerRepository;
import com.shamba.amoi.Repository.VendorRepository;
import com.shamba.amoi.model.Manufacturer;
import com.shamba.amoi.model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ManufacturerController {

    @Autowired
    ManufacturerRepository manufacturerRepository;

//    @RequestMapping("/")
//    public String index() {
//        return "Hit planting controller";
//    }

//    PlantingMockedData blogMockedData = PlantingMockedData.getInstance();

    @GetMapping("/manufacturer")
    public List<Manufacturer> index() {
        return manufacturerRepository.findAll();
    }

    @GetMapping("/manufacturer/{id}")
    public Manufacturer show(@PathVariable String id) {
        int man_id = Integer.parseInt(id);
        return manufacturerRepository.getOne(man_id);
    }

    @PostMapping("/manufacturer/search")
    public List<Manufacturer> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
//        return plantingRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
        return null;

    }

    @PostMapping("/createManufacturer")
    public Manufacturer create(@RequestBody Map<String, String> body) {

        String manufacturer_name = body.get("manufacturer_name");
        String phone = body.get("phone");
        String email = body.get("email");
        String map = body.get("map");
        String directions = body.get("directions");
        String county = body.get("county");
        String town = body.get("town");

        String details = body.get("details");

        return manufacturerRepository.save(new Manufacturer(manufacturer_name,phone,email,map,directions,
                county,town,details));
    }

    @PutMapping("/updateManufacturer/{id}")
    public Manufacturer update(@PathVariable String id, @RequestBody Map<String, String> body) {

        int man_id = Integer.parseInt(id);
        Manufacturer manufacturer = manufacturerRepository.getOne(man_id);

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