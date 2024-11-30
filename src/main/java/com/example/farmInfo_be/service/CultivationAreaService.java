package com.example.farmInfo_be.service;

import com.example.farmInfo_be.domain.OptimalCultivationArea;
import com.example.farmInfo_be.enums.Crop;
import org.springframework.stereotype.Service;

@Service
public class CultivationAreaService {

    public void changeCultivationArea(Crop crop, Integer newArea){
        System.out.println("newArea = " + newArea);
        OptimalCultivationArea.OPTIMAL_CULTIVATION_AREA.put(crop, newArea);
    }

    public int get(Crop crop) {
        System.out.println("crop = " + crop);
        return OptimalCultivationArea.OPTIMAL_CULTIVATION_AREA.get(crop);
    }
}
