package entity;

import com.agendapro.challenge.entity.Product;
import com.agendapro.challenge.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ProductTest {

    @Test
    public void testGettersAndSetters() {
        Product test = new Product();
        test.setId(1);
        test.setName("test");
        test.setDescription("description");
        test.setCategory("category");
        test.setStatus("A");

        assertNotNull(test);
        assertNotNull(test.getId());
        assertNotNull(test.getDescription());
        assertNotNull(test.getCategory());
        assertNotNull(test.getStatus());

    }
}
