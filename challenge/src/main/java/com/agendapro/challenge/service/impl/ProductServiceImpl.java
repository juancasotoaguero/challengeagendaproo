package com.agendapro.challenge.service.impl;

import com.agendapro.challenge.bean.comunication.CreateUpdateProductRequest;
import com.agendapro.challenge.bean.comunication.ProductResponse;
import com.agendapro.challenge.entity.Product;
import com.agendapro.challenge.exception.ResourceNotFoundException;
import com.agendapro.challenge.mapper.ProductMapper;
import com.agendapro.challenge.repository.ProductRepository;
import com.agendapro.challenge.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public ProductResponse create(CreateUpdateProductRequest productDto) {

        LOGGER.debug("-----Start creating product-------");

        Product product = ProductMapper.mapToProductCreate(productDto);
        product.setStatus("A");
        Product productCreated = productRepository.save(product);
        LOGGER.debug("-----End creating product-------");
        return ProductMapper.mapToProductResponse(productCreated);
    }

    @Override
    public ProductResponse findById(Integer id) {
        LOGGER.debug("-----Start finding product by id-------");
        Product product = productRepository.findByIdAndStatus(id, "A")
                .orElseThrow(()-> new ResourceNotFoundException("Producto no encontrado con el id "+ id));
        LOGGER.debug("-----End finding product by id-------");
        return ProductMapper.mapToProductResponse(product);

    }

    @Override
    public List<ProductResponse> findByName(String name) {
        LOGGER.debug("-----Start finding product by name-------");
        List<Product> products = productRepository.findByStatusAndNameContainingIgnoreCase("A", name);
        LOGGER.debug("-----End finding product by name-------");
        return products.stream().map(ProductMapper::mapToProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> findAll() {
        LOGGER.debug("-----Start finding all products-------");
        List<Product> products = productRepository.findByStatus("A");
        LOGGER.debug("-----End finding all products-------");
        return products.stream().map(ProductMapper::mapToProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse updateProduct(Integer productId, ProductResponse updateProduct) {
        LOGGER.debug("-----Start updating product by id-------");
        Product product = productRepository.findByIdAndStatus(productId, "A")
                .orElseThrow(()-> new ResourceNotFoundException("Producto no encontrado con el id "+ productId));
        product.setDescription(updateProduct.getDescription());
        product.setName(updateProduct.getName());
        product.setCategory(updateProduct.getCategory());
        Product productUpdateObj = productRepository.save(product);
        LOGGER.debug("-----End updating product by id-------");
        return ProductMapper.mapToProductResponse(productUpdateObj);

    }

    @Override
    public ProductResponse deleteProduct(Integer productId) {
        LOGGER.debug("-----Start deleting product by id-------");
        //Lo que en realidad se hace es un borrado logico utilizando el atributo status, cambiandolo a  'I' de inactivo.
        Product product = productRepository.findByIdAndStatus(productId, "A")
                .orElseThrow(()-> new ResourceNotFoundException("Producto no encontrado con el id "+ productId));
        product.setStatus("I");
        Product productUpdateObj =  productRepository.save(product);
        LOGGER.debug("-----End deleting product by id-------");
        return ProductMapper.mapToProductResponse(productUpdateObj);
    }
}
