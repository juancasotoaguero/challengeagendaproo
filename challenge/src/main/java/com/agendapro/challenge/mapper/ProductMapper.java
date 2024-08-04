package com.agendapro.challenge.mapper;

import com.agendapro.challenge.bean.comunication.CreateUpdateProductRequest;
import com.agendapro.challenge.bean.comunication.ProductResponse;
import com.agendapro.challenge.entity.Product;

public class ProductMapper {
    public static ProductResponse mapToProductResponse(Product product){
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCategory()
        );
    }

    public static Product mapToProduct(ProductResponse productResponse){
        return new Product(
                productResponse.getId(),
                productResponse.getName(),
                productResponse.getDescription(),
                productResponse.getCategory()
        );
    }

    public static Product mapToProductCreate(CreateUpdateProductRequest createUpdateProductRequest){
        return new Product(
                createUpdateProductRequest.getName(),
                createUpdateProductRequest.getDescription(),
                createUpdateProductRequest.getCategory()
        );
    }
}
