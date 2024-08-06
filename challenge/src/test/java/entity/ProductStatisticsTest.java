package entity;

import com.agendapro.challenge.entity.ProductStatistics;
import com.agendapro.challenge.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ProductStatisticsTest {

    @Test
    public void testGettersAndSetters() {
        ProductStatistics test = new ProductStatistics();
        test.setId(1);
        test.setName("test");
        test.setStatistic("test");
        test.setQuantity(1);
        test.setStatus("A");

        assertNotNull(test);
        assertNotNull(test.getId());
        assertNotNull(test.getName());
        assertNotNull(test.getStatistic());
        assertNotNull(test.getQuantity());
        assertNotNull(test.getStatus());

    }
}
