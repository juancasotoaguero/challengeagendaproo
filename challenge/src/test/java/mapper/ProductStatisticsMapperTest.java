package mapper;

import com.agendapro.challenge.bean.comunication.ProductStatisticsResponse;
import com.agendapro.challenge.entity.ProductStatistics;
import com.agendapro.challenge.mapper.ProductStatisticsMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductStatisticsMapperTest {

    @Test
    void testMapToProductStatisticsResponse() {
        ProductStatistics productStatistics = new ProductStatistics(1, "Computers", "PRODUCT_CATEGORY", 100,"");

        ProductStatisticsResponse productStatisticsResponse = ProductStatisticsMapper.mapToProductStaticsResponse(productStatistics);

        assertNotNull(productStatisticsResponse);
        assertEquals(productStatistics.getId(), productStatisticsResponse.getId());
        assertEquals(productStatistics.getStatistic(), productStatisticsResponse.getStatistic());
        assertEquals(productStatistics.getQuantity(), productStatisticsResponse.getQuantity());
    }

}