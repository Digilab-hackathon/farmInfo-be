package com.example.farmInfo_be.controller;

import com.example.farmInfo_be.enums.Crop;
import com.example.farmInfo_be.service.ExpectedPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/price")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExpectedPriceController {
    private final ExpectedPriceService priceService;

    @GetMapping("/{crop}")
    public Integer getExpectedPrice(@PathVariable Crop crop) {
        System.out.println("ExpectedPriceController.getExpectedPrice");
        System.out.println("crop = " + crop);
        return priceService.calculate(crop);
    }
}
