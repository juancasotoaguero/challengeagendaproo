package com.agendapro.challenge.bean.comunication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductStatisticsResponse {
    private Integer id;
    private String name;
    private String statistic;
    private Integer quantity;
}
