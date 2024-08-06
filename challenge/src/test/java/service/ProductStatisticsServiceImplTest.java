package service;

import com.agendapro.challenge.bean.comunication.ProductStatisticsResponse;
import com.agendapro.challenge.bean.domain.ProductStatisticsEnum;
import com.agendapro.challenge.entity.ProductStatistics;
import com.agendapro.challenge.repository.ProductStatisticsRepository;
import com.agendapro.challenge.service.impl.ProductStatisticsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductStatisticsServiceImplTest {

    @Mock
    private ProductStatisticsRepository productStatisticsRepository;

    @Mock
    private Logger logger;

    @InjectMocks
    private ProductStatisticsServiceImpl productStatisticsService;

    private ProductStatistics productStatistics;
    private ProductStatisticsResponse productStatisticsResponse;

    @BeforeEach
    void setUp() {
        productStatistics = new ProductStatistics();
        productStatistics.setStatistic("PRODUCT_BY_CATEGORY");
        productStatistics.setName("PRODUCT_BY_CATEGORY / Electronics");
        productStatistics.setQuantity(1);
        productStatistics.setStatus("A");

        productStatisticsResponse = new ProductStatisticsResponse();
        productStatisticsResponse.setStatistic("PRODUCT_BY_CATEGORY");
        productStatisticsResponse.setName("PRODUCT_BY_CATEGORY / Electronics");
        productStatisticsResponse.setQuantity(1);
    }

    @Test
    void testInsertProductStatisticsProductsByCategory_UpdateExisting() {
        Mockito.when(productStatisticsRepository.findByStatisticAndName(anyString(), anyString()))
                .thenReturn(Optional.of(productStatistics));

        productStatisticsService.insertProductStatisticsProductsByCategory(ProductStatisticsEnum.PRODUCT_BY_CATEGORY, "Electronics");

        Mockito.verify(productStatisticsRepository).save(productStatistics);
        assertEquals(2, productStatistics.getQuantity());
    }

    @Test
    void testInsertProductStatisticsProductsByCategory_CreateNew() {
        Mockito.when(productStatisticsRepository.findByStatisticAndName(anyString(), anyString()))
                .thenReturn(Optional.empty());

        productStatisticsService.insertProductStatisticsProductsByCategory(ProductStatisticsEnum.PRODUCT_BY_CATEGORY, "Electronics");

        Mockito.verify(productStatisticsRepository).save(any(ProductStatistics.class));
    }

    @Test
    void testGetStatistics() {
        List<ProductStatistics> productStatisticsList = Arrays.asList(productStatistics);
        Mockito.when(productStatisticsRepository.findByStatistic(anyString())).thenReturn(productStatisticsList);

        List<ProductStatisticsResponse> responses = productStatisticsService.getStatistics("PRODUCT_BY_CATEGORY");

        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals(productStatisticsResponse.getStatistic(), responses.get(0).getStatistic());
        assertEquals(productStatisticsResponse.getName(), responses.get(0).getName());
        assertEquals(productStatisticsResponse.getQuantity(), responses.get(0).getQuantity());
    }
}