package com.agendapro.challenge.controller;

import com.agendapro.challenge.bean.comunication.CreateUpdateProductRequest;
import com.agendapro.challenge.bean.comunication.ProductResponse;
import com.agendapro.challenge.exception.ResourceNotFoundException;
import com.agendapro.challenge.service.ProductService;
import com.agendapro.challenge.rabbit.mq.RabbitMQProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private ProductService productService;
    private RabbitMQProducer rabbitMQProducer;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateUpdateProductRequest createUpdateProductRequest){
        ProductResponse productCreated = productService.create(createUpdateProductRequest);
        //va a la cola mq
        rabbitMQProducer.send(createUpdateProductRequest);
        return new ResponseEntity<>(productCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Integer id){
        try {
            ProductResponse productResponse = productService.findById(id);
            return  ResponseEntity.ok(productResponse);
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProduct(){
        List<ProductResponse> productList = productService.findAll();
        return  ResponseEntity.ok(productList);
    }

    @GetMapping("/byName")
    public ResponseEntity<List<ProductResponse>> getByName(@RequestParam(required = false)  String name){
        List<ProductResponse> productList = productService.findByName(name);
        return  ResponseEntity.ok(productList);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Integer id, @RequestBody ProductResponse productResponse){
        try {
            ProductResponse productUpdated = productService.updateProduct(id, productResponse);
            return ResponseEntity.ok(productUpdated);
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse>  deleteProduct( @PathVariable Integer id){
        try {
            ProductResponse productDeleted = productService.deleteProduct(id);
            return ResponseEntity.ok(productDeleted);
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}