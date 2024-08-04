package com.agendapro.challenge.service;

import com.agendapro.challenge.bean.comunication.CreateUpdateProductRequest;
import com.agendapro.challenge.bean.comunication.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse create(CreateUpdateProductRequest productDto);

    ProductResponse findById(Integer id);

    List<ProductResponse> findByName(String name);

    List<ProductResponse> findAll();

    ProductResponse updateProduct(Integer productId, ProductResponse updateProduct);

    ProductResponse deleteProduct(Integer productId);
}
