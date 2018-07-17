//package com.shamba.amoi.MockedDate;
//
///**
// * Created by amoi on 09/07/2018.
// */
//import com.shamba.amoi.model.PlantingModel;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class PlantingMockedData {
//    //list of blog posts
//    private List<PlantingModel> plantings;
//
//    private static PlantingMockedData instance = null;
//    public static PlantingMockedData getInstance(){
//        if(instance == null){
//            instance = new PlantingMockedData();
//        }
//        return instance;
//    }
//
//
//    public PlantingMockedData(){
//        plantings = new ArrayList<PlantingModel>();
//        plantings.add(new PlantingModel(1,"Kangemi-FB",10,100.0,"2018/08/08","2018/08/08","2018/08/08","2018/08/08",
//                "2018/08/08",8,6,569.90,908.90));
//
//        plantings.add(new PlantingModel(1,"Kwa-George",10,100.0,"2018/08/08","2018/08/08","2018/08/08","2018/08/08",
//                "2018/08/08",8,6,569.90,908.90));
//    }
//
//    // return all blogs
//    public List<PlantingModel> fetchPlantings() {
//        return plantings;
//    }
//
//    // return blog by id
//    public PlantingModel getBlogById(int id) {
//        for(PlantingModel b: plantings) {
//            if(b.getId() == id) {
//                return b;
//            }
//        }
//        return null;
//    }
//
//    // search blog by text
//    public List<PlantingModel> searchPlantings(String searchTerm) {
//        List<PlantingModel> searchedBlogs = new ArrayList<PlantingModel>();
//        for(PlantingModel b: plantings) {
//            if(b.getName().toLowerCase().contains(searchTerm.toLowerCase()) ||
//                    b.getPreparation_String().toLowerCase().contains(searchTerm.toLowerCase())) {
//                searchedBlogs.add(b);
//            }
//        }
//
//        return searchedBlogs;
//    }
//
//    // create blog
//    public PlantingModel createPlanting(int id, String name, int crop_type_id, double seed_quantity, String prep_date,
//                                        String seed_bed, String transplant_date, String harvest_date,String sales_date,
//                                        int location_id, int block_id,double cost, double revenue ) {
//        PlantingModel newPlanting = new PlantingModel(id,name,crop_type_id,seed_quantity,prep_date,seed_bed,transplant_date,
//                harvest_date,sales_date,location_id,block_id,cost,revenue);
//        plantings.add(newPlanting);
//        return newPlanting;
//    }
//
//    // update blog
//    public PlantingModel updatePlanting(int id, String name, String seed_bed) {
//        for(PlantingModel b: plantings) {
//            if(b.getId() == id) {
//                int blogIndex = plantings.indexOf(b);
//                b.setName(name);
//                b.setSeedbed_String(seed_bed);
//                plantings.set(blogIndex, b);
//                return b;
//            }
//
//        }
//
//        return null;
//    }
//
//    // delete blog by id
//    public boolean delete(int id){
//        int blogIndex = -1;
//        for(PlantingModel b: plantings) {
//            if(b.getId() == id) {
//                blogIndex = plantings.indexOf(b);
//                continue;
//            }
//        }
//        if(blogIndex > -1){
//            plantings.remove(blogIndex);
//        }
//        return true;
//    }
//
//}