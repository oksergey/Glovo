package com.example.glovo.service;

import com.example.glovo.dto.ProductDto;
import com.example.glovo.entity.Product;
import com.example.glovo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void addToOrder(ProductDto productDto, int orderId) {
        Product product = Product.builder()
                .name(productDto.getName())
                .cost(productDto.getCost())
                .orderId(orderId).build();

        productRepository.save(product);

    }

}
