package com.example.glovo.service;

import com.example.glovo.converter.OrderConverter;
import com.example.glovo.dto.OrderDto;
import com.example.glovo.entity.Order;
import com.example.glovo.entity.Product;
import com.example.glovo.exeption.OrderNotFoundExeption;
import com.example.glovo.repository.OrderRepository;
import com.example.glovo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor

public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderDto save(OrderDto orderDto) {

        Order savedOrder = orderRepository.save(Order.builder().date(Date.valueOf(LocalDate.now())).build());

        List<Product> products = orderDto.getProducts().stream()
                .map(productDto -> Product.builder()
                        .cost(productDto.getCost())
                        .name(productDto.getName())
                        .orderId(savedOrder.getId())
                        .build()).toList();

        products = (List<Product>) productRepository.saveAll(products);

        return OrderConverter.toOrderDto(savedOrder, products);
    }

    public List<OrderDto> getAll() {
        return orderRepository.findAll().stream()
                .map(order -> OrderConverter.toOrderDto(order, productRepository.findAllByOrderId(order.getId())))
                .toList();
    }

    public OrderDto getOrderById(int id) {
        return orderRepository.findById(id)
                .map(order -> OrderConverter.toOrderDto(order, productRepository.findAllByOrderId(id)))
                .orElseThrow(() -> new OrderNotFoundExeption("Order not found..."));
    }
}
