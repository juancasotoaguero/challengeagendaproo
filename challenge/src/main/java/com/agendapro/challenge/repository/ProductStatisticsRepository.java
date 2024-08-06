package com.agendapro.challenge.repository;

import com.agendapro.challenge.entity.ProductStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductStatisticsRepository extends JpaRepository<ProductStatistics, Long> {
    Optional<ProductStatistics> findByStatisticAndName(String statistics, String name);
    List<ProductStatistics> findByStatistic(String statistic);
}
