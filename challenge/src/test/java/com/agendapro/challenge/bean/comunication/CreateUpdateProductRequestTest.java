package com.agendapro.challenge.bean.comunication;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CreateUpdateProductRequestTest {

    @Test
    public void testGettersAndSetters() {
        CreateUpdateProductRequest request = new CreateUpdateProductRequest();
        request.setName("name");
        request.setCategory("category");
        request.setDescription("description");

        assertNotNull(request);
        assertNotNull(request.getName());
        assertNotNull(request.getCategory());
        assertNotNull(request.getDescription());
    }
}
