package com.example.glovo.controller;

import com.example.glovo.model.Order;
import com.example.glovo.model.Product;
import com.example.glovo.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Integer id) {
        return this.orderService.getOrderById(id).orElseThrow();
    }

    @GetMapping
    public List<Order> getOrders() {
        return this.orderService.getAll();
    }

    @PostMapping
    public Order save (@RequestBody Order order){
        return this.orderService.save(order);
    }

    @PostMapping("/{id}")
    public Order addProduct (@PathVariable Integer id, @RequestBody Product product){
        return this.orderService.addProduct(id, product).orElseThrow();
    }
}
