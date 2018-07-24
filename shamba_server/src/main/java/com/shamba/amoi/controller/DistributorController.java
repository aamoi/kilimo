package com.shamba.amoi.controller;

/**
 * Created by amoi on 09/07/2018.
 */

import com.shamba.amoi.Repository.DistributorRepository;
import com.shamba.amoi.Repository.ManufacturerRepository;
import com.shamba.amoi.model.Distributor;
import com.shamba.amoi.model.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class DistributorController {

    @Autowired
    DistributorRepository distributorRepository;

//    @RequestMapping("/")
//    public String index() {
//        return "Hit planting controller";
//    }

//    PlantingMockedData blogMockedData = PlantingMockedData.getInstance();

    @GetMapping("/distributor")
    public List<Distributor> index() {
        return distributorRepository.
                findAll(new Sort(Sort.Order.asc("id")));
    }

    @GetMapping("/distributor/{id}")
    public Distributor show(@PathVariable String id) {
        int dist_id = Integer.parseInt(id);
        return distributorRepository.getOne(dist_id);
    }

    @PostMapping("/distributor/search")
    public List<Distributor> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
//        return plantingRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
        return null;

    }

    @PostMapping("/createDistributor")
    public Distributor create(@RequestBody Map<String, String> body) {

        String distributor_name = body.get("distributor_name");
        String phone = body.get("phone");
        String email = body.get("email");
        String map = body.get("map");
        String directions = body.get("directions");
        String county = body.get("county");
        String town = body.get("town");
        String details = body.get("details");

        return distributorRepository.save(new Distributor(distributor_name,phone,email,map,directions,
                county,town,details));
    }

    @PutMapping("/updateDistributor/{id}")
    public Distributor update(@PathVariable String id, @RequestBody Map<String, String> body) {

        int dist_id = Integer.parseInt(id);
        Distributor distributor = distributorRepository.getOne(dist_id);

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