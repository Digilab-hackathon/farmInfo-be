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
        System.out.println(optimalCultivationAreaRequest.toString());
        cultivationAreaService.changeCultivationArea(optimalCultivationAreaRequest.getCrop(), optimalCultivationAreaRequest.getValue());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "적정 재배 면적 조회", description = "적정 재배 면적 조회")
    @GetMapping("/{crop}")
    public ResponseEntity<Integer> geteOptimalCultivationArea(
            @RequestParam String crop
    ) {
        System.out.println(crop);
        return ResponseEntity.ok(cultivationAreaService.get(Crop.valueOf(crop)));
    }
}
