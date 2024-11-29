package com.example.farmInfo_be.dto.request;

import lombok.Data;

@Data
public class OptimalCultivationAreaRequest {
    String crop;
    int value;
}
