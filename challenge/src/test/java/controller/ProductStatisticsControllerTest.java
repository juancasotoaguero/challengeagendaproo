package controller;

import com.agendapro.challenge.bean.comunication.ProductStatisticsResponse;
import com.agendapro.challenge.controller.ProductStatisticsController;
import com.agendapro.challenge.service.ProductStatisticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductStatisticsControllerTest {

    @Mock
    private ProductStatisticsService productStatisticsService;

    @InjectMocks
    private ProductStatisticsController productStatisticsController;

    private List<ProductStatisticsResponse> productStatisticsResponses;

    @BeforeEach
    void setUp() {
        productStatisticsResponses = Arrays.asList(new ProductStatisticsResponse(), new ProductStatisticsResponse());
    }

    @Test
    void testGetByName() {
        when(productStatisticsService.getStatistics(anyString())).thenReturn(productStatisticsResponses);

        ResponseEntity<List<ProductStatisticsResponse>> responseEntity = productStatisticsController.getByName("statistics");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productStatisticsResponses, responseEntity.getBody());
    }

    @Test
    void testGetByNameWithoutStatistics() {
        when(productStatisticsService.getStatistics(null)).thenReturn(productStatisticsResponses);

        ResponseEntity<List<ProductStatisticsResponse>> responseEntity = productStatisticsController.getByName(null);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productStatisticsResponses, responseEntity.getBody());
    }
}
