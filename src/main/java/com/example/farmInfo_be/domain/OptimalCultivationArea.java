package com.example.farmInfo_be.domain;

import com.example.farmInfo_be.enums.Crop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OptimalCultivationArea {
    public static Map<Crop, Integer> OPTIMAL_CULTIVATION_AREA = Arrays.stream(Crop.values())
            .collect(Collectors.toMap(
                    crop -> crop,
                    crop -> 10000
            ));

}
