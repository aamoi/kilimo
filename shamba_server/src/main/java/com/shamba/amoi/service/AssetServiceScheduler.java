package com.shamba.amoi.service;

import com.shamba.amoi.Repository.AssetRepository;
import com.shamba.amoi.Repository.AssetServiceRepository;
import com.shamba.amoi.model.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amoi on 8/20/18.
 */
@Service
public class AssetServiceScheduler {

    String class_name="AssetServiceScheduler";

    @Autowired
    AssetRepository assetRepository;

    @Autowired
    AssetServiceRepository assetServiceRepository;

    @Scheduled(cron = " 0 *  * *  *  * ")
    public void scheduleTaskWithCronExpression() {

        List<Asset> assetList=new ArrayList<>();
        assetList=assetRepository.findAll();

        for(int i=0;i<assetList.size();++i){}

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        System.out.println(class_name+"|scheduleTaskWithCronExpression()"+"|schedule executed!!");
        System.out.println("Time executed: " + dtf.format(now));
    }
}
