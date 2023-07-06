package com.example.glovo.controller;

import com.example.glovo.dto.OrderDto;
import com.example.glovo.dto.ProductDto;
import com.example.glovo.service.OrderService;
import com.example.glovo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }

    @GetMapping
    public List<OrderDto> getOrders() {
        return orderService.getAll();
    }

    @PostMapping
    public OrderDto add(@RequestBody OrderDto order) {
        return orderService.save(order);
    }

    @PostMapping("/{id}")
    public OrderDto add(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        productService.addToOrder(productDto, id);
        return orderService.getOrderById(id);
    }
}
