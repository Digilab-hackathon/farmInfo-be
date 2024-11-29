package com.example.farmInfo_be.dto.request;

import com.example.farmInfo_be.enums.Crop;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OptimalCultivationAreaRequest {
    Crop crop;
    int value;
}
