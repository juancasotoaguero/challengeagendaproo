package com.agendapro.challenge.controller;

import com.agendapro.challenge.bean.comunication.ProductStatisticsResponse;
import com.agendapro.challenge.bean.domain.ProductStatisticsEnum;
import com.agendapro.challenge.service.ProductStatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/category_product")
public class ProductStatisticsController {

    private ProductStatisticsService productStatisticsService;

    @GetMapping("/productByCategory")
    public ResponseEntity<List<ProductStatisticsResponse>> getByName(@RequestParam(required = false) String statistics){
        List<ProductStatisticsResponse> productList = productStatisticsService.getStatistics(statistics);
        return  ResponseEntity.ok(productList);
    }
}
