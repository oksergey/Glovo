package com.example.glovo.converter;

import com.example.glovo.dto.OrderDto;
import com.example.glovo.dto.ProductDto;
import com.example.glovo.entity.Order;
import com.example.glovo.entity.Product;

import java.util.List;

public class OrderConverter {

    public static OrderDto toOrderDto(Order order, List<Product> products) {
        List<ProductDto> productDtos = products.stream().map(p -> ProductDto.builder()
                .id(p.getId())
                .name(p.getName())
                .cost(p.getCost())
                .build()).toList();

        double sum = productDtos.stream().mapToDouble(ProductDto::getCost).sum();

        return
                OrderDto.builder().id(order.getId()).date(order.getDate()).cost(sum).products(productDtos).build();

    }
}
