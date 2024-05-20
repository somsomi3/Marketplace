package org.example.orderapi.service;

import lombok.RequiredArgsConstructor;
import org.example.orderapi.domain.model.Product;
import org.example.orderapi.domain.product.AddProductForm;
import org.example.orderapi.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public Product addProduct(Long sellerId, AddProductForm form){
        return productRepository.save(Product.of(sellerId,form));
    }//상품에 대한 추가
}
