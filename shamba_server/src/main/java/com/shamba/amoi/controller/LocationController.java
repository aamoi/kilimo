package com.shamba.amoi.controller;

/**
 * Created by amoi on 09/07/2018.
 */
import com.shamba.amoi.Repository.LocationRepository;
import com.shamba.amoi.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class LocationController {

    @Autowired
    LocationRepository locationRepository;

//    @RequestMapping("/")
//    public String index() {
//        return "Hit planting controller";
//    }

//    PlantingMockedData blogMockedData = PlantingMockedData.getInstance();

    @GetMapping("/location")
    public List<Location> index() {
        return locationRepository.
                findAll(new Sort(Sort.Order.asc("id")));
    }

    @GetMapping("/location/{id}")
    public Location show(@PathVariable String id) {
        int l_id = Integer.parseInt(id);
        return locationRepository.getOne(l_id);
    }

    @PostMapping("/location/search")
    public List<Location> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
//        return plantingRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
        return null;
    }

    @PostMapping("/createLocation")
    public Location create(@RequestBody Map<String, String> body) {

        String location_name = body.get("location_name");
        String location_details = body.get("location_details");
        String county = body.get("county");
        String town = body.get("town");
        String map = body.get("map");
        String directions = body.get("directions");

        return locationRepository.save(new Location(location_name, location_details,county,town,map,directions ));
    }

    @PutMapping("/updateLocation/{id}")
    public Location update(@PathVariable String id, @RequestBody Map<String, String> body) {

        int lb_id = Integer.parseInt(id);
        Location location  = locationRepository.getOne(lb_id);

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