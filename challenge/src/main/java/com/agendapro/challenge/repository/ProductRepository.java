package com.agendapro.challenge.repository;

import com.agendapro.challenge.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    List<Product> findByStatus(String status);
    Optional<Product> findByIdAndStatus(Integer id, String status);
    List<Product> findByStatusAndNameContainingIgnoreCase(String status, String name);
}
