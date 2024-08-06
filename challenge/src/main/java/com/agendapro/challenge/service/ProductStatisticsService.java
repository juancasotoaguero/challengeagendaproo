package com.agendapro.challenge.service;

import com.agendapro.challenge.bean.comunication.ProductStatisticsResponse;
import com.agendapro.challenge.bean.domain.ProductStatisticsEnum;

import java.util.List;

public interface ProductStatisticsService {
    void insertProductStatisticsProductsByCategory(ProductStatisticsEnum productStatisticsEnum, String categoryName);
    List<ProductStatisticsResponse> getStatistics(String statistic);
}
