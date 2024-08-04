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

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public ProductResponse create(CreateUpdateProductRequest productDto) {
        Product product = ProductMapper.mapToProductCreate(productDto);
        product.setStatus("A");
        Product productCreated = productRepository.save(product);
        return ProductMapper.mapToProductResponse(productCreated);
    }

    @Override
    public ProductResponse findById(Integer id) {
        Product product = productRepository.findByIdAndStatus(id, "A")
                .orElseThrow(()-> new ResourceNotFoundException("Producto no encontrado con el id "+ id));
        return ProductMapper.mapToProductResponse(product);
    }

    @Override
    public List<ProductResponse> findByName(String name) {
        List<Product> products = productRepository.findByStatusAndNameContainingIgnoreCase("A", name);
        return products.stream().map(ProductMapper::mapToProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> findAll() {
        List<Product> products = productRepository.findByStatus("A");
        return products.stream().map(ProductMapper::mapToProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse updateProduct(Integer productId, ProductResponse updateProduct) {
        Product product = productRepository.findByIdAndStatus(productId, "A")
                .orElseThrow(()-> new ResourceNotFoundException("Producto no encontrado con el id "+ productId));
        product.setDescription(updateProduct.getDescription());
        product.setName(updateProduct.getName());
        product.setCategory(updateProduct.getCategory());
        Product productUpdateObj = productRepository.save(product);
        return ProductMapper.mapToProductResponse(productUpdateObj);

    }

    @Override
    public ProductResponse deleteProduct(Integer productId) {
        //Lo que en realidad se hace es un borrado logico utilizando el atributo status, cambiandolo a  'I' de inactivo.
        Product product = productRepository.findByIdAndStatus(productId, "A")
                .orElseThrow(()-> new ResourceNotFoundException("Producto no encontrado con el id "+ productId));
        product.setStatus("I");
        Product productUpdateObj =  productRepository.save(product);
        return ProductMapper.mapToProductResponse(productUpdateObj);
    }
}
