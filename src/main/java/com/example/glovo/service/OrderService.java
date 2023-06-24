package com.example.glovo.service;

import com.example.glovo.model.Order;
import com.example.glovo.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final List<Order> orders = new ArrayList<>();

    public Optional<Order> getOrderById(int id) {
        return orders.stream().filter(order -> order.getId() == id).findFirst();
    }

    public List<Order> getAll() {
        return this.orders;
    }

    public Order save(Order order) {
        Order orderAdded = new Order(order);
        orders.add(orderAdded);
        return orderAdded;
    }

    public Optional<Order> addProduct(int id, Product product) {
        orders.get(id).getProducts().add(product);
        calculateCost(id);
        return getOrderById(id);
    }

    private void calculateCost(int id) {
        double newCost = 0;

        for (Product product : orders.get(id).getProducts()) {
            newCost += product.getCost();
        }
        orders.get(id).setCost(newCost);
    }
}
