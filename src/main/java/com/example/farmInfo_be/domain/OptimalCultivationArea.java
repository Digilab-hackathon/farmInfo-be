package com.example.farmInfo_be.domain;

import com.example.farmInfo_be.enums.Crop;

import java.util.HashMap;
import java.util.Map;

public class OptimalCultivationArea {
    public static Map<Crop, Integer> OPTIMAL_CULTIVATION_AREA = initializeMap();

    private static Map<Crop, Integer> initializeMap() {
        Map<Crop, Integer> map = new HashMap<>();

        // 무만 5000으로 설정
        map.put(Crop.RADISH, 700);

        // 나머지는 모두 1000으로 설정
        for (Crop crop : Crop.values()) {
            if (crop != Crop.RADISH) {
                map.put(crop, 10000);
            }
        }

        return map;
    }
}