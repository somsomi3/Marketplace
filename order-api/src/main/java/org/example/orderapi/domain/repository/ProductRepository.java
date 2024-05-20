package org.example.orderapi.domain.repository;

import org.example.orderapi.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
