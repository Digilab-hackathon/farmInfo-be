package com.example.farmInfo_be.controller;

import com.example.farmInfo_be.enums.Crop;
import com.example.farmInfo_be.service.ExpectedPriceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/price")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExpectedPriceController {
    private final ExpectedPriceService priceService;

    @Operation(summary = "특정 Crop의 예상가격(ai 서버 아직 안떠서 더미 더미 데이터)",description = "특정 Crop의 예상가격 (ai 서버 아직 안뜸)")
    @GetMapping("/{crop}")
    public Integer getExpectedPrice(@PathVariable Crop crop) {
        System.out.println("ExpectedPriceController.getExpectedPrice");
        System.out.println("crop = " + crop);
        return priceService.calculate(crop);
    }
}
