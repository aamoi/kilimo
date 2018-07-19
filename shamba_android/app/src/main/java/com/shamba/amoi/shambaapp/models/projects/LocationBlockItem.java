package com.shamba.amoi.shambaapp.models.projects;
/**
 * Created by amoi on 09/02/2018.
 */
    public class LocationBlockItem {
        private int id;
        private int location_id;
        private int block_id;
        private String details;
        private String location_block_name;
        private int acreage;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLocation_id() {
            return location_id;
        }

        public void setLocation_id(int location_id) {
            this.location_id = location_id;
        }

        public int getBlock_id() {
            return block_id;
        }

        public void setBlock_id(int block_id) {
            this.block_id = block_id;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getLocation_block_name() {
            return location_block_name;
        }

        public void setLocation_block_name(String location_block_name) {
            this.location_block_name = location_block_name;
        }

        public int getAcreage() {
            return acreage;
        }

        public void setAcreage(int acreage) {
            this.acreage = acreage;
        }
    }
