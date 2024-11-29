package com.example.farmInfo_be.repository;

import com.example.farmInfo_be.enums.Crop;
import java.util.Map;
import static java.util.Map.entry;

public class CropPricingMap {
    public static final Map<Crop, Integer> cropPricingMap = Map.ofEntries(
            entry(Crop.CABBAGE, 725),
            entry(Crop.BEET, 1940),
            entry(Crop.BROCCOLI, 1080),
            entry(Crop.BRUSSELS_SPROUTS, 13790),
            entry(Crop.CARROT, 2847),
            entry(Crop.GARLIC, 4540),
            entry(Crop.KOHLRABI, 970),
            entry(Crop.RADISH, 1034),
            entry(Crop.ONION, 1080),
            entry(Crop.RED_CABBAGE, 1460),
            entry(Crop.GREEN_ONION, 2797),
            entry(Crop.WINTER_CABBAGE, 745)
    );
}