package service;

import com.agendapro.challenge.bean.comunication.CreateUpdateProductRequest;
import com.agendapro.challenge.bean.comunication.ProductResponse;
import com.agendapro.challenge.entity.Product;
import com.agendapro.challenge.exception.ResourceNotFoundException;
import com.agendapro.challenge.repository.ProductRepository;
import com.agendapro.challenge.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private ProductResponse productResponse;
    private CreateUpdateProductRequest createUpdateProductRequest;

    @BeforeEach
    void setUp() {
        product = new Product(1, "ProductName", "ProductDescription", "ProductCategory");
        productResponse = new ProductResponse(1, "ProductName", "ProductDescription", "ProductCategory");
        createUpdateProductRequest = new CreateUpdateProductRequest("ProductName", "ProductDescription", "ProductCategory");
    }

    @Test
    void testCreateProduct() {
        Mockito.when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductResponse response = productService.create(createUpdateProductRequest);

        assertNotNull(response);
        assertEquals(productResponse.getId(), response.getId());
        assertEquals(productResponse.getName(), response.getName());
        assertEquals(productResponse.getDescription(), response.getDescription());
        assertEquals(productResponse.getCategory(), response.getCategory());
    }

    @Test
    void testFindById() {
        Mockito.when(productRepository.findByIdAndStatus(anyInt(), eq("A"))).thenReturn(Optional.of(product));

        ProductResponse response = productService.findById(1);

        assertNotNull(response);
        assertEquals(productResponse.getId(), response.getId());
        assertEquals(productResponse.getName(), response.getName());
        assertEquals(productResponse.getDescription(), response.getDescription());
        assertEquals(productResponse.getCategory(), response.getCategory());
    }

    @Test
    void testFindByIdNotFound() {
        Mockito.when(productRepository.findByIdAndStatus(anyInt(), eq("A"))).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            productService.findById(1);
        });

        assertEquals("Producto no encontrado con el id 1", thrown.getMessage());
    }

    @Test
    void testFindByName() {
        List<Product> products = Arrays.asList(product);
        Mockito.when(productRepository.findByStatusAndNameContainingIgnoreCase(anyString(), anyString()))
                .thenReturn(products);

        List<ProductResponse> responses = productService.findByName("ProductName");

        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals(productResponse.getName(), responses.get(0).getName());
    }

    @Test
    void testFindAll() {
        List<Product> products = Arrays.asList(product);
        Mockito.when(productRepository.findByStatus(anyString())).thenReturn(products);

        List<ProductResponse> responses = productService.findAll();

        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals(productResponse.getName(), responses.get(0).getName());
    }

    @Test
    void testUpdateProduct() {
        Product updatedProduct = new Product(1, "UpdatedName", "UpdatedDescription", "UpdatedCategory");
        Mockito.when(productRepository.findByIdAndStatus(anyInt(), eq("A"))).thenReturn(Optional.of(product));
        Mockito.when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        ProductResponse response = productService.updateProduct(1, productResponse);

        assertNotNull(response);
        assertEquals(updatedProduct.getName(), response.getName());
        assertEquals(updatedProduct.getDescription(), response.getDescription());
        assertEquals(updatedProduct.getCategory(), response.getCategory());
    }

    @Test
    void testDeleteProduct() {
        Mockito.when(productRepository.findByIdAndStatus(anyInt(), eq("A"))).thenReturn(Optional.of(product));
        Mockito.when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductResponse response = productService.deleteProduct(1);

        assertNotNull(response);

    }
}