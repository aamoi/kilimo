package com.shamba.amoi.controller;

/**
 * Created by amoi on 09/07/2018.
 */

import com.shamba.amoi.Repository.PhaseRepository;
import com.shamba.amoi.Utils.SortItems;
import com.shamba.amoi.model.Phase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class PhaseController {

    @Autowired
    PhaseRepository phaseRepository;

//    @RequestMapping("/")
//    public String index() {
//        return "Hit planting controller";
//    }

//    PlantingMockedData blogMockedData = PlantingMockedData.getInstance();

    @GetMapping("/phase")
    public List<Phase> index() {
        return phaseRepository.findAll(new Sort(Sort.Order.asc("id")) );
    }

//    public List<Phase> index() {
//    return phaseRepository.findAll( );
//    }

    @GetMapping("/phase/{id}")
    public Phase show(@PathVariable String id) {
        int phase_id = Integer.parseInt(id);
        return phaseRepository.getOne(phase_id);
    }

    @PostMapping("/phase/search")
    public List<Phase> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
//        return plantingRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
        return null;

    }

    @PostMapping("/createPhase")
    public Phase create(@RequestBody Map<String, String> body) {

        String phase_name = body.get("phase_name");
        String phase_details = body.get("phase_details");
        boolean is_planting_phase = Boolean.parseBoolean(body.get("is_planting_phase"));
        boolean is_poultry_phase = Boolean.parseBoolean(body.get("is_poultry_phase"));
        boolean is_fishing_phase = Boolean.parseBoolean(body.get("is_fishing_phase"));
        boolean is_dairy_phase = Boolean.parseBoolean(body.get("is_dairy_phase"));


        return phaseRepository.save(new Phase(phase_name, phase_details, is_planting_phase, is_poultry_phase,
                is_fishing_phase,is_dairy_phase));
    }

    @PutMapping("/updatePhase/{id}")
    public Phase update(@PathVariable String id, @RequestBody Map<String, String> body) {

        int planting_id = Integer.parseInt(id);
        Phase phase = phaseRepository.getOne(planting_id);

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