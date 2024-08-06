package controller;

import com.agendapro.challenge.bean.comunication.CreateUpdateProductRequest;
import com.agendapro.challenge.bean.comunication.ProductResponse;
import com.agendapro.challenge.controller.ProductController;
import com.agendapro.challenge.exception.ResourceNotFoundException;
import com.agendapro.challenge.rabbit.mq.RabbitMQProducer;
import com.agendapro.challenge.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private RabbitMQProducer rabbitMQProducer;

    @InjectMocks
    private ProductController productController;

    private CreateUpdateProductRequest createUpdateProductRequest;
    private ProductResponse productResponse;

    @BeforeEach
    void setUp() {
        createUpdateProductRequest = new CreateUpdateProductRequest();
        productResponse = new ProductResponse();
    }

    @Test
    void testCreateProduct() {
        when(productService.create(any(CreateUpdateProductRequest.class))).thenReturn(productResponse);

        ResponseEntity<ProductResponse> responseEntity = productController.createProduct(createUpdateProductRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(productResponse, responseEntity.getBody());
        Mockito.verify(rabbitMQProducer).send(any(CreateUpdateProductRequest.class));
    }

    @Test
    void testGetProductById() {
        when(productService.findById(any(Integer.class))).thenReturn(productResponse);

        ResponseEntity<ProductResponse> responseEntity = productController.getProductById(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productResponse, responseEntity.getBody());
    }

    @Test
    void testGetProductByIdNotFound() {
        when(productService.findById(any(Integer.class))).thenThrow(new ResourceNotFoundException("Error"));

        ResponseEntity<ProductResponse> responseEntity = productController.getProductById(1);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testGetAllProducts() {
        List<ProductResponse> productResponses = Arrays.asList(new ProductResponse(), new ProductResponse());

        when(productService.findAll()).thenReturn(productResponses);

        ResponseEntity<List<ProductResponse>> responseEntity = productController.getAllProduct();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productResponses, responseEntity.getBody());
    }

    @Test
    void testGetByName() {
        List<ProductResponse> productResponses = Arrays.asList(new ProductResponse(), new ProductResponse());

        when(productService.findByName(any(String.class))).thenReturn(productResponses);

        ResponseEntity<List<ProductResponse>> responseEntity = productController.getByName("example");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productResponses, responseEntity.getBody());
    }

    @Test
    void testUpdateProduct() {
        when(productService.updateProduct(any(Integer.class), any(ProductResponse.class))).thenReturn(productResponse);

        ResponseEntity<ProductResponse> responseEntity = productController.updateProduct(1, productResponse);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productResponse, responseEntity.getBody());
    }

    @Test
    void testUpdateProductNotFound() {
        when(productService.updateProduct(any(Integer.class), any(ProductResponse.class))).thenThrow(new ResourceNotFoundException("Error"));

        ResponseEntity<ProductResponse> responseEntity = productController.updateProduct(1, productResponse);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteProduct() {
        when(productService.deleteProduct(any(Integer.class))).thenReturn(productResponse);

        ResponseEntity<ProductResponse> responseEntity = productController.deleteProduct(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productResponse, responseEntity.getBody());
    }

    @Test
    void testDeleteProductNotFound() {
        when(productService.deleteProduct(any(Integer.class))).thenThrow(new ResourceNotFoundException("Error"));

        ResponseEntity<ProductResponse> responseEntity = productController.deleteProduct(1);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}