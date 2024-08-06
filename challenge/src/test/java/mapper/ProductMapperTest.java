package mapper;

import com.agendapro.challenge.bean.comunication.CreateUpdateProductRequest;
import com.agendapro.challenge.bean.comunication.ProductResponse;
import com.agendapro.challenge.entity.Product;
import com.agendapro.challenge.mapper.ProductMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductMapperTest {

    @Test
    void testMapToProductResponse() {
        Product product = new Product(1, "ProductName", "ProductDescription", "ProductCategory");

        ProductResponse productResponse = ProductMapper.mapToProductResponse(product);

        assertNotNull(productResponse);
        assertEquals(product.getId(), productResponse.getId());
        assertEquals(product.getName(), productResponse.getName());
        assertEquals(product.getDescription(), productResponse.getDescription());
        assertEquals(product.getCategory(), productResponse.getCategory());
    }

    @Test
    void testMapToProduct() {
        ProductResponse productResponse = new ProductResponse(1, "ProductName", "ProductDescription", "ProductCategory");

        Product product = ProductMapper.mapToProduct(productResponse);

        assertNotNull(product);
        assertEquals(productResponse.getId(), product.getId());
        assertEquals(productResponse.getName(), product.getName());
        assertEquals(productResponse.getDescription(), product.getDescription());
        assertEquals(productResponse.getCategory(), product.getCategory());
    }

    @Test
    void testMapToProductCreate() {
        CreateUpdateProductRequest createUpdateProductRequest = new CreateUpdateProductRequest("ProductName", "ProductDescription", "ProductCategory");

        Product product = ProductMapper.mapToProductCreate(createUpdateProductRequest);

        assertNotNull(product);
        assertEquals(createUpdateProductRequest.getName(), product.getName());
        assertEquals(createUpdateProductRequest.getDescription(), product.getDescription());
        assertEquals(createUpdateProductRequest.getCategory(), product.getCategory());
    }
}