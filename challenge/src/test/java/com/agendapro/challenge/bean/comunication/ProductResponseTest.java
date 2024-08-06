package com.agendapro.challenge.bean.comunication;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ProductResponseTest {
    @Test
    public void testGettersAndSetters() {
        ProductResponse test = new ProductResponse();
        test.setId(1);
        test.setName("test");
        test.setDescription("description");
        test.setCategory("category");

        assertNotNull(test);
        assertNotNull(test.getId());
        assertNotNull(test.getName());
        assertNotNull(test.getDescription());
        assertNotNull(test.getCategory());
    }
}
