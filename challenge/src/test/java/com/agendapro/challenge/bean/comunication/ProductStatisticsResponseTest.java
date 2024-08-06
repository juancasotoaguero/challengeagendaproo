package com.agendapro.challenge.bean.comunication;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ProductStatisticsResponseTest {

    @Test
    public void testGettersAndSetters() {
        ProductStatisticsResponse test = new ProductStatisticsResponse();
        test.setId(1);
        test.setName("test");
        test.setStatistic("Statictis");
        test.setQuantity(3);

        assertNotNull(test);
        assertNotNull(test.getId());
        assertNotNull(test.getName());
        assertNotNull(test.getStatistic());
        assertNotNull(test.getQuantity());
    }
}
