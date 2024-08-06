package com.agendapro.challenge.service.impl;

import com.agendapro.challenge.bean.comunication.ProductStatisticsResponse;
import com.agendapro.challenge.bean.domain.ProductStatisticsEnum;
import com.agendapro.challenge.entity.ProductStatistics;
import com.agendapro.challenge.mapper.ProductStatisticsMapper;
import com.agendapro.challenge.repository.ProductStatisticsRepository;
import com.agendapro.challenge.service.ProductStatisticsService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ProductStatisticsServiceImpl implements ProductStatisticsService {

    private  ProductStatisticsRepository productStatisticsRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public void insertProductStatisticsProductsByCategory(ProductStatisticsEnum productStatisticsEnum, String categoryName) {
        LOGGER.info("Starting Insert product statistics products by category");

        ProductStatistics productStatistics = new ProductStatistics();
        productStatistics.setStatistic(productStatisticsEnum.name());
        productStatistics.setName(productStatistics.getStatistic()+" / "+ categoryName );
        Optional<ProductStatistics> productStatisticsFindExist = productStatisticsRepository.findByStatisticAndName(productStatistics.getStatistic(), productStatistics.getName());
        if(productStatisticsFindExist.isPresent()){
            productStatistics = productStatisticsFindExist.get();
            productStatistics.setQuantity(productStatistics.getQuantity()+1);
            productStatistics.setStatus("A");
        }else {
            productStatistics.setQuantity(1);
        }
        productStatisticsRepository.save(productStatistics);
        LOGGER.info("Finished Insert product statistics products by category");
    }

    @Override
    public List<ProductStatisticsResponse> getStatistics(String statistic) {
        LOGGER.info("Starting Get product statistics by category");
        List<ProductStatistics> productStatistics = productStatisticsRepository.findByStatistic(statistic);
        LOGGER.info("Finished Get product statistics by category");
        return productStatistics.stream().map(ProductStatisticsMapper::mapToProductStaticsResponse)
                .collect(Collectors.toList());
    }
}
