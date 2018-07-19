package com.shamba.amoi.controller;

/**
 * Created by amoi on 09/07/2018.
 */

import com.shamba.amoi.Repository.ProductRepository;
import com.shamba.amoi.Repository.UnitOfMeasureRepository;
import com.shamba.amoi.model.Planting;
import com.shamba.amoi.model.Product;
import com.shamba.amoi.model.UnitOfMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UnitOfMeasureController {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

//    @RequestMapping("/")
//    public String index() {
//        return "Hit planting controller";
//    }

//    PlantingMockedData blogMockedData = PlantingMockedData.getInstance();

    @GetMapping("/unitOfMeasure")
    public List<UnitOfMeasure> index() {
        return unitOfMeasureRepository.findAll();
    }

    @GetMapping("/unitOfMeasure/{id}")
    public UnitOfMeasure show(@PathVariable String id) {
        int uom_id = Integer.parseInt(id);
        return unitOfMeasureRepository.getOne(uom_id);
    }

    @PostMapping("/unitOfMeasure/search")
    public List<UnitOfMeasure> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
//        return plantingRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
        return null;

    }

    @PostMapping("/createUnitOfMeasure")
    public UnitOfMeasure create(@RequestBody Map<String, String> body) {
        String uom_name = body.get("uom_name");
        String details = body.get("details");
        String symbol = body.get("symbol");

        return unitOfMeasureRepository.save(new UnitOfMeasure(uom_name, details, symbol));
    }

    @PutMapping("/updateUnitOfMeasure/{id}")
    public UnitOfMeasure update(@PathVariable String id, @RequestBody Map<String, String> body) {

        int uom_id = Integer.parseInt(id);
        UnitOfMeasure unitOfMeasure = unitOfMeasureRepository.getOne(uom_id);

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