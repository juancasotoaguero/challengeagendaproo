package com.agendapro.challenge.mapper;


import com.agendapro.challenge.bean.comunication.ProductStatisticsResponse;
import com.agendapro.challenge.entity.ProductStatistics;

public class ProductStatisticsMapper {
    public static ProductStatisticsResponse mapToProductStaticsResponse(ProductStatistics product){
        return new ProductStatisticsResponse(
                product.getId(),
                product.getName(),
                product.getStatistic(),
                product.getQuantity()
        );
    }
}
