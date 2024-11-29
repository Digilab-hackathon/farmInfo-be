package com.example.farmInfo_be.controller;

import com.example.farmInfo_be.dto.request.OptimalCultivationAreaRequest;
import com.example.farmInfo_be.enums.Crop;
import com.example.farmInfo_be.service.CultivationAreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cultivation-area")
@RequiredArgsConstructor
public class CultivationAreaController {

    private final CultivationAreaService cultivationAreaService;

    @Operation(summary = "적정 재배 면적 수정", description = "적정 재배 면적 수정")
    @PatchMapping
    public ResponseEntity<Void> updateOptimalCultivationArea(
           @RequestBody OptimalCultivationAreaRequest optimalCultivationAreaRequest
    ) {
        cultivationAreaService.changeCultivationArea(Crop.valueOf(optimalCultivationAreaRequest.getCrop()), optimalCultivationAreaRequest.getValue());
        return ResponseEntity.ok().build();
    }
}
