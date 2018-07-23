package com.shamba.amoi.controller;

/**
 * Created by amoi on 09/07/2018.
 */

import com.shamba.amoi.Repository.LocationBlockRepository;
import com.shamba.amoi.Repository.ProductRepository;
import com.shamba.amoi.model.LocationBlock;
import com.shamba.amoi.model.Planting;
import com.shamba.amoi.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class LocationBlockController {

    @Autowired
    LocationBlockRepository locationBlockRepository;

//    @RequestMapping("/")
//    public String index() {
//        return "Hit planting controller";
//    }

//    PlantingMockedData blogMockedData = PlantingMockedData.getInstance();

    @GetMapping("/locationBlock")
    public List<LocationBlock> index() {
        return locationBlockRepository.
                findAll(new Sort(Sort.Order.asc("id")));
    }

    @GetMapping("/locationBlock/{id}")
    public LocationBlock show(@PathVariable String id) {
        int lb_id = Integer.parseInt(id);
        return locationBlockRepository.getOne(lb_id);
    }

    @PostMapping("/locationBlock/search")
    public List<LocationBlock> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
//        return plantingRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
        return null;
    }

    @PostMapping("/createLocationBlock")
    public LocationBlock create(@RequestBody Map<String, String> body) {
        String details = body.get("details");
        String location_block_name = body.get("location_block_name");
        int location_id = Integer.parseInt(body.get("location_id"));
        int block_id = Integer.parseInt(body.get("block_id"));
        int acreage = Integer.parseInt(body.get("acreage"));

        return locationBlockRepository.save(new LocationBlock(location_id, block_id,details,location_block_name,acreage ));
    }

    @PutMapping("/updateLocationBlock/{id}")
    public Planting update(@PathVariable String id, @RequestBody Map<String, String> body) {

        int lb_id = Integer.parseInt(id);
        LocationBlock locationBlock = locationBlockRepository.getOne(lb_id);

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